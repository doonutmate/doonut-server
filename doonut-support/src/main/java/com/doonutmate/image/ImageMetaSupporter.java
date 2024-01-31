package com.doonutmate.image;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageMetaSupporter {

    public static ImageMeta extract(MultipartFile file) throws IOException {
        try {
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());

            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            long capacity = file.getSize() / 1024;

            return new ImageMeta(width, height, capacity);
        } catch (Exception e) {
            throw e;
        }
    }
}
