package com.example.springboot.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.springboot.controller.dto.LoginDTO;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PasswordRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.AdminMapper;
import com.example.springboot.service.AdminService;
import com.example.springboot.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminmapper;

    private static final String DEFAULT_PASS = "123";
    private static final String PASS_SALT = "tu5812";

    @Override
    public List<Admin> getAllAdmins(){
        return adminmapper.listAdmins();
    }

    @Override
    public PageInfo<Admin> page(BaseRequest request) {
        PageHelper.startPage(request.getPageNum(),request.getPageSize());
        List<Admin> admins = adminmapper.listByCondition(request);
        PageInfo<Admin> userPageInfo = new PageInfo<>(admins);
        return userPageInfo;
    }

    @Override
    public void save(Admin obj) {
        //默认密码 123
        if(StrUtil.isBlank(obj.getPassword())){
            obj.setPassword(DEFAULT_PASS);
        }
        obj.setPassword(securePass(obj.getPassword()));//设置加密
        try {
            adminmapper.save(obj);
        }catch (DuplicateKeyException e){
                log.error("数据插入失败，username：{}",obj.getUsername());
                throw new ServiceException("用户名重复");
        }


    }

    @Override
    public Admin getById(Integer id) {
        Admin user = adminmapper.getById(id);
        return user;
    }

    @Override
    public void update(Admin obj) {
        obj.setUpdatetime(new Date());
        adminmapper.updateById(obj);
    }

    @Override
    public void deleteById(Integer id) {
        adminmapper.deleteById(id);

    }

    @Override
    public LoginDTO login(LoginRequest request) {
            Admin admin = null;
            try{
                admin = adminmapper.getByUsername(request.getUsername());
            }catch (Exception e){
                log.error("根据用户名{}查询出错", request.getUsername());
                throw new ServiceException("用户名错误");
            }
            //判断密码是否合法
            String securePass = securePass(request.getPassword());
            if(!securePass.equals(admin.getPassword())){
                throw new ServiceException("用户名或者密码错误");
            }
            if(admin == null){
                throw new ServiceException("用户名或密码错误");
            }
            if(!admin.isStatus()) {
                throw new ServiceException("当前用户处于禁用状态,请联系管理员");
            }
            LoginDTO loginDTO = new LoginDTO();
            BeanUtils.copyProperties(admin,loginDTO);
            //生成token
            String token = TokenUtils.genToken(String.valueOf(admin.getId()), admin.getPassword());
            loginDTO.setToken(token);
            return loginDTO;
    }

    @Override
    public void changePass(PasswordRequest request) {//新密码需要加密
        request.setNewPass(securePass(request.getNewPass()));
        Integer count = adminmapper.updatePassword(request);
        if(count <= 0){
            throw new ServiceException("修改密码失败");
        }
    }

    //加密密码
    private String securePass(String password){
        String newPass = SecureUtil.md5(password + PASS_SALT);
        return newPass;
    }

}