package edu.bit.juti.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import edu.bit.juti.service.LogInService;
import edu.bit.juti.service.MemberService;
import edu.bit.juti.service.MemberServiceImpl;
import edu.bit.juti.vo.LoginVO;
import edu.bit.juti.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
public class MemberController {
	
	
	@Autowired
	private LogInService loginService;
	
	
	@Inject
	private MemberServiceImpl memberService;

	
	//ó���ؾ���
	@RequestMapping(value = "/login2", method = RequestMethod.GET)
	public String login(HttpServletRequest req, RedirectAttributes rttr) throws Exception{
		
		log.info("post login");
		
		//Session ó���� ���� Session��ü HttpServletRequest�ȿ� ����
		HttpSession session = req.getSession();
		
		String id = req.getParameter("user_id");
		String pw = req.getParameter("user_password");
		
		UserVO login = loginService.loginUser(id,pw);
		
		if(login == null) {
			session.setAttribute("user", null);
			
			//spring3���� �����ϴ� RedirectAttributes�� ����ϸ� redirect post ������ �����մϴ�
			//������ ��ȸ���Դϴ�. ���ΰ�ħ�ϸ� ���󰡴� �������̹Ƿ� �������� ���� ���/�Ұ��� �Ǵ��� �� �ϼž� �մϴ�
			
			rttr.addFlashAttribute("msg",false); //�����̷�Ʈ ���� �÷��ÿ� �����ϴ� �޼ҵ��. �����̷�Ʈ ���Ŀ��� �Ҹ��Ѵ�.

		}else {
			session.setAttribute("user",login);
		}
		//return "redirect:/";
		return "login2";
		
	}
	
	// �α��� ȭ�� (�˾�)
	@RequestMapping(value = "login")
    public String login(HttpSession session) throws Exception{
		log.info("/login");
				
		return "member/login";			
    }
	
	// �α��� ó��
	@RequestMapping(value = "login", method = RequestMethod.POST)
		public @ResponseBody UserVO login(HttpServletRequest req, HttpServletResponse resp,LoginVO loginVo) {
			UserVO user = memberService.login(req, resp,loginVo);
			
			return user;
		}
	
	//�α��� ������ �� session�� �ִ���?
		//session��ü��  HttpSession���� �޾ƿ´�
		
		@RequestMapping(value = "/logout")
	        public String logout(HttpSession session) throws Exception{
				log.info("/member/logout");
				
				session.invalidate(); //�޸𸮿��� ���������� �� - �� DELETE�� �ƴѰ�? �������÷��Ͱ� ���������� ����� �� ������ ������ ���������� �ʴ´�. ->�޸𸮿� ��������
				                      //                                 .invalidate�� �������÷��Ϳ��� �޸� ����� ����̶�� �˷��ִ°�
				
				return "redirect:/";
						
		}
		
		
		
		
		
		//ȸ������
		@RequestMapping(value = "/join")
		public String join() { 
			log.info("join");
			
			return "member/join"; 
		}
					
		//ȸ�����Լ���
		@RequestMapping(value = "/member/addUser", method = RequestMethod.POST)
		public String addUser(Model model, UserVO userVO) {
			System.out.println(userVO);
			memberService.addUser(model, userVO);
						
			return "member/success";
		}

		
		
	
	

	
			
	

	
	
	

	
	
	
	
	
}	
	

