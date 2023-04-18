package com.project.gamelibrary.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.yaml.snakeyaml.scanner.ScannerImpl;

import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;

    private int price;
    private int stockQuantity;

    private LocalDateTime createDate;
    @OneToOne
    @JoinColumn(name="files_id")
    private Files thumbnail;

    public static class Builder {
        private final Category category;
        private final String name;
        private final int price;
        private final int stockQuantity;

        public Builder(Category category, String name, int price, int stockQuantity) {
            this.category = category;
            this.name = name;
            this.price = price;
            this.stockQuantity = stockQuantity;
        }
        public Item build() { return new Item(this);}
    }
    protected Item() {}

    private Item(Builder builder) {
        this.category = builder.category;
        this.name = builder.name;
        this.price = builder.price;
        this.stockQuantity = builder.stockQuantity;
    }
}
