package com.doonutmate.name;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomNameGeneratorTest {

    @Test
    void generateRandomName() {

        // when
        var randomName = RandomNameGenerator.generateRandomName();

        // then
        assertThat(randomName).isNotNull();
    }
}
