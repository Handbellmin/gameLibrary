package com.project.gamelibrary.Handler;

import com.project.gamelibrary.Form.FileForm;
import com.project.gamelibrary.domain.Files;
import com.project.gamelibrary.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FileHandler {
    @Value("${upload.path}")
    private String uploadDir;
    private final FileService fileService;

    public List<Files> parseFileInfo(
            List<MultipartFile> multipartFiles, Boolean thumbnailYn) throws Exception {
        // 반환할 파일 리스트
        List<Files> fileList = new ArrayList<>();
        // 전달된 파일이 null이 아닌경우
        if(!CollectionUtils.isEmpty(multipartFiles)) {
            // 파일명을 업로드한 날짜로 치환
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dataTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String current_date = now.format(dataTimeFormatter);

            //프로젝트 디렉터리 내의 저장을 위한 절대 경로 설정
            // 경로 구분자 separator로 리눅스, 윈도우 구별
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
            // 파일 세부 경로 지정
            String path = "files" + File.separator + current_date;
            File file = new File(path);

            //디렉터리가 존재하지 않을 경우 생성
            if(!file.exists()) {
                boolean wasSuccessful = file.mkdirs();
                if (!wasSuccessful) System.out.println("files: directory is not make");
            }
            for (MultipartFile multipartFile : multipartFiles) {
                String originalName =  multipartFile.getOriginalFilename();
                String originalFileExtension = originalName.substring(originalName.lastIndexOf("."));
                String conetentType = multipartFile.getContentType();

                if(ObjectUtils.isEmpty(conetentType)) {
                    break;
                } else {
                    if (conetentType.contains("jsp") || conetentType.contains("xml"))
                        break;
                }
                String savedFileName = System.nanoTime() + originalFileExtension;
                FileForm fileForm = FileForm.builder()
                        .originFileName(originalName)
                        .savedFileName(savedFileName)
                        .uploadDir(absolutePath+path)
                        .extension(originalFileExtension)
                        .fileSize(multipartFile.getSize())
                        .contentType(conetentType)
                        .regDate(LocalDateTime.now())
                        .build();
                Files files = fileForm.toEntity();
                fileList.add(files);
                if(!thumbnailYn) {
                    file = new File(absolutePath + path + File.separator + savedFileName);
                }else {
                    int thumbnailWidth = 100;
                    int thumbnailHeight = 100;
                    BufferedImage originalImage = ImageIO.read(file);

                    BufferedImage thumbnailImage = new BufferedImage(thumbnailWidth,thumbnailHeight,BufferedImage.TYPE_INT_RGB);
                    thumbnailImage.createGraphics().drawImage(originalImage.getScaledInstance(thumbnailWidth, thumbnailHeight, Image.SCALE_SMOOTH),0,0,null);
                    ImageIO.write(thumbnailImage, "jpg", new File(path));
                }
                multipartFile.transferTo(file);

                // 파일 권한 설정(쓰기, 읽기)
                file.setWritable(true);
                file.setReadable(true);
            }
        }
        return fileList;
    }


}
