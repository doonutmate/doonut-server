package com.doonutmate.util;

import java.time.Instant;
import java.util.Calendar;
import java.util.TimeZone;

public class CommonDateUtils {

    public static final String KST_ZONE_ID = "Asia/Seoul";

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

    public static int getDay(Instant instant) {
        return instant.atZone(TimeZone.getTimeZone(KST_ZONE_ID).toZoneId()).getDayOfMonth();
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
