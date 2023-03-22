package com.example.springboot.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Repository
@Data
public class Category {
    private Integer id;
    private String categoryname;
    private Integer no;
}
