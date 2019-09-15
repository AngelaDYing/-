package com.travel.controller;

import java.util.List;

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
import com.travel.domain.Staff;
import com.travel.domain.Finance;
import com.travel.domain.Manager;
import com.travel.service.TravelService;
import com.travel.util.common.Constants;

@Controller
public class LoginContoller {
	@Autowired
	@Qualifier("TravelService")
	private TravelService travelservice;
    //�˳�����
	@RequestMapping(value="/user/logout")
	 public ModelAndView logout(ModelAndView mv, HttpSession session){
		session.setAttribute(Constants.USER_SESSION, null);
		session.setAttribute("tag", null);
		mv.setViewName("redirect:/index");
		
		return mv;
	}
	@RequestMapping(value="/login")
	 public ModelAndView login(@RequestParam("id") String id,
			 @RequestParam("password") String password,@RequestParam("tag") String tag,
			 HttpSession session,
			 ModelAndView mv){
		// ����ҵ���߼�����ж��û��Ƿ���Ե�¼
		boolean flag = false;
		if("1".equals(tag)) {//����Ա��½�����ж�
			Admin admin = travelservice.admin_login(id, password);
			if(admin!=null){
				// ���û����浽HttpSession����
				System.out.println("HttpSession����Ա");
				session.setAttribute(Constants.USER_SESSION, admin);
				session.setAttribute("tag", "1");
				// �ͻ�����ת��mainҳ��
				mv.setViewName("redirect:/adminindex");//����Ա�ܽ���
			}else{
				// ���õ�¼ʧ����ʾ��Ϣ
				System.out.println("���õ�¼ʧ����ʾ��Ϣ");
				mv.addObject("message", "�˺Ż��������!����������");
				// �������ڲ���ת����¼ҳ��
				mv.setViewName("forward:/loginForm");
			}
		}
		else if("2".equals(tag)){//�����½�����ж�
			Manager manager = travelservice.manager_login(id, password);
			if(manager!=null){
				// ���û����浽HttpSession����
				System.out.println("HttpSession����");
				session.setAttribute(Constants.USER_SESSION, manager);
				session.setAttribute("tag", "2");
				// �ͻ�����ת��mainҳ��
				mv.setViewName("redirect:/managerindex");//�����ܽ���
			}else{
				// ���õ�¼ʧ����ʾ��Ϣ
				System.out.println("���õ�¼ʧ����ʾ��Ϣ");
				mv.addObject("message", "�˺Ż��������!����������");
				// �������ڲ���ת����¼ҳ��
				mv.setViewName("forward:/loginForm");
			}
		}
		else if("3".equals(tag)){//�����½�����ж�
			Finance finance = travelservice.finance_login(id, password);
			if(finance!=null){
				// ���û����浽HttpSession����
				System.out.println("HttpSession����");
				session.setAttribute(Constants.USER_SESSION, finance);
				session.setAttribute("tag", "3");
				// �ͻ�����ת��mainҳ��
				mv.setViewName("redirect:/financeindex");//�����ܽ���
			}else{
				// ���õ�¼ʧ����ʾ��Ϣ
				System.out.println("���õ�¼ʧ����ʾ��Ϣ");
				mv.addObject("message", "�˺Ż��������!����������");
				// �������ڲ���ת����¼ҳ��
				mv.setViewName("forward:/loginForm");
			}
		}
		else {//��ͨԱ���жϵ�½
			Staff staff = travelservice.staff_login(id, password);
			if(staff!=null){
				// ���û����浽HttpSession����
				System.out.println("HttpSession��ͨԱ��");
				session.setAttribute(Constants.USER_SESSION, staff);
				session.setAttribute("tag", "4");
				// �ͻ�����ת��mainҳ��
				mv.setViewName("redirect:/staffindex/");//��ͨ�û���ҳ��
			}else{
				// ���õ�¼ʧ����ʾ��Ϣ
				System.out.println("���õ�¼ʧ����ʾ��Ϣ");
				mv.addObject("message", "�˺Ż��������!����������");
				// �������ڲ���ת����¼ҳ��
				mv.setViewName("forward:/loginForm");
			}
			
		}
		return mv;
	}
}
