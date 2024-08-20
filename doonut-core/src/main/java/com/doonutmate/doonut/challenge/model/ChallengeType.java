package com.doonutmate.doonut.challenge.model;

import lombok.Getter;

@Getter
public enum ChallengeType {
    DEFAULT(1000, 1000),
    THUMBNAIL(46, 46);

    private static final String DEFAULT_URL_QUERY_STRING_FORMAT = "?w=%s&h=%s&swidth=%d&sheight=%d&smargin=%d&sleft=%d&fontSize=%d&stdDeviation=%d&preset=timestamp";
    private static final String THUMBNAIL_URL_QUERY_STRING_FORMAT = "?w=%s&h=%s";
    private static final int DEFAULT_SWIDTH = 200;
    private static final int DEFAULT_SHEIGHT = 50;
    private static final int DEFAULT_SMARGIN = 20;
    private static final int DEFAULT_SLEFT = 25;
    private static final int DEFAULT_FONT_SIZE = 20;
    private static final int DEFAULT_STD_DEVIATION = 3;

    private final int width;
    private final int height;

    ChallengeType(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static String getDefaultUrl(String url) {
        var queryString = String.format(THUMBNAIL_URL_QUERY_STRING_FORMAT, ChallengeType.DEFAULT.getWidth(), ChallengeType.DEFAULT.getHeight());
        return url + queryString;
    }

    public static String getThumbNailUrl(String url) {
        var queryString = String.format(THUMBNAIL_URL_QUERY_STRING_FORMAT, ChallengeType.THUMBNAIL.getWidth(), ChallengeType.THUMBNAIL.getHeight());
        return url + queryString;
    }
}
