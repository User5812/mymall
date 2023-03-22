package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@Data
public class Order {
    private Integer id;
    private String orderNo;
    private Double totalPrice;
    private Integer userId;
    private String linkUser;
    private String linkPhone;
    private String linkAddress;
    private String state;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate createTime;
}
