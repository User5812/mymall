package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Banner;
import com.example.springboot.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/banner")
public class BannerController {

    @Autowired
    BannerService bannerService;

    @GetMapping("/list")
    public Result list(){
        List<Banner> obj = bannerService.list();
        return Result.success(obj);
    }
}
