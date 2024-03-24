package com.doonutmate.doonut.member.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ImageType {

    REPRESENTATIVE("대표이미지");

    @Getter
    private final String text;
}
