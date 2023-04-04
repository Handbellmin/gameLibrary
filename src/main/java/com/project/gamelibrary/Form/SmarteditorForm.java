package com.project.gamelibrary.Form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SmarteditorForm {
    private MultipartFile fileData;
    private String callback;
    private String callback_func;
}
