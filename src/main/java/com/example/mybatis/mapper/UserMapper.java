package com.example.mybatis.mapper;

import com.example.mybatis.unity.User;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper
{
    //增
    @Insert("insert into account(name, sex,age,password) values(#{name}, #{sex},#{age},#{password})")
    int add(@Param("name") String name, @Param("sex") String sex,@Param("age") int age,@Param("password") String password);

    //更新
    @Update("update account set name = #{name}, sex=#{sex}, age = #{age}, password=#{password} where id = #{id}")
    int update(@Param("name") String name, @Param("sex") String sex,@Param("age") int age,@Param("password") String password, @Param("id") int  id);

    //根据id删除
    @Delete("delete from account where id = #{id}")
    int deleteid(int id);

    //根据名字删除
    @Delete("delete from account where name = #{name}")
    int deletename(String name);

    //根据id查询数据
    @Select("select id, name as name, age as age, sex as sex, password as password from account where id = #{id}")
    User findAccountId(@Param("id") int id);

    //根据名字账号查询数据
    @Select("select id , name as name, age as age, sex as sex, password as password from account where name= #{name}")
    User findAccountName(@Param("name") String name);

    //查询所有数据
    @Select("select id, name as name, age as age, sex as sex, password as password from account")
    List<User> findAccountList();
}
