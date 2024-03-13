package com.doonutmate.util;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.TimeZone;

public class CommonDateUtils {

    private static final String KST_ZONE_ID = "Asia/Seoul";
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMdd").withZone(TimeZone.getTimeZone(KST_ZONE_ID).toZoneId());

    public static Instant getInstant(int year, int month, int day) {
        Calendar calendar = getCalendar(year, month, day);
        return calendar.toInstant();
    }

    public static Instant getFirst(int year, int month) {
        return getInstant(year, month, 1);
    }

    public static Instant getLast(int year, int month) {
        var lastDay = getCalendar(year, month, 1).getActualMaximum(Calendar.DAY_OF_MONTH);
        var calendar = getCalendar(year, month, lastDay, 23, 59, 59, 999);

        return calendar.toInstant();
    }

    private static Calendar getCalendar(int year, int month, int day) {
        return getCalendar(year, month, day, 0, 0, 0, 0);
    }

    private static Calendar getCalendar(int year, int month, int day, int hourOfDay, int minute, int second, int ms) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(KST_ZONE_ID));
        calendar.set(year, getCalendarMonth(month), day, hourOfDay, minute, second);
        calendar.set(Calendar.MILLISECOND, ms);

        return calendar;
    }

    public static String getYearMonthDay(Instant instant) {
        return DATE_TIME_FORMATTER.format(instant);
    }

    public static int getDay(Instant instant) {
        return instant.atZone(TimeZone.getTimeZone(KST_ZONE_ID).toZoneId()).getDayOfMonth();
    }

    public static Instant getToday() {
        var now = Instant.now().atZone(TimeZone.getTimeZone(KST_ZONE_ID).toZoneId());
        var year = now.getYear();
        var month = now.getMonth().getValue();
        var day = now.getDayOfMonth();

        return getInstant(year, month, day);
    }

    /**
     * Calendar는 month가 0부터 시작한다.
     *
     * @see java.util.Calendar.JANUARY
     */
    private static int getCalendarMonth(int month) {
        return month - 1;
    }
}
