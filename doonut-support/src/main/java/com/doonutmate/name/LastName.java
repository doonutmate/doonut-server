package com.doonutmate.name;

import java.util.Random;

public enum LastName {

    토끼("토끼"),
    오리("오리"),
    양("양"),
    사자("사자"),
    코끼리("코끼리"),
    사슴("사슴"),
    호랑이("호랑이"),
    거미("거미"),
    여우("여우"),
    강아지("강아지"),
    원숭이("원숭이"),
    쥐("쥐"),
    소("소"),
    뱀("뱀"),
    말("말"),
    상어("상어"),
    돼지("돼지"),
    늑대("늑대"),
    코뿔소("코뿔소"),
    라쿤("라쿤"),
    유니콘("유니콘"),
    나비("나비"),
    햄스터("햄스터"),
    오징어("오징어"),
    고등어("고등어"),
    참새("참새"),
    독수리("독수리"),
    도마뱀("도마뱀"),
    앵무새("앵무새"),
    선인장("선인장"),
    네잎클로버("네잎클로버"),
    문어("문어"),
    거북이("거북이"),
    얼룩말("얼룩말"),
    해파리("해파리"),
    악어("악어"),
    곰("곰"),
    고양이("고양이"),
    닭("닭"),
    개미("개미"),
    공룡("공룡"),
    나무늘보("나무늘보"),
    고슴도치("고슴도치"),
    하마("하마"),
    드래곤("드래곤"),
    박쥐("박쥐"),
    오랑우탄("오랑우탄"),
    낙타("낙타"),
    병아리("병아리"),
    비둘기("비둘기"),
    꿀벌("꿀벌"),
    표범("표범"),
    코알라("코알라"),
    캥거루("캥거루"),
    펭귄("펭귄"),
    스컹크("스컹크"),
    달팽이("달팽이"),
    해바라기("해바라기"),
    지렁이("지렁이");

    private static final Random PRNG = new Random();
    private final String text;

    LastName(String text) {
        this.text = text;
    }

    public static LastName getRandomLastName() {
        LastName[] lastNames = values();
        return lastNames[PRNG.nextInt(lastNames.length)];
    }

    public String getText() {
        return text;
    }
}
