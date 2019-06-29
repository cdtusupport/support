package com.cdtu.support.controller;


import com.cdtu.support.pojo.WorkState;
import com.cdtu.support.service.workStateService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传及进度测试
 */
@Controller
public class workStateController {

    @Autowired
    workStateService workstateService;

    /**
     * 显示文件上传页
     *
     * @return
     */


    @GetMapping("/workState/addWorkStatePage")
    public String addWorkStatePage(Map<String, Object> model) {
        return "workstate/addWorkState";
    }


    @GetMapping("/workState/selectByName")
    public String selectByName(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                               @RequestParam("workStateName") String workStateName, Map<String, Object> model) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        if (StringUtils.isEmpty(workStateName)) {
            return "redirect:/workState/workStateList";

        }
        List<WorkState> workStateList = workstateService.queryByName(workStateName);

        if (workStateList == null) {
            return "redirect:/workState/workStateList";
        }
        System.out.println(workStateList.get(0).getName());
        model.put("workStateList", workStateList);
        model.put("currentPage", pageNum);
        model.put("pages", page.getPages());
        model.put("pageSize", pageSize);
        return "workstate/workStateList";
    }

    @PostMapping("/workState/addworkState")
    public String addworkState(WorkState workState) {
        workstateService.deleteByPrimaryKey(workState.getId());
        workState.setId(UUID.randomUUID().toString().substring(0,5));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        workState.setPublictime(df.format(new Date()));
        workstateService.addWorkState(workState);
        System.out.println(workState.toString());
        System.out.println("到达2");
        return "redirect:/workState/workStateList";
    }

    @GetMapping("/workState/alterworkStatePage")
    public String alterworkStatePage(String id,Map<String, Object> model) {
        WorkState workState= workstateService.queryById(id);
        model.put("workState",workState);
        return "workstate/alterWorkState";
    }

    @GetMapping("/workState/deleteWorkstate")
    public String deleteWorkstate(String id) {
        workstateService.deleteByPrimaryKey(id);
        return "redirect:/workState/workStateList";
    }

    @GetMapping("/workState/workStateList")
    public String workStateList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                Map<String, Object> model) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<WorkState> workStateList = workstateService.queryAll();
        ;
        model.put("workStateList", workStateList);
        model.put("currentPage", pageNum);
        model.put("pages", page.getPages());
        model.put("pageSize", pageSize);
        return "workstate/workStateList";
    }
}
