package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.CategoryPageRequest;
import com.example.springboot.entity.Category;
import com.example.springboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;




    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        categoryService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Category obj){
        //System.out.println(obj);
        categoryService.update(obj);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Category obj = categoryService.getById(id);
        return Result.success(obj);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Category obj){
        //System.out.println(obj);
        categoryService.save(obj);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list (){
        List<Category> users = categoryService.getAllCategorys();
        return Result.success(users);
    }

    @GetMapping("/page")
    public Result page(CategoryPageRequest request){
        Object userPage = categoryService.page(request);
        return Result.success(userPage);
    }
}
