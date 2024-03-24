package com.doonutmate.doonut.challenge.model;

import lombok.Getter;

@Getter
public enum ChallengeType {
    DEFAULT(390, 390),
    THUMBNAIL(46, 46)
    ;

    public static final String QUERY_STRING_FORMAT = "?w=%s&h=%s";
    private final int width;
    private final int height;

    ChallengeType(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static String getDefaultUrl(String url) {
        var queryString = String.format(QUERY_STRING_FORMAT, ChallengeType.DEFAULT.getWidth(), ChallengeType.DEFAULT.getHeight());
        return url + queryString;
    }

    public static String getThumbNailUrl(String url) {
        var queryString = String.format(QUERY_STRING_FORMAT, ChallengeType.THUMBNAIL.getWidth(), ChallengeType.THUMBNAIL.getHeight());
        return url + queryString;
    }
}
