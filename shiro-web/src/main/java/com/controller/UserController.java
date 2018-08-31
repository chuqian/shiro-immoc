package com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vo.User;

@Controller
public class UserController {

	@RequestMapping(value="/subLogin", method=RequestMethod.POST, 
			produces="application/json;charset=utf-8")
	@ResponseBody
	public String subLogin(User user){
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),
				user.getPassword());
		try{
			token.setRememberMe(user.isRememberMe());
			subject.login(token);
		}
		catch(Exception e){
			return e.getMessage();
		}
		
		if(subject.hasRole("admin"))
			return "admin登录成功";
		return "登录成功";
	}
	
	@RequestMapping(value="/testRoles", method=RequestMethod.GET)
	@ResponseBody
	@RequiresRoles("admin")
	public String testRoles(){
		return "testRoles successful";
	}
	
	@RequestMapping(value="/testPermissions", method=RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions("user:update")
	public String testPermissions(){
		return "testPermissions successful";
	}
	
	@RequestMapping(value="/testRoles1", method=RequestMethod.GET)
	@ResponseBody
	public String testRoles1(){
		return "testRoles1 successful";
	}
	
	@RequestMapping(value="/testPerms1", method=RequestMethod.GET)
	@ResponseBody
	public String testPerms1(){
		return "testPerms1 successful";
	}
	
	@RequestMapping(value="testRolesFilter" , method=RequestMethod.GET)
	@ResponseBody
	public String testRolesFilter(){
		return "testRolesFilter successful";
	}
	
}