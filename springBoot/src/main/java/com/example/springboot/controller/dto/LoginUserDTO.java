package com.example.springboot.controller.dto;


import lombok.Data;

@Data
public class LoginUserDTO {
    private Integer id;
    private String username;
    private String nickName;
    private String phone;
    private String email;
    private String token;
    private String identify;
}
