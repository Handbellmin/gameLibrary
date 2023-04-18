package com.project.gamelibrary.Form;

import com.project.gamelibrary.domain.Category;
import com.project.gamelibrary.domain.Files;
import com.project.gamelibrary.domain.Item;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemForm {
    private Category category;
    private String name;
    private int price;
    private int stockQuantity;
    private LocalDateTime createDate;

    private Files thumbnail;

    public Item toEntity() {
        return new Item.Builder(category, name, price, stockQuantity)
                .build();
    }
}
