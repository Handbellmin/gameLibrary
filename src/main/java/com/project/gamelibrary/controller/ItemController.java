package com.project.gamelibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.gamelibrary.Form.ItemForm;
import com.project.gamelibrary.Form.OrderItemForm;
import com.project.gamelibrary.service.ItemService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.h2.util.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
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
    public String addOrderItem(@RequestParam(name="file") List<MultipartFile> image,
                               @RequestParam(name = "ItemForm") String ItemForm
    ) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        ItemForm itemForm_new = mapper.readValue(ItemForm, ItemForm.class);
        itemForm_new.setCreateDate(LocalDateTime.now());
        itemService.createItem(itemForm_new);

        return "redirect:/items";
    }

    @PostMapping("/thumbnail/upload")
    @ResponseBody
    public void uploadRThumbnail(@RequestParam(name = "file") List<MultipartFile> image,
                                    HttpServletResponse response
    ) throws Exception {
        //미리보기 제작
        /*FileSystemResource fileSystemResource = new FileSystemResource(preview);
        response.setContentType("image/gif");
        HttpHeaders headers = new HttpHeaders();
        Path filePath = null;
        filePath = Paths.get(preview);
        headers.add("Content-Type", Files.probeContentType(filePath));
        System.out.println(filePath);
        return new ResponseEntity<FileSystemResource>(fileSystemResource, headers, HttpStatus.OK);*/
    }
}
