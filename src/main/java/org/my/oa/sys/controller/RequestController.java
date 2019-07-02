package org.my.oa.sys.controller;

import java.util.List;

import org.my.oa.sys.dto.UserModule;
import org.my.oa.sys.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/oa")
public class RequestController {
	
	/** 1.定义业务层对象 */
	@Autowired
	private SysService sysService;
	
	@ApiOperation(value = "调整登录页面")
	@RequestMapping(value="/login")
	public String requestLogin(){
		return "login";
	}
	
	@RequestMapping(value="/main")
	public String requestMain(Model model){
		try {
			//查询出当前用户拥有的所有模块权限
			List<UserModule> userModules = sysService.getUserPopedomModules();
			model.addAttribute("userPopedomModules", userModules);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "main";
	}
	
	@RequestMapping(value="/home")
	public String requestHome(){
		return "home";
	}
}
