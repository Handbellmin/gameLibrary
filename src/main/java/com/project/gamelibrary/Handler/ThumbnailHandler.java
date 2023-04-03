package com.project.gamelibrary.Handler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ThumbnailHandler {

    public void createThumbnail() {
        String imagePath = "이미지 파일 경로";
        String thumbnailPath = "썸네일 파일 경로";
        int thumbnailWidth = 100; // 썸네일 이미지의 폭
        int thumbnailHeight = 100; // 썸네일 이미지의 높이
        try {
            File imageFile = new File(imagePath);
            BufferedImage originalImage = ImageIO.read(imageFile);

            // 썸네일 이미지 생성
            BufferedImage thumbnailImage = new BufferedImage(thumbnailWidth, thumbnailHeight, BufferedImage.TYPE_INT_RGB);
            thumbnailImage.createGraphics().drawImage(originalImage.getScaledInstance(thumbnailWidth, thumbnailHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);

            // 썸네일 이미지 저장
            ImageIO.write(thumbnailImage, "jpg", new File(thumbnailPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
