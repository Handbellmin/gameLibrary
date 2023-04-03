package com.project.gamelibrary.controller;

import com.project.gamelibrary.Form.ItemForm;
import com.project.gamelibrary.Form.OrderItemForm;
import com.project.gamelibrary.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    public String uploadThumbnail(@RequestParam(name = "file2") List<MultipartFile> image) throws Exception {
        //미리보기 제작
        System.out.println(image);
        String previewPath = itemService.createPreview(image);
        return previewPath;
    }
}
