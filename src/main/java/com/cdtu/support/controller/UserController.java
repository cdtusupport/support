package com.cdtu.support.controller;



import com.cdtu.support.pojo.School;
import com.cdtu.support.pojo.SchoolWithBLOBs;
import com.cdtu.support.pojo.User;
import com.cdtu.support.service.SchoolService;
import com.cdtu.support.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文件上传及进度测试
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    SchoolService schoolService;
    /**
     * 显示文件上传页
     * @return
     */


    @GetMapping("/User/addUserPage")
    public String addUserPage(Map<String, Object> model){
        List<SchoolWithBLOBs> schoolList = schoolService.queryAll();
        System.out.println(schoolList.size());
        model.put("schools", schoolList);
        return "User/addUserPage";
    }
    @GetMapping("/User/alterUserPage")
    public String alterUserPage(String id, Map<String, Object> model){
        List<SchoolWithBLOBs> schoolList = schoolService.queryAll();
        User user=userService.queryById(id);
        model.put("user",user);
        model.put("schools", schoolList);
        System.out.println("ceshi");
        return "User/alterUserPage";
    }
    @PostMapping(value = "/User/addUser")
    public String addUser(User user){
        userService.deleteByPrimaryKey(user.getId());
        user.setId(UUID.randomUUID().toString().substring(0,5));
        List<SchoolWithBLOBs> schoolWithBLOBs= schoolService.queryByName(user.getSchoolname());
        user.setSchoolid(schoolWithBLOBs.get(0).getId());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        user.setCreatetime(df.format(new Date()));
        System.out.println(user.toString());
        userService.addUser(user);
        return "redirect:/UserList";
    }
    @GetMapping("/UserList")
    public String showUserList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                               Map<String, Object> model) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userService.queryAll();;
        model.put("users", userList);
        model.put("currentPage", pageNum);
        model.put("pages", page.getPages());
        model.put("pageSize", pageSize);
        return "User/UserList";
    }
    @GetMapping("/User/SelectByName")
    public String SelectByName(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
            @RequestParam("userName") String userName, Map<String, Object> model){
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        if(StringUtils.isEmpty(userName)){
            return "redirect:/UserList";
        }
        List<User> userList=userService.queryByName(userName);
        if (userList==null){
            return "redirect:/UserList";
        }
        System.out.println(userList.toString());
        model.put("users", userList);
        model.put("currentPage", pageNum);
        model.put("pages", page.getPages());
        model.put("pageSize", pageSize);
        return "User/UserList";
    }

    @GetMapping("/User/deleteUser")
    public String deleteUser(String id){
        userService.deleteByPrimaryKey(id);
        return "redirect:/UserList";
    }

}
