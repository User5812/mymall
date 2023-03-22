package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String nickName;
    private String email;
    private String phone;
    private String address;
    private Integer age;
    private Integer account;
    private String identify;
}
