package com.example.springboot.service.impl;

import com.example.springboot.controller.options.UserOptions;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Order;
import com.example.springboot.mapper.OrderMapper;
import com.example.springboot.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper ordermapper;

    @Override
    public List<Order> getAllOrders(){
        return ordermapper.listOrders();
    }

    @Override
    public PageInfo<Order> page(BaseRequest request) {
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<Order> orders = ordermapper.listByCondition(request);
        PageInfo<Order> OrderPageInfo = new PageInfo<>(orders);
        return OrderPageInfo;
    }

    @Override
    public void save(Order obj) {
        ordermapper.save(obj);
    }

    @Override
    public Order getById(Integer id) {
        Order order = ordermapper.getById(id);
        return order;
    }

    @Override
    public void update(Order obj) {
        ordermapper.updateById(obj);
    }

    @Override
    public void deleteById(Integer id) {
        ordermapper.deleteById(id);

    }
    

}