package com.doonutmate.doonut.challenge.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChallengeTypeTest {

    @Test
    void getDefaultUrl() {
        // given
        var imageUrl = "https://test.url";
        var expected = String.format("https://test.url?w=%s&h=%s", ChallengeType.DEFAULT.getWidth(), ChallengeType.DEFAULT.getHeight());

        // when
        var actual = ChallengeType.getDefaultUrl(imageUrl);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getThumbNailUrl() {
        // given
        var imageUrl = "https://test.url";
        var expected = String.format("https://test.url?w=%s&h=%s", ChallengeType.THUMBNAIL.getWidth(), ChallengeType.THUMBNAIL.getHeight());

        // when
        var actual = ChallengeType.getThumbNailUrl(imageUrl);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
