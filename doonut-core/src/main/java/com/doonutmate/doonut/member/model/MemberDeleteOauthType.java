package com.doonutmate.doonut.member.model;

public enum MemberDeleteOauthType {
    KAKAO("kakaoMemberDelete"),
    APPLE("appleMemberDelete");

    public final String strategyName;

    MemberDeleteOauthType(String strategyName) {
        this.strategyName = strategyName;
    }
}
