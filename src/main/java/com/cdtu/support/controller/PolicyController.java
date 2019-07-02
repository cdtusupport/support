package com.cdtu.support.controller;


import com.cdtu.support.pojo.Policy;
import com.cdtu.support.service.PolicyService;
import com.cdtu.support.util.SupportUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.jdbc.Null;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文件上传及进度测试
 */
@Controller
@RequiresPermissions("policy")
@SuppressWarnings("All")
public class PolicyController {

    @Autowired
    PolicyService policyService;
    /**
     * 显示文件上传页
     * @return
     */


    @GetMapping("/uploadPage")
    public String toUploadPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                 Map<String, Object> model) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Policy> policyList = policyService.queryAll();;
        model.put("policyList", policyList);
        model.put("pageNum", pageNum);
        model.put("pages", page.getPages());
        model.put("pageSize", pageSize);
        return "policy/uploadPage";
    }
    @GetMapping("/deletePolicy")
    public String delectPolicy(String name){
        List<Policy> list=policyService.queryByName(name);
        //文件夹中删除文件
        File file =new File(list.get(0).getPath()+"\\"+list.get(0).getName());
        System.gc();
        file.delete();
        policyService.delectByName(name);
        return "redirect:/uploadPage";
    }
    @PostMapping("upload")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile file){
        Map<String, Object> result = new HashMap<>();
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (file != null && !file.isEmpty()){
            try {

                file.transferTo(new File(path+"\\"+file.getOriginalFilename()));
                System.out.println(path);
                System.out.println(path+"\\"+file.getOriginalFilename());
                System.out.println(file.getOriginalFilename());
                result.put("code", 200);
                result.put("msg", "success");
            } catch (IOException e) {
                result.put("code", -1);
                result.put("msg", "文件上传出错，请重新上传！");
                e.printStackTrace();
            }
        } else {
//            result.put("code", -1);
//            result.put("msg", "未获取到有效的文件信息，请重新上传!");
        }
        Policy policy=new Policy();
        policy.setId(SupportUtil.getUUID());
        policy.setName(file.getOriginalFilename());
        policy.setPath(path.toString());
        policy.setPublishtime(SupportUtil.getTime());
        addAlterPolicy(policy);

        return result;
    }
    public void addAlterPolicy(Policy policy)
    {
        //判断是该文件是否在数据库存在该文件的数据
        if (policyService.queryByName(policy.getName()).isEmpty()){
            policyService.addAlterPolicy(policy);
        }else {
            //如果存在就删除改数据库在进行新增
            policyService.delectByName(policy.getName());
            policyService.addAlterPolicy(policy);
        }
        return;
    }

}
