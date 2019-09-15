package com.travel.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.travel.domain.Admin;
import com.travel.domain.Apartment;
import com.travel.domain.Staff;
import com.travel.domain.Finance;
import com.travel.domain.Manager;
import com.travel.domain.Notice;
import com.travel.service.TravelService;
import com.travel.util.common.Constants;

@Controller
public class FinanceController {
	@Autowired
	@Qualifier("TravelService")
	private TravelService travelservice;
	
	//修改财务个人信息
	@RequestMapping(value="/finance/update/password",method=RequestMethod.GET)
	 public String updatefianance(Model model,String id,HttpServletRequest request){
		Finance finance=(Finance)request.getSession().getAttribute(Constants.USER_SESSION);
		String finance_id=finance.getId();
		finance=travelservice.get_finance(finance_id);
		model.addAttribute("finance",finance);

		return "/finance/update/password";
	}
	@RequestMapping(value="/finance/update/password",method=RequestMethod.POST)
	 public String updatefianaceinfo(Model model,String id,@ModelAttribute Finance finance){
		finance.setId(id);
		travelservice.update_finance(finance);
		return "/finance/update/password";
	}

}
