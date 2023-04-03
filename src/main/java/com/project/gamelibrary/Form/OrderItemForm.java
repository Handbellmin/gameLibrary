package com.project.gamelibrary.Form;

import com.project.gamelibrary.domain.Files;
import com.project.gamelibrary.domain.Item;
import com.project.gamelibrary.domain.Order;
import lombok.Builder;
import lombok.Data;

import java.awt.*;

@Data
public class OrderItemForm {
    private Order order;
    private Item item;
    private int price;
    private int count;
    private String description;
    private Files Thumbnail;


}
