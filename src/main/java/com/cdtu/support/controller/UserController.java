package com.cdtu.support.controller;



import com.cdtu.support.pojo.School;
import com.cdtu.support.pojo.SchoolWithBLOBs;
import com.cdtu.support.pojo.User;
import com.cdtu.support.service.SchoolService;
import com.cdtu.support.service.UserService;
import com.github.pagehelper.PageHelper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.cdtu.support.pojo.SchoolWithBLOBs;
import com.cdtu.support.service.SchoolService;
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
        SchoolWithBLOBs schoolWithBLOBs= schoolService.queryByName(user.getSchoolname());
        user.setSchoolid(schoolWithBLOBs.getId());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        user.setCreatetime(df.format(new Date()));
        System.out.println(user.toString());
        userService.addUser(user);
        return "redirect:/UserList";
    }
    @GetMapping("/UserList")
    public String showUserList(Map<String, Object> model){
        PageHelper.startPage(1,10);
        List<User> userList = userService.queryAll();;
        model.put("users", userList);
        return "User/UserList";
    }
    @GetMapping("/User/SelectByName")
    public String SelectByName(@RequestParam("userName") String userName, Map<String, Object> model){
        if(StringUtils.isEmpty(userName)){
            return "redirect:/UserList";
        }
        List<User> userList=userService.queryByName(userName);
        if (userList==null){
            return "redirect:/UserList";
        }
        System.out.println(userList.toString());
        model.put("users", userList);
        return "User/UserList";
    }

    @GetMapping("/User/deleteUser")
    public String deleteUser(String id){
        userService.deleteByPrimaryKey(id);
        return "redirect:/UserList";
    }
//    @GetMapping("/delectPolicy")
//    public String delectPolicy(String name){
//        List<Policy> list=policyService.queryByName(name);
//        //文件夹中删除文件
//        File file =new File(list.get(0).getPath()+"\\"+list.get(0).getName());
//        System.gc();
//        file.delete();
//        policyService.delectByName(name);
//        return "redirect:/uploadPage";
//    }
//    @PostMapping("upload")
//    @ResponseBody
//    public Map<String, Object> upload(MultipartFile file){
////    public String upload(MultipartFile file){
//        Map<String, Object> result = new HashMap<>();
//        File path = null;
//        try {
//            path = new File(ResourceUtils.getURL("classpath:").getPath());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        if (file != null && !file.isEmpty()){
//            try {
//
//                file.transferTo(new File(path+"\\"+file.getOriginalFilename()));
//                System.out.println(path);
//                System.out.println(path+"\\"+file.getOriginalFilename());
//                System.out.println(file.getOriginalFilename());
//                result.put("code", 200);
//                result.put("msg", "success");
//            } catch (IOException e) {
//                result.put("code", -1);
//                result.put("msg", "文件上传出错，请重新上传！");
//                e.printStackTrace();
//            }
//        } else {
////            result.put("code", -1);
////            result.put("msg", "未获取到有效的文件信息，请重新上传!");
//        }
//        Policy policy=new Policy();
//        policy.setId(UUID.randomUUID().toString().substring(0,5));
//        policy.setName(file.getOriginalFilename());
//        policy.setPath(path.toString());
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        policy.setPublishtime(df.format(new Date()));
//        addAlterPolicy(policy);
//
//        return result;
////        return  "redirect:/uploadPage";
//    }
//    public void addAlterPolicy(Policy policy)
//    {
//        //判断是该文件是否在数据库存在该文件的数据
//        if (policyService.queryByName(policy.getName()).isEmpty()){
//            policyService.addAlterPolicy(policy);
//        }else {
//            //如果存在就删除改数据库在进行新增
//            policyService.delectByName(policy.getName());
//            policyService.addAlterPolicy(policy);
//        }
//        return;
//    }



}
