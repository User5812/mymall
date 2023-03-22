package com.example.springboot.mapper;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Category;
import com.example.springboot.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {

    List<Goods> listGoodss();


    List<Goods> listByCondition(BaseRequest baseRequest);

    void save(Goods goods);

    Goods getById(Integer id);

    void updateById(Goods goods);

    void deleteById(Integer id);

    List<Category> getOption();

    List<Goods> sales();
}
