package com.cdtu.support.controller;

import com.cdtu.support.pojo.Road;
import com.cdtu.support.service.RoadService;
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
@RequiresPermissions("road")
public class RoadController {
	@Autowired
	RoadService roadService;

	//显示所有需求==================================
	@GetMapping("/road/list")
	public String list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                   @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
	                   Map<String, Object> model) {
		Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		List<Road> roadList = roadService.queryAll();
		model.put("roadList", roadList);
		model.put("pageNum", pageNum);
		model.put("pageSize", pageSize);
		model.put("pages", page.getPages());
		return "road/list";
	}

	//    进入修改页面======================================
	@GetMapping("/road/toUpRoad")
	public String upRoad(@RequestParam("id") String id,
	                     Map<String, Object> model) {

		Road road = roadService.queryById(id);
		model.put("road", road);

		return "road/upRoad";

	}

	//路线信息修改===================================
	@PutMapping(value = "/upRoad")
	public String updateRoad(@RequestParam("id") String id,
	                         @RequestParam("name") String name,
	                         @RequestParam("content") String content,
	                         @RequestParam("pageNum") Integer pageNum,
	                         @RequestParam("pageSize") Integer pageSize,
	                         RedirectAttributes redirectAttributes) {

		Road road = new Road();
		road.setId(id);
		road.setName(name);
		road.setContent(content);
		Integer integer = roadService.updateRoad(road);
		redirectAttributes.addAttribute("pageNum", pageNum);
		redirectAttributes.addAttribute("pageSize", pageSize);
		if (integer == 1) {
			return "redirect:/road/list";
		}
		return "redirect:/road/list";
	}

	//进入添加页面=========================================
	@GetMapping("/road/toAddRoad")
	public String addRoad() {
		return "road/addRoad";
	}
//删除需求================================================

	@DeleteMapping("/road/{id}")
	public String deleteRoad(@PathVariable("id") String id) {
		roadService.deleteRoad(id);
		return "redirect:/road/list";
	}

	//添加路线============================================
	@PostMapping("/adroad")
	public String addRoad(Road road) {
		roadService.addRoad(road);
		return "redirect:/road/list";
	}


	//按名查找========================================
	@GetMapping("road/queryRoad")
	public String queryByName(@RequestParam("name") String roadName,
	                          Map<String, Object> model) {

		if (StringUtils.isEmpty(roadName.trim())) {
			return "redirect:/road/list";
		}

		List<Road> roadList = roadService.queryByName(roadName.trim());
		if (roadList == null) {
			return "redirect:/road/list";
		}

		model.put("road", roadList.get(0));
		System.out.println(roadList);
		return "road/queryRoad";
	}

}
