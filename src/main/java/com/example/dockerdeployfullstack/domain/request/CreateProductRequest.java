package com.example.dockerdeployfullstack.domain;

import jakarta.persistence.Column;
import lombok.Data;

@Data

public class CreateProductRequest {


    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String category;

    @Column(length = 1000)
    private String description;

    private String image;
}
