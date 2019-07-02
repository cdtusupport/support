package com.cdtu.support.controller;

import com.cdtu.support.pojo.NeedInfo;
import com.cdtu.support.service.NeedInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiresPermissions("need")
public class NeedInfoController {
	@Autowired
	NeedInfoService needInfoService;

	//显示所有需求
	@GetMapping("/need/list")
	public String getNeed(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                      @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
	                      Map<String, Object> model) {

		Page<Object> page = PageHelper.startPage(pageNum, pageSize);

		List<NeedInfo> needList = needInfoService.queryAll();
		model.put("needList", needList);
		model.put("pageNum", pageNum);
		model.put("pages", page.getPages());
		model.put("pageSize", pageSize);

		return "need/list";
	}

	//添加需求
	@PostMapping("/adneed")
	public String addNeed(NeedInfo need) {
		needInfoService.addNeed(need);
		return "redirect:/need/list";
	}

	//删除需求
	@DeleteMapping("/need/{id}")
	public String deleteNeed(@PathVariable("id") String id) {
		needInfoService.deleteNeed(id);
		return "redirect:/need/list";
	}

	//需求信息修改
	@PutMapping("/upNeed")
	public String updateNeed(
			@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("content") String content) {
		NeedInfo needInfo = new NeedInfo();
		needInfo.setId(id);
		needInfo.setName(name);
		needInfo.setInfo(content);
		Integer integer = needInfoService.updateNeed(needInfo);

		if (integer == 1) {
			return "redirect:/need/list";
		}
		return "redirect:/need/list";
	}

	//按名字查找需求
	@GetMapping("/need/queryNeed")
	public String queryByName(@RequestParam("name") String needName,
	                          Map<String, Object> model) {

		if (StringUtils.isEmpty(needName.trim())) {
			return "redirect:/need/list";
		}

		List<NeedInfo> needInfoList = needInfoService.queryByName(needName.trim());

		if (needInfoList == null) {
			return "redirect:/need/list";
		}
		model.put("need", needInfoList.get(0));
		return "need/queryNeed";

	}

	//进入添加页面
	@GetMapping("/need/toAddNeedPage")
	public String addNeed() {
		return "need/addNeed";
	}

	//进入修改页面
	@GetMapping("/need/toUpNeed")
	public String upNeed(@RequestParam("id") String id,
	                     Map<String, Object> model) {

		NeedInfo needInfo = needInfoService.queryById(id);
		model.put("need", needInfo);
		return "need/upNeed";

	}

}