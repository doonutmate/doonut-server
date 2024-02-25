package com.doonutmate.doonut.challenge.model;

import lombok.Getter;

@Getter
public enum ChallengeType {
    DEFAULT(390, 390),
    THUMBNAIL(46, 46)
    ;

    private final int width;
    private final int height;

    ChallengeType(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
