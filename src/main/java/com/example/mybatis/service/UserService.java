package com.example.mybatis.service;

import com.example.mybatis.unity.User;
import com.example.mybatis.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserMapper usermapper;
    //增
    public  int addUser(User user)
    {
        return usermapper.add(user.getName(),user.getSex(),user.getAge(),user.getPassword());
    }
    public int adduser(String name,String sex,int age,String password)
    {
        return usermapper.add(name,sex,age,password);
    }

    //删
    public int deleteName(String name)
    {
        return usermapper.deletename(name);
    }

    //删id
    public int deleteId(int id)
    {
        return usermapper.deleteid(id);
    }

    //查询单个用户资料
    public User findUserName(String name)
    {
        return usermapper.findAccountName(name);
    }
    public User findUserId(int id)
    {
        return usermapper.findAccountId(id);
    }
    //查所有用户资料
    public List<User> findAll()
    {
        return usermapper.findAccountList();
    }

    //更新用户资料
    public int updateUser(String name,String sex,int age,String password,Integer id)
    {
        return usermapper.update(name,sex,age,password,id);
    }








}
