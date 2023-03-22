package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.GoodsPageRequest;
import com.example.springboot.entity.Category;
import com.example.springboot.entity.Goods;
import com.example.springboot.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;


    @GetMapping("/sales")
    public Result sales(){
        List<Goods> obj = goodsService.sales();
        return Result.success(obj);
    }
    @GetMapping("/option")
    public Result option(){
        List<Category> categories = goodsService.getOption();
        return Result.success(categories);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        goodsService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Goods goods){
        goodsService.update(goods);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Goods goods = goodsService.getById(id);
        return Result.success(goods);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Goods goods){
        goodsService.save(goods);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list (){
        List<Goods> goodss = goodsService.getAllGoodss();
        return Result.success(goodss);
    }

    @GetMapping("/page")
    public Result page(GoodsPageRequest goodsPageRequest){
        Object goodsPage = goodsService.page(goodsPageRequest);
        return Result.success(goodsPage);
    }
}
