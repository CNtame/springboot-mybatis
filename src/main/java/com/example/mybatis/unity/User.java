package com.example.mybatis.unity;

public class User
{
    private int id;
    private String name;
    private String sex;
    private int age;
    private String password;

    public void setId(int a)
    {
        id=a;
    }
    public int getId()
    {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public User()
    {

    }
}
