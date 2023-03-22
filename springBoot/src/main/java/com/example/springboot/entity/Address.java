package com.example.springboot.entity;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Repository
@Data
public class Address {
    private Integer id;
    private String linkUser;
    private String linkAddress;
    private String linkPhone;
    private Integer userId;
}
