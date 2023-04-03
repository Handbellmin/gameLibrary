package com.project.gamelibrary.controller;

import com.project.gamelibrary.Form.ItemForm;
import com.project.gamelibrary.Form.OrderItemForm;
import com.project.gamelibrary.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    @GetMapping("/item/add")
    public String setOrderItemForm(Model model) {
        model.addAttribute("orderItemForm" , new ItemForm());
        return "/item/ItemForm";
    }
    @PostMapping("/item/add")
    public String addOrderItem() {
        return "";
    }

    @PostMapping("/thumbnail/upload")
    @ResponseBody
    public ResponseEntity<Resource> uploadThumbnail(@RequestParam(name = "file") List<MultipartFile> image) throws Exception {
        //미리보기 제작

        String previewPath = itemService.createPreview(image);
        Resource resource = (Resource) new FileSystemResource(previewPath);
        HttpHeaders headers = new HttpHeaders();
        Path filePath = null;
        filePath = Paths.get(previewPath);
        headers.add("Content-Type", Files.probeContentType(filePath));
        return new ResponseEntity<Resource>(resource,headers, HttpStatus.OK);
    }
}
