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

    @Test
    @DisplayName("이미지의 확장자를 가져온다.")
    void getExtension() {
        // given
        var fileName = "CAP_B3E3DE45-7016-4BF4-A39C-BEB9A40ACC15_image2F810FE9-C6D6-4EAF-97D5-6553D265537E_cropped.jpg";

        // when
        var actual = ImageMetaSupporter.getExtension(fileName);

        // then
        assertThat(actual).isEqualTo("jpg");
    }
}
