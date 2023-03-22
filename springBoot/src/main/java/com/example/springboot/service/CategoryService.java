package com.example.springboot.service;

import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.entity.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface CategoryService {

    List<Category> getAllCategorys();

    PageInfo<Category> page(BaseRequest request);

    void save(Category obj);

    Category getById(Integer id);

    void update(Category obj);

    void deleteById(Integer id);


}
