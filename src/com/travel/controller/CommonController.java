package com.travel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	@RequestMapping(value="/{formName}")
	 public String loginForm(@PathVariable String formName){
		// ��Ϊһ���շ���������ƥ���κ���Ч���룬����ת��404
		return formName;
//		String blank = "blank";
//		return blank;
	}
	
	@RequestMapping(value="/")
	 public String index(){
		String blank = "index";//��ҳ��Ϊindex
		return blank;
	}
	@RequestMapping(value="/welcome")
	 public String welcome(){
		String blank = "welcome";
		return blank;
	}
}
