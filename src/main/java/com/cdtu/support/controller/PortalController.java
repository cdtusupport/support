package com.cdtu.support.controller;

import com.cdtu.support.pojo.*;
import com.cdtu.support.service.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SuppressWarnings("All")
public class PortalController {
    //用户
    @Autowired
    UserService userService;
    //需求
    @Autowired
    NeedInfoService needService;
    //政策
    @Autowired
    PolicyService policyService;
    //招聘信息
    @Autowired
    RecruitService recruitService;
    //支援路线
    @Autowired
    RoadService roadService;
    //学校
    @Autowired
    SchoolService schoolService;
    //工作动态
    @Autowired
    workStateService workstateService;
    //请求跳转到详细页面
    @GetMapping("/indexShowPage")
    public String indexShowPage(Map<String, Object> model) {
        List<NeedInfo> needList = needService.queryAll();
        List<RecruitInfo> recruitInfoList = recruitService.queryAllRecruit();
        if(needList.size()<6){
            model.put("needList",needList);
        }else {
            List<NeedInfo> needList1 = new ArrayList();
            for (int i=0;i<6;i++) {
                needList1.add(needList.get(i));
            }
            model.put("needList",needList1);
        }

        if(recruitInfoList.size()<6){
            model.put("recruitInfoList",recruitInfoList);
        }else {
            List<NeedInfo> recruitInfoList1 = new ArrayList();
            for (int i=0;i<6;i++) {
                recruitInfoList1.add(recruitInfoList1.get(i));
            }
            model.put("recruitInfoList",recruitInfoList1);
        }
        return "index/indexShowPage";
    }

    @GetMapping("/showPolicyPage")
    public String showPolicyPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                 Map<String, Object> model) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        System.out.println();
        List<Policy> policyList = policyService.queryAll();;
        model.put("policys", policyList);
        model.put("currentPage", pageNum);
        model.put("pages", page.getPages());
        model.put("pageSize", pageSize);
        model.put("message", "");
        return "index/showPolicyPage";
    }
    //完成
    @GetMapping("/downloadPolicyPage")
    public String downloadPolicyPage(String name, Model model,
                                   HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("文件名为："+name);
        List<Policy> policyList= policyService.queryByName(name);
        String fileName=policyList.get(0).getName();
        String path=policyList.get(0).getPath();
        if (fileName != null) {
            //设置文件路径
            System.out.println(path+"\\"+fileName);
            File file = new File(path+"\\"+fileName);
            //File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
                    fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1"); // firefox浏览器
                } else if (request.getHeader("user-Agent").toUpperCase().indexOf("MSIE") > 0) {
                    fileName = URLEncoder.encode(fileName, "UTF-8");// IE浏览器
                }else if (request.getHeader("user-Agent").toUpperCase().indexOf("CHROME") > 0) {
                    fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");// 谷歌
                }
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                response.setCharacterEncoding("UTF-8");
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    model.addAttribute("message", "下载成功");
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        model.addAttribute("message", "下载失败");
        return  "redirect:/showPolicyPage";
    }

    //完成
    @GetMapping("/showNeedPage")
    public String showNeedPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                               Map<String, Object> model) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<NeedInfo> needList = needService.queryAll();
        for (NeedInfo needInfo : needList) {
            //将id变成用户名
            if (needInfo.getUserid() == null) {
                continue;
            } else {
                if (userService.queryById(needInfo.getUserid()).getUsername() == null) {
                    continue;
                } else
                    needInfo.setUserid(userService.queryById(needInfo.getUserid()).getUsername());
            }
            //将schoolid变成学校名来显示
            if (needInfo.getSchoolid() == null) {
                continue;
            } else {
                if (schoolService.queryById(needInfo.getSchoolid()).getSchoolname() == null) {
                    continue;
                } else
                    needInfo.setSchoolid(schoolService.queryById(needInfo.getSchoolid()).getSchoolname());
            }
        }
        model.put("needs", needList);
        model.put("currentPage",pageNum);
        model.put("pages", page.getPages());
        System.out.println();
        model.put("pageSize", pageSize);
        return "index/showNeedPage";
    }
    //完成
    @GetMapping("/showRoadPage")
    public String showRoadPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                               Map<String, Object> model) {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<Road> roadList = roadService.queryAll();
        System.out.println(roadList.size());
        model.put("roads",roadList);
        model.put("currentPage", pageNum);
        model.put("pages", page.getPages());
        model.put("pageSize", pageSize);
        return "index/showRoadPage";
    }
    //完成
    @GetMapping("/showSchoolPage")
    public String showSchoolPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                 Map<String, Object> model) {

        Page<Object> page = PageHelper.startPage(pageNum, pageSize);

        List<SchoolWithBLOBs> schoolList = schoolService.queryAll();
        model.put("schools", schoolList);
        model.put("currentPage", pageNum);
        model.put("pages", page.getPages());
        model.put("pageSize", pageSize);
        return "index/showSchoolPage";
    }

    @GetMapping("/showWorkStatePage")
    public String showWorkStatePage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
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
        model.put("currentPage", pageNum);
        model.put("pages", page.getPages());
        model.put("pageSize", pageSize);
        return "index/showWorkStatePage";
    }
    @GetMapping("/showWorkStatePageDetaile")
    public String showWorkStatePageDetaile(String id, Map<String, Object> model) {
        WorkState workState = workstateService.queryById(id);
        model.put("workState", workState);
        return "index/showWorkStatePageDetaile";
    }
//完成
    @GetMapping("/showRecruitPage")
    public String showRecruitPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "6") Integer pageSize,
                                  Map<String, Object> model){

        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<RecruitInfo> recruitInfoList = recruitService.queryAllRecruit();

        model.put("recruitInfoList", recruitInfoList);
        model.put("currentPage", pageNum);
        model.put("pages", page.getPages());
        model.put("pageSize", pageSize);
        return "index/showRecruitPage";
    }
}
