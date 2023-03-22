package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Category;
import com.example.springboot.entity.Goods;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface GoodsService {

    List<Goods> getAllGoodss();

    PageInfo<Goods> page(BaseRequest baseRequest);

    void save(Goods goods);

    Goods getById(Integer id);

    void update(Goods goods);

    void deleteById(Integer id);

    List<Category> getOption();

    List<Goods> sales();
}
