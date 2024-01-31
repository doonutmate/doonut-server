package com.doonutmate.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class ImageMetaSupporterTest {

    @Test
    @DisplayName("이미지의 메타 정보를 추출한다.")
    void extract() {
        try {
            // given
            var file = new File(new File("").getAbsolutePath() + "/src/test/resources/fixtures/doonut.png");
            var multipartFile = new MockMultipartFile("test.xlsx", new FileInputStream(file));

            // when
            ImageMeta actual = ImageMetaSupporter.extract(multipartFile);

            // then
            assertThat(actual).isEqualTo(new ImageMeta(400, 400, 43));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}