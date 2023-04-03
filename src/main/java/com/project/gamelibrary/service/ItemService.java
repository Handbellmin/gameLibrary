package com.project.gamelibrary.service;

import com.project.gamelibrary.Handler.FileHandler;
import com.project.gamelibrary.domain.Files;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
    private final FileHandler fileHandler;
    public String createPreview(List<MultipartFile> images) throws Exception {
        List<Files> image = fileHandler.parseFileInfo(images);
        return image.get(0).getFilePath();
    }
}
