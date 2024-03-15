package com.doonutmate.doonut.member.model;

public enum OauthTypeStrategy {
    KAKAO("kakaoMemberDelete"),
    APPLE("appleMemberDelete");

    public final String strategyName;

    OauthTypeStrategy(String strategyName) {
        this.strategyName = strategyName;
    }
}
