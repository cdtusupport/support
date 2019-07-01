package com.cdtu.support.controller;

import com.cdtu.support.mapper.NeedInfoMapper;
import com.cdtu.support.mapper.SchoolMapper;
import com.cdtu.support.pojo.NeedInfo;
import com.cdtu.support.pojo.NeedJoin;
import com.cdtu.support.pojo.School;
import com.cdtu.support.pojo.SchoolWithBLOBs;
import com.cdtu.support.service.NeedJoinService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/needJoin")
@RequiresPermissions("person")
public class NeedJoinController {

	@Autowired
	NeedJoinService needJoinService;

	@Autowired
	NeedInfoMapper needInfoMapper;

	@Autowired
	SchoolMapper schoolMapper;


	@GetMapping("/toJoinPage")
	public String toJoinPage(Map<String, Object> model) {
		PageHelper.startPage(1, 5);
		List<NeedInfo> needInfoList = needInfoMapper.selectByExample(null);
		PageHelper.startPage(1, 5);
		List<School> schoolList = schoolMapper.selectByExample(null);
		model.put("needInfoList", needInfoList);
		model.put("schoolList", schoolList);

		return "needJoin/join";
	}

	@PostMapping("/join")
	public String join(NeedJoin needJoin) {

		needJoinService.addNeedJoin(needJoin);
		System.out.println(needJoin.toString());

		return "redirect:/school/list";
	}

	@GetMapping("/list")
	public String list(Model model,
	                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
	                   @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize) {

		Page<Object> page = PageHelper.startPage(pageNum, pageSize);
		List<NeedJoin> needJoinList = needJoinService.queryAll();
		model.addAttribute("needJoinList", needJoinList);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pages", page.getPages());
		model.addAttribute("pageSize", pageSize);

		return "needJoin/list";
	}

}
