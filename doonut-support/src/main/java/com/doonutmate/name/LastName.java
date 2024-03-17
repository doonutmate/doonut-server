package com.doonutmate.name;

import java.util.Random;

public enum LastName {

    토끼,
    오리,
    양,
    사자,
    코끼리,
    사슴,
    호랑이,
    거미,
    여우,
    강아지,
    원숭이,
    쥐,
    소,
    뱀,
    말,
    상어,
    돼지,
    늑대,
    코뿔소,
    라쿤,
    유니콘,
    나비,
    햄스터,
    오징어,
    고등어,
    참새,
    독수리,
    도마뱀,
    앵무새,
    선인장,
    네잎클로버,
    문어,
    거북이,
    얼룩말,
    해파리,
    악어,
    곰,
    고양이,
    닭,
    개미,
    공룡,
    나무늘보,
    고슴도치,
    하마,
    드래곤,
    박쥐,
    오랑우탄,
    낙타,
    병아리,
    비둘기,
    꿀벌,
    표범,
    코알라,
    캥거루,
    펭귄,
    스컹크,
    달팽이,
    해바라기,
    지렁이;

    private static final Random PRNG = new Random();

    public static LastName getRandomLastName() {
        LastName[] lastNames = values();
        return lastNames[PRNG.nextInt(lastNames.length)];
    }
}
