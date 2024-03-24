package com.doonutmate.name;

public class RandomNameGenerator {

    public static String generateRandomName() {
        var firstName = FirstName.getRandomFirstName();
        var lastName = LastName.getRandomLastName();

        return String.format("%s %s", firstName.getText(), lastName.getText());
    }
}
