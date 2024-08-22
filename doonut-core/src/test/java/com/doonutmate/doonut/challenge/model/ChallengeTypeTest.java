package com.doonutmate.doonut.challenge.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ChallengeTypeTest {

    @Test
    void getDefaultUrl() {
        // given
        var imageUrl = "https://test.url";
        var expected = "https://test.url?w=390&h=390&swidth=200&sheight=50&smargin=20&sleft=25&fontSize=20&stdDeviation=3&preset=timestamp";

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
