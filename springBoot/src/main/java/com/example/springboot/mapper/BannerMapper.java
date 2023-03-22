package com.example.springboot.mapper;

import com.example.springboot.entity.Banner;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {


    List<Banner> list();
}
