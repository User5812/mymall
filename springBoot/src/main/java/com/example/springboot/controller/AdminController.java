package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PutMapping("/password")
    public Result password(@RequestBody PasswordRequest request){
        adminService.changePass(request);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request){
        System.out.println(request);
        LoginDTO login = adminService.login(request);
        return Result.success(login);
    };

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        adminService.deleteById(id);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Admin obj){
        //System.out.println(obj);
        adminService.update(obj);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Admin obj = adminService.getById(id);
        return Result.success(obj);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Admin obj){
        //System.out.println(obj);
        adminService.save(obj);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list (){
        List<Admin> users = adminService.getAllAdmins();
        return Result.success(users);
    }

    @GetMapping("/page")
    public Result page(AdminPageRequest request){
        Object userPage = adminService.page(request);
        return Result.success(userPage);
    }
}
