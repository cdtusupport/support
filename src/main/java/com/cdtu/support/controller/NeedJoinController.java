package com.cdtu.support.controller;

import com.cdtu.support.mapper.NeedInfoMapper;
import com.cdtu.support.mapper.SchoolMapper;
import com.cdtu.support.pojo.NeedInfo;
import com.cdtu.support.pojo.NeedJoin;
import com.cdtu.support.pojo.School;
import com.cdtu.support.pojo.SchoolWithBLOBs;
import com.cdtu.support.service.NeedJoinService;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		PageHelper.startPage(1,5);
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
}
