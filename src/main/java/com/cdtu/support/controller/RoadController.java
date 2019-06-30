package com.cdtu.support.controller;

import com.cdtu.support.pojo.NeedInfo;
import com.cdtu.support.pojo.Road;
import com.cdtu.support.pojo.SchoolWithBLOBs;
import com.cdtu.support.service.NeedService;
import com.cdtu.support.service.RoadService;
import com.github.pagehelper.PageHelper;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequiresPermissions("road")
public class RoadController {
    @Autowired
    RoadService roadService;
   //显示所有需求==================================
   @GetMapping("/roads")
    public String getRoad(Map<String, Object> model) {
        PageHelper.startPage(1, 10);
        List<Road> roadList = roadService.queryAll();
        System.out.println(roadList.size());
        model.put("roads",roadList);
        return "road/road";
    }
//    进入修改页面======================================
    @GetMapping("/road/toUpRoad")
    public String upRoad(@RequestParam("id") String id,
                         Map<String, Object> model)
    {
//        /*@RequestParam("pageNum") Integer pageNum,
//        @RequestParam("pageSize") Integer pageSize,*/
//        SchoolWithBLOBs schoolWithBLOBs = schoolService.queryById(id);
        Road road=roadService.queryById(id);
        model.put("road", road);
//        model.put("pageNum", pageNum);
//        model.put("pageSize", pageSize);

        return "road/upRoad";

    }

    //路线信息修改===================================
    @PutMapping(value="/upRoad")
    public String updateRoad (@RequestParam("id") String id ,
                              @RequestParam("name") String name,
                              @RequestParam("content") String content,
                              @RequestParam("pageNum") Integer pageNum,
                              @RequestParam("pageSize") Integer pageSize,
                               RedirectAttributes redirectAttributes)
    {

       Road road=new Road();
       road.setId(id);
       road.setName(name);
       road.setContent(content);
       Integer integer = roadService.updateRoad(road);
       redirectAttributes.addAttribute("pageNum", pageNum);
       redirectAttributes.addAttribute("pageSize", pageSize);
        if (integer ==1) {
            return "redirect:/roads";
        }
        return "redirect:/roads";
    }

    //进入添加页面=========================================
    @GetMapping("/road/addRoad")
    public String addRoad()
    {
        return "road/addRoad";
    }
//删除需求================================================

    @DeleteMapping("/road/{id}")
    public String deleteRoad(@PathVariable("id") String id) {
        roadService.deleteRoad(id);
        return "redirect:/roads";
    }
    //添加路线============================================
    @PostMapping("/adroad")
    public String addRoad(Road road){
       roadService.addRoad(road);
       return "redirect:/roads";
    }

//    @PutMapping("/uproad")
//    public String updateSchool(Road road,
//                               @RequestParam("pageNum") Integer pageNum,
//                               @RequestParam("pageSize") Integer pageSize,
//                               RedirectAttributes redirectAttributes) {
//
//        System.out.println(road);
//        Integer integer = roadService.updateRoad(road);
//        redirectAttributes.addAttribute("pageNum", pageNum);
//        redirectAttributes.addAttribute("pageSize", pageSize);
//        if (integer == 1) {
//            return "redirect:/road/road";
//        }
//        return "redirect:/road/road";
//    }



    //按名查找========================================
    @GetMapping("road/queryRoad")
    public String queryByName(@RequestParam("name") String roadName,
                              Map<String, Object> model) {

        if (StringUtils.isEmpty(roadName.trim())) {
            return "redirect:/road/road";
        }

        List<Road> roadList = roadService.queryByName(roadName.trim());
        if (roadList==null) {
            return "redirect:/road/road";
        }

        model.put("road", roadList.get(0));
        System.out.println(roadList);
        return "road/queryRoad";
    }

}
