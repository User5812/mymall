package com.example.springboot.service;

import com.example.springboot.controller.options.UserOptions;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Order;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface OrderService {

    List<Order> getAllOrders();

    PageInfo<Order> page(BaseRequest request);

    void save(Order obj);

    Order getById(Integer id);

    void update(Order obj);

    void deleteById(Integer id);


}
