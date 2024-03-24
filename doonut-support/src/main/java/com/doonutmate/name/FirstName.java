package com.doonutmate.name;

import java.util.Random;

public enum FirstName {

    깔끔한("깔끔한"),
    근면한("근면한"),
    엄격한("엄격한"),
    꼼꼼한("꼼꼼한"),
    솔직한("솔직한"),
    냉정한("냉정한"),
    용감한("용감한"),
    완고한("완고한"),
    세련된("세련된"),
    도도한("도도한"),
    외향적인("외향적인"),
    창의적인("창의적인"),
    유순한("유순한"),
    신중한("신중한"),
    아름다운("아름다운"),
    빛나는("빛나는"),
    성실한("성실한"),
    친절한("친절한"),
    진실한("진실한"),
    정직한("정직한"),
    정중한("정중한"),
    헤엄치는("헤엄치는"),
    웃음짓는("웃음짓는"),
    당황한("당황한"),
    낙천적인("낙천적인"),
    존경받는("존경받는"),
    사랑스러운("사랑스러운"),
    비밀스러운("비밀스러운"),
    행복한("행복한"),
    우아한("우아한"),
    배고픈("배고픈"),
    배부른("배부른"),
    목마른("목마른"),
    활기찬("활기찬"),
    잠자는("잠자는"),
    심심한("심심한"),
    수다스러운("수다스러운"),
    재잘대는("재잘대는"),
    겸손한("겸손한"),
    조용한("조용한"),
    시끄러운("시끄러운"),
    춤추는("춤추는"),
    노래하는("노래하는"),
    박수치는("박수치는"),
    뒹굴대는("뒹굴대는"),
    두근대는("두근대는"),
    미소짓는("미소짓는"),
    무뚝뚝한("무뚝뚝한"),
    정신없는("정신없는"),
    꿈틀대는("꿈틀대는"),
    고민많은("고민많은");

    private static final Random PRNG = new Random();
    private final String text;

    FirstName(String text) {
        this.text = text;
    }

    public static FirstName getRandomFirstName() {
        FirstName[] firstNames = values();
        return firstNames[PRNG.nextInt(firstNames.length)];
    }

    public String getText() {
        return text;
    }
}
