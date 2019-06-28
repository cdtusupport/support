package com.cdtu.support.controller;

import com.cdtu.support.pojo.SchoolWithBLOBs;
import com.cdtu.support.service.SchoolService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller

public class SchoolController {
	@Autowired
	SchoolService schoolService;

	@GetMapping("/school/list")
	public String getSchool(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                        @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
	                        Map<String, Object> model) {

		Page<Object> page = PageHelper.startPage(pageNum, pageSize);

		List<SchoolWithBLOBs> schoolList = schoolService.queryAll();

		System.out.println("pageNum = " + pageNum);
		model.put("schools", schoolList);
		model.put("currentPage", pageNum);
		model.put("pages", page.getPages());
		model.put("pageSize", pageSize);

		return "school/list";
	}

	@GetMapping("school/queryByName")
	public String queryByName(@RequestParam("schoolName") String schoolName,
	                          Map<String, Object> model) {

		if (StringUtils.isEmpty(schoolName.trim())) {
			return "redirect:/school/list";
		}

		List<SchoolWithBLOBs> schoolList = schoolService.queryByName(schoolName.trim());
		System.out.println(schoolList.get(0).getCity());

		if (schoolList == null) {
			return "redirect:/school/list";
		}

		model.put("school", schoolList.get(0));
		System.out.println(schoolList);

		return "school/query";
	}

	@DeleteMapping("/school")
	public String deleteSchool(@RequestParam("id") String id,
	                           @RequestParam("pageSize") Integer pageSize,
	                           @RequestParam("pageNum") Integer pageNum,
	                           RedirectAttributes redirectAttributes) {

		schoolService.deleteSchool(id);
		redirectAttributes.addAttribute("pageNum", pageNum);
		redirectAttributes.addAttribute("pageSize", pageSize);
		System.out.println("pageNum=" + pageNum);
		return "redirect:/school/list";
	}


	@PostMapping("/school")
	public String addSchool(SchoolWithBLOBs schoolWithBLOBs) {

		schoolService.addSchool(schoolWithBLOBs);

		return "redirect:/school/list";
	}

	@GetMapping("/school/toAddPage")
	public String toAddPage() {

		return "school/add";

	}

	@PutMapping("/school")
	public String updateSchool(SchoolWithBLOBs schoolWithBLOBs,
	                           @RequestParam("pageNum") Integer pageNum,
	                           @RequestParam("pageSize") Integer pageSize,
	                           RedirectAttributes redirectAttributes) {

		System.out.println(schoolWithBLOBs);

		Integer integer = schoolService.updateSchool(schoolWithBLOBs);
		redirectAttributes.addAttribute("pageNum", pageNum);
		redirectAttributes.addAttribute("pageSize", pageSize);
		if (integer == 1) {
			return "redirect:/school/list";
		}

		return "redirect:/school/list";
	}

	// @GetMapping("/school/toUpdatePage/{id}")
	@GetMapping("/school/toUpdatePage")
	public String toUpdatePage(@RequestParam("id") String id,
	                           @RequestParam("pageNum") Integer pageNum,
	                           @RequestParam("pageSize") Integer pageSize,
	                           Map<String, Object> model) {

		SchoolWithBLOBs schoolWithBLOBs = schoolService.queryById(id);
		model.put("school", schoolWithBLOBs);
		model.put("pageNum", pageNum);
		model.put("pageSize", pageSize);

		return "school/update";
	}

	@GetMapping("/school/toFirstPage")
	public String toFirstPage() {
		return "redirect:/school/list";
	}
}
