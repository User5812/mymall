package com.example.springboot.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Repository
@Data
public class Banner {
    private Integer id;
    private String img;
    private String url;
}
