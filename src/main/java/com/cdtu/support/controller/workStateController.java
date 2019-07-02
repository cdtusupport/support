package com.cdtu.support.controller;


import com.cdtu.support.pojo.WorkState;
import com.cdtu.support.service.UserService;
import com.cdtu.support.service.workStateService;
import com.cdtu.support.util.SupportUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Controller
@RequiresPermissions("work")
@SuppressWarnings("all")

public class workStateController {

	@Autowired
	workStateService workstateService;
	@Autowired
	UserService userService;

	@GetMapping("/workState/list")
	public String workStateList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                            @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
	                            Map<String, Object> model) {

		Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		List<WorkState> workStateList = workstateService.queryAll();
		for (WorkState workState : workStateList) {
			if (workState.getUserid() == null) {
				continue;
			} else {
				if (userService.queryById(workState.getUserid()).getUsername() == null) {
					continue;
				} else
					workState.setUserid(userService.queryById(workState.getUserid()).getUsername());
			}
		}
		model.put("workStateList", workStateList);
		model.put("pageNum", pageNum);
		model.put("pages", page.getPages());
		model.put("pageSize", pageSize);
		return "workstate/list";
	}

	@PostMapping("/workState")
	public String addworkState(WorkState workState) {
		workstateService.deleteByPrimaryKey(workState.getId());
		System.out.println(workState.getUserid());
		workState.setId(SupportUtil.getUUID());
		workState.setPublictime(SupportUtil.getTime());
		workstateService.addWorkState(workState);
		return "redirect:/workState/list";
	}

	@PostMapping("/updateWorkState")
	public String updateWorkState(WorkState workState) {
		workState.setPublictime(SupportUtil.getTime());
		workstateService.updateWorkState(workState);
		return "redirect:/workState/list";
	}
	@GetMapping("/deleteWorkstate")
	public String deleteWorkstate(String id) {
		workstateService.deleteByPrimaryKey(id);
		return "redirect:/workState/list";
	}

	@GetMapping("/workState/selectByName")
	public String selectByName(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                           @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
	                           @RequestParam("workStateName") String workStateName, Map<String, Object> model) {
		Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		if (StringUtils.isEmpty(workStateName)) {
			return "redirect:/workState/list";

		}
		List<WorkState> workStateList = workstateService.queryByName(workStateName);

		if (workStateList == null) {
			return "redirect:/workState/list";
		}
		for (WorkState workState : workStateList) {
			if (workState.getUserid() == null) {
				continue;
			} else {
				if (userService.queryById(workState.getUserid()).getUsername() == null) {
					continue;
				} else
					workState.setUserid(userService.queryById(workState.getUserid()).getUsername());
			}
		}
		model.put("workStateList", workStateList);
		model.put("pageNum", pageNum);
		model.put("pages", page.getPages());
		model.put("pageSize", pageSize);
		return "workstate/list";
	}



	@GetMapping("/workState/toAddWorkStatePage")
	public String addWorkStatePage(Map<String, Object> model) {
		return "workstate/add";
	}





	@GetMapping("/workState/alterworkStatePage")
	public String alterworkStatePage(String id, Map<String, Object> model) {
		WorkState workState = workstateService.queryById(id);
		model.put("workState", workState);
		return "workstate/update";
	}




}
