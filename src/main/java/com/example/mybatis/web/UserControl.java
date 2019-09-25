package com.example.mybatis.web;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.ModelMap;

import com.example.mybatis.unity.User;
import com.example.mybatis.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserControl extends HttpServlet
{
    @Resource
    private UserService userService;

    @RequestMapping("/")
    public String index()
    {
        return "user/first" ;                                                 //重定向到主页面
    }
    //to登陆
    @RequestMapping(value = "/log")
    public String log()
    {
        return "user/log";
    }
    //登陆
    @RequestMapping(value = "/log1",method = RequestMethod.POST)            //get方法
    public String logl(HttpServletRequest req,@Param(value = "name") String name,@Param("password") String password,@Param("power") int power)
    {
        if(power==1)                    //普通用户
        {
            req.setAttribute("name",name);
            req.setAttribute("password",password);

            return "forward:/log2";
        }else                                           //管理员
        {
            req.setAttribute("name",name);
            req.setAttribute("password",password);

            return "forward:/log3";
        }


    }
    @RequestMapping(value = "/log2")
    @ResponseBody
    public String loggg(@Param(value = "name") String name,@Param("password") String password)
    {
        User user=new User();
        user=userService.findUserName(name);
        if(password.equals(user.getPassword()))
        {
            return "登陆成功";
        }else {
            return "登陆失败 无此用户";
        }
    }
    //管理员登陆界面
    @RequestMapping(value = "/log3")
    public String guanli(@Param(value = "name") String name,@Param("password") String password)
    {
        if(name.equals("aaa")&&password.equals("123456"))
        {
            return "redirect:/list";
        }else
        {
            return "user/badlog";
        }
    }
    //to注册
    @RequestMapping(value = "/seg")                     //get方法
    public String seg()
    {
        return "user/seg";
    }

    //注册写入数据库
    @RequestMapping(value = "/seg1",method = RequestMethod.POST)
    @ResponseBody
    public String segol(@Param(value = "name") String name,@Param(value = "sex") String sex,@Param(value = "age") int age,@Param("password") String password)
    {
        User b=userService.findUserName(name);
        if(b==null)
        {
            return "注册失败";
        }
        int a=userService.adduser(name,sex,age,password);
        if(a==0)                                                    //插入成功返回，数据id号
        {
            return "注册失败";

        }else {
            return "注册成功";
        }

    }
    //查询所有信息
    @RequestMapping(value = "/list")
    public String list(ModelMap map)
    {

        List<User> users=userService.findAll();
        map.addAttribute("users",users);
        return "user/tab";
    }
    //跳转编辑用户页面
    @RequestMapping(value = "/edi")
    public String edit(ModelMap map,@Param(value = "id") int id)
    {
        User user=userService.findUserId(id);
        map.addAttribute("user",user);
//        map.addAttribute("id",id);
        return "user/edit";
    }
    //编辑用户资料
    @RequestMapping(value = "/edit2")
    public String edit1(@Param(value = "name") String name,@Param(value = "sex") String sex,@Param(value = "age") int age,@Param( value = "password") String password,@Param(value = "id") int id)
    {
            if(userService.findUserName(name)==null) //账号无重名
            {
                int b=userService.updateUser(name,sex,age,password,id);
                if(b==1)                            //更新了一条数据信息
                {
                    return "redirect:/edi";
                }else
                {
                    return "user/bad";
                }
            }else                   //有重名 name
            {
                return "user/bad";
            }

    }
    //删除 删除成功返回删除的数据数
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(@Param(value = "id") int id)
    {
        int a=userService.deleteId(id);
        if(a==0)
        {
            return "删除失败";

        }else
        {
            return "删除了"+a+"条数据";
        }
    }


}
