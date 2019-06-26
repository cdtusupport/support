package com.cdtu.support.controller;

import com.cdtu.support.pojo.SchoolWithBLOBs;
import com.cdtu.support.service.SchoolService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class SchoolController {
	@Autowired
	SchoolService schoolService;

	@GetMapping("/schools")
	public String getSchool(Map<String, Object> model){

		PageHelper.startPage(1,10);

		List<SchoolWithBLOBs> schoolList = schoolService.queryAll();
		System.out.println(schoolList.size());

		model.put("schools", schoolList);

		return "school/list";
	}

	@GetMapping("/school/queryByName")
	public String queryByName(@RequestParam("schoolName") String schoolName,
	                          Map<String, Object> model){

		if (StringUtils.isEmpty(schoolName)){
			return "redirect:/schools";
		}

		SchoolWithBLOBs schoolWithBLOBs = schoolService.queryByName(schoolName);

		if (schoolWithBLOBs == null){
			return "redirect:/schools";
		}

		model.put("schools", schoolWithBLOBs);

		return "school/list";
	}

	@DeleteMapping("/school/{id}")
	public String deleteSchool(@PathVariable("id") String id){
		schoolService.deleteSchool(id);

		return "redirect:/schools";
	}


	@RequestMapping("/add")
	public String addSchool(){

		return "school";
	}

	@RequestMapping("update")
	public String updateSchool(){

		return "school";
	}
}
