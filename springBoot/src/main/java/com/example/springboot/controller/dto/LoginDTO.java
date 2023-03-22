package com.example.springboot.controller.dto;


import lombok.Data;

import java.util.Date;

@Data
public class LoginDTO {

    private Integer id;
    private String username;
    private String phone;
    private String email;
    private String token;
}
