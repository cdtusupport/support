package com.cdtu.support.controller;

import com.cdtu.support.pojo.RecruitInfo;
import com.cdtu.support.service.RecruitService;
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
@RequiresPermissions("recruit")
@SuppressWarnings("All")
public class RecruitController {

	@Autowired
	RecruitService recruitService;

	@GetMapping("/recruit/list")
	public String getRecruit(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                         @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
	                         Map<String, Object> model) {

		Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		List<RecruitInfo> recruitInfoList = recruitService.queryAllRecruit();

		model.put("recruitInfoList", recruitInfoList);
		model.put("pageNum", pageNum);
		model.put("pages", page.getPages());
		model.put("pageSize", pageSize);
		return "recruit/list";
	}

	@PostMapping("/recruit")
	public String addRecruit(RecruitInfo recruitInfo) {
		recruitService.addRecruit(recruitInfo);

		return "redirect:/recruit/list";
	}

	@DeleteMapping("/recruit")
	public String deleteRecruit(@RequestParam("id") String id,
	                            @RequestParam("pageSize") Integer pageSize,
	                            @RequestParam("pageNum") Integer pageNum,
	                            RedirectAttributes redirectAttributes) {

		recruitService.deleteRecruit(id);

		redirectAttributes.addAttribute("pageSize", pageSize);
		redirectAttributes.addAttribute("pageNum", pageNum);
		return "redirect:/recruit/list";
	}

	@PutMapping("/recruit")
	public String updateRecruit(RecruitInfo recruitInfo,
	                            @RequestParam("pageSize") Integer pageSize,
	                            @RequestParam("pageNum") Integer pageNum,
	                            RedirectAttributes redirectAttributes) {

		recruitService.updateRecruit(recruitInfo);
		redirectAttributes.addAttribute("pageSize", pageSize);
		redirectAttributes.addAttribute("pageNum", pageNum);

		return "redirect:/recruit/list";
	}

	@GetMapping("/recruit/queryByName")
	public String queryByName(@RequestParam("name") String name,
	                          Map<String, Object> model) {
		if (StringUtils.isEmpty(name.trim())) {
			return "redirect:/recruit/list";
		}

		List<RecruitInfo> recruitInfoList = recruitService.queryByName(name.trim());
		if (recruitInfoList == null) {
			return "redirect:/recruit/list";
		}

		model.put("recruitInfoList", recruitInfoList);
		return "recruit/query";

	}

	@GetMapping("/recruit/toAddPage")
	public String toAddPage() {
		return "recruit/add";
	}


	@GetMapping("/recruit/toUpdatePage")
	public String toUpdatePage(@RequestParam("id") String id,
	                           @RequestParam("pageSize") Integer pageSize,
	                           @RequestParam("pageNum") Integer pageNum,
	                           Map<String, Object> model) {
		RecruitInfo recruitInfo = recruitService.queryById(id);
		model.put("recruitInfo", recruitInfo);
		model.put("pageSize", pageSize);
		model.put("pageNum", pageNum);
		return "recruit/update";
	}

	@GetMapping("/recruit/toFirstPage")
	public String toFirstPage() {
		return "redirect:/recruit/list";
	}

}
