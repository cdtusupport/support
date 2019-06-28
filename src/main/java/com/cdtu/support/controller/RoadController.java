package com.cdtu.support.controller;

import com.cdtu.support.pojo.NeedInfo;
import com.cdtu.support.pojo.Road;
import com.cdtu.support.service.NeedService;
import com.cdtu.support.service.RoadService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class RoadController {
    @Autowired
    RoadService roadService;
   //显示所有需求==================================
   @RequestMapping("/roads")
    public String getRoad(Map<String, Object> model) {
        PageHelper.startPage(1, 10);
        List<Road> roadList = roadService.queryAll();
        System.out.println(roadList.size());
        model.put("roads",roadList);
        return "road/road";
    }
//  //按名查找========================================
//    @GetMapping("/road/queryByName")
//    public String queryByName(@RequestParam("roadName") String roadName,
//                              Map<String, Object> model) {
//        if (StringUtils.isEmpty(roadName)) {
//            return "redirect:/roads";
//        }
//        Road road = roadService.queryByName(roadName);
//        if (road == null) {
//            return "redirect:/roads";
//        }
//        model.put("roads",road);
//        return "/road/road";
//    }

//删除需求================================================
    @DeleteMapping("/road/{id}")
    public String deleteRoad(@PathVariable("id") String id) {
        roadService.deleteRoad(id);
        return "redirect:/roads";
    }
}
