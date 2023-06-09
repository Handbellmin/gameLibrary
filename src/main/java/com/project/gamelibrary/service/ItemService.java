package com.project.gamelibrary.service;

import com.project.gamelibrary.Form.ItemForm;
import com.project.gamelibrary.Handler.FileHandler;
import com.project.gamelibrary.domain.Files;
import com.project.gamelibrary.domain.Item;
import com.project.gamelibrary.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {

    final private ItemRepository itemRepository;
    private final FileHandler fileHandler;
    public String createPreview(List<MultipartFile> images) throws Exception {
        List<Files> image = fileHandler.parseFileInfo(images,true);

        return image.get(0).getUploadDir()+ File.separator+image.get(0).getSavedFileName();
    }
    public void createItem(ItemForm itemForm) throws Exception {
        Item item = itemForm.toEntity();
        itemRepository.save(item);
    }
}
