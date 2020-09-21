package edu.bit.juti.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import edu.bit.juti.service.LogInService;
import edu.bit.juti.service.MemberService;
import edu.bit.juti.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * Handles requests for the application home page.
 */
@Controller
@Log4j
@AllArgsConstructor  
public class LogInController {
	
	
	@Inject
	private LogInService loginService;
	private MemberService memberService;

	
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
	
	@RequestMapping(value = "/login")
    public String login(HttpSession session) throws Exception{
		log.info("/login");
		

		
		return "login";
		
	
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
		
		
		
		
		
		@GetMapping("/join") 
		public String join() {
			log.info("join");
				
			return "join2";
		}
		
		//join1
		/*@PostMapping("/user/addUser")
		public String addUser(UserVO userVO) {
			log.info("addUser");
			
			memberService.addUser(userVO);
			
			return "redirect:/"; 
		}**/
		
		//join2
		// ȸ������ ����
		@PostMapping("/success")
		public String addUser(UserVO userVO) {
			log.info("addUser");
			
			memberService.addUser(userVO);
			
			return "main"; 
		}
	
	
			
	

	
	
	

	
	
	
	
	
}	
	

