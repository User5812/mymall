package com.example.springboot.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.entity.Category;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.CategoryMapper;
import com.example.springboot.service.CategoryService;
import com.example.springboot.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categorymapper;

    @Override
    public List<Category> getAllCategorys(){
        return categorymapper.listCategorys();
    }

    @Override
    public PageInfo<Category> page(BaseRequest request) {
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<Category> categorys = categorymapper.listByCondition(request);
        PageInfo<Category> userPageInfo = new PageInfo<>(categorys);
        return userPageInfo;
    }

    @Override
    public void save(Category obj) {
        categorymapper.save(obj);
    }

    @Override
    public Category getById(Integer id) {
        Category user = categorymapper.getById(id);
        return user;
    }

    @Override
    public void update(Category obj) {
        categorymapper.updateById(obj);
    }

    @Override
    public void deleteById(Integer id) {
        categorymapper.deleteById(id);

    }



}