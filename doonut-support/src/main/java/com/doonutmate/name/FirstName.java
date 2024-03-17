package com.doonutmate.name;

import java.util.Random;

public enum FirstName {

    깔끔한,
    근면한,
    엄격한,
    꼼꼼한,
    솔직한,
    냉정한,
    용감한,
    완고한,
    세련된,
    도도한,
    외향적인,
    창의적인,
    유순한,
    신중한,
    아름다운,
    빛나는,
    성실한,
    친절한,
    진실한,
    정직한,
    정중한,
    헤엄치는,
    웃음짓는,
    당황한,
    낙천적인,
    존경받는,
    사랑스러운,
    비밀스러운,
    행복한,
    자신감있는,
    우아한,
    배고픈,
    배부른,
    목마른,
    활기찬,
    잠자는,
    심심한,
    수다스러운,
    재잘대는,
    겸손한,
    조용한,
    시끄러운,
    춤추는,
    노래하는,
    박수치는,
    뒹굴대는,
    두근대는,
    미소짓는,
    무뚝뚝한,
    정신없는,
    꿈틀대는,
    고민많은;

    private static final Random PRNG = new Random();

    public static FirstName getRandomFirstName() {
        FirstName[] firstNames = values();
        return firstNames[PRNG.nextInt(firstNames.length)];
    }
}
