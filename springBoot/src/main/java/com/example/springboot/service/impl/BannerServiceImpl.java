package com.example.springboot.service.impl;

import com.example.springboot.entity.Banner;
import com.example.springboot.mapper.BannerMapper;
import com.example.springboot.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    BannerMapper bannerMapper;


    @Override
    @GetMapping("/list")
    public List<Banner> list() {
        return bannerMapper.list();
    }
}
