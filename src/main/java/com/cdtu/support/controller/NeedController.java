package com.cdtu.support.controller;

import com.cdtu.support.pojo.NeedInfo;
import com.cdtu.support.pojo.Road;
import com.cdtu.support.pojo.SchoolWithBLOBs;
import com.cdtu.support.service.NeedService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;
import java.util.Map;

@Controller
@RequiresPermissions("need")
public class NeedController {
    @Autowired
    NeedService needService;

    //显示所有需求==================================
    @GetMapping("/needs")
    public String getNeed(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                          Map<String, Object> model) {

        Page<Object> page = PageHelper.startPage(pageNum, pageSize);

        List<NeedInfo> needList = needService.queryAll();
        System.out.println(needList.size());
        model.put("needs", needList);

        model.put("currentPage",pageNum);
        model.put("pages", page.getPages());
        model.put("pageSize", pageSize);

        return "need/need";
    }

    //按名字查找需求===========================================================
    @GetMapping("/need/queryNeed")
    public String queryByName(@RequestParam("name") String needName,
                              Map<String, Object> model) {

        if (StringUtils.isEmpty(needName.trim())) {
            return "redirect:/need/need";
        }
        ///=============================================================
        List<NeedInfo> needInfoList = needService.queryByName(needName.trim());

        if (needInfoList == null) {
            return "redirect:/need/need";
        }
        model.put("need", needInfoList.get(0));
        System.out.println(needInfoList);
        return "need/queryNeed";

    }


    //进入添加页面=================================
    @GetMapping("/need/addNeed")
    public String addNeed() {
        return "need/addNeed";
    }

    //删除需求================================================
    @DeleteMapping("/need/{id}")
    public String deleteNeed(@PathVariable("id") String id) {
        needService.deleteNeed(id);
        return "redirect:/needs";
    }
    //添加需求====================================================
    @PostMapping("/adneed")
    public String addNeed(NeedInfo need) {
        needService.addNeed(need);
        return "redirect:/needs";
    }


    //进入修改页面======================================
    @GetMapping("/need/toUpNeed")
    public String upNeed(@RequestParam("id") String id,
                         Map<String,Object> model)
    {
//        /*@RequestParam("pageNum") Integer pageNum,
//        @RequestParam("pageSize") Integer pageSize,*/
//        SchoolWithBLOBs schoolWithBLOBs = schoolService.queryById(id);
          NeedInfo needInfo=needService.queryById(id);
          model.put("need",needInfo);
//        model.put("pageNum", pageNum);
//        model.put("pageSize", pageSize);
        return "need/upNeed";

    }
    //需求信息修改===================================
    @PutMapping("/upNeed")
    public String updateNeed (/*NeedInfo needInfo*/
                              @RequestParam("id") String id,
                              @RequestParam("name") String name,
                              @RequestParam("content") String content
//                               @RequestParam("pageNum") Integer pageNum,
//                               @RequestParam("pageSize") Integer pageSize,
            /*RedirectAttributes redirectAttributes*/) {
        NeedInfo needInfo=new NeedInfo();
        needInfo.setId(id);
        needInfo.setName(name);
        needInfo.setInfo(content);
        Integer integer = needService.updateNeed(needInfo);
//        redirectAttributes.addAttribute("pageNum", pageNum);
//        redirectAttributes.addAttribute("pageSize", pageSize);
        System.out.println("更新方法已进入");

        if (integer ==1) {
           return "redirect:/needs";
        }
        return "redirect:/needs";
    }


}