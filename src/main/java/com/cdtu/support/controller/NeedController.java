package com.cdtu.support.controller;

import com.cdtu.support.pojo.NeedInfo;
import com.cdtu.support.service.NeedService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class NeedController {
    @Autowired
    NeedService needService;
   //显示所有需求==================================
    @RequestMapping("/needs")
    public String getNeed(Map<String, Object> model) {
        PageHelper.startPage(1, 10);
        List<NeedInfo> needList = needService.queryAll();
        System.out.println(needList.size());
        model.put("needs",needList);
        return "need/need";
    }
  //按名查找========================================
 /*   @GetMapping("/need/queryByName")
    public String queryByName(@RequestParam("needName") String needName,
                              Map<String, Object> model) {
        if (StringUtils.isEmpty(needName)) {
            return "redirect:/needs";
        }
        NeedInfo needInfo = needService.queryByName(needName);
        if (needInfo == null) {
            return "redirect:/needs";
        }
        model.put("needs", needInfo);
        return "need/need";
    }*/
//删除需求================================================
    @DeleteMapping("/need/{id}")
    public String deleteNeed(@PathVariable("id") String id) {
        needService.deleteNeed(id);
        return "redirect:/needs";
    }
}
