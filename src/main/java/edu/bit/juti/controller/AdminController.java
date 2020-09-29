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

import edu.bit.juti.service.AdminServiceImpl;
import edu.bit.juti.service.MemberService;
import edu.bit.juti.service.MemberServiceImpl;
import edu.bit.juti.vo.LoginVO;
import edu.bit.juti.vo.ProductVO;
import edu.bit.juti.vo.Product_CategoryVO;
import edu.bit.juti.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.sf.json.JSONArray;

@Controller
@RequestMapping("/admin/")
@Log4j
public class AdminController {

    		
	@Inject
	private AdminServiceImpl adminService;
	
	//������������
	@RequestMapping(value = "index")
    public String index(){
		log.info("index");				
		return "admin/index";			
    }
	
	//��ǰ���
	@RequestMapping(value = "proList")
    public String productlist(Model model){
		log.info("��ǰ���");	
			
		return "admin/proList";			
    }
	
	//��ǰ��� �Ϸ�
	@RequestMapping(value = "proList", method = RequestMethod.GET)
    public void prolist(Model model){
		log.info("��ǰ���");			
		List<ProductVO> prolist = adminService.productList();
		model.addAttribute("prolist",prolist);					
    }


	//��ǰ��� ������
	@RequestMapping(value = "proRegister")
    public String productRegister(){
		log.info("��ǰ���");
				
		return "admin/proRegister";			
    }
	
	//��ǰ��� �Ϸ�
	@RequestMapping(value = "addProRegister", method = RequestMethod.POST)
    public String addProductRegister(ProductVO productVO){
		log.info("��ǰ��ϿϷ�");
		
		adminService.productRegister(productVO);
			
		return "redirect:/admin/proList";			
    }
	
	
	
	// ��ǰī�װ� ���///////////////////
	@RequestMapping(value = "proRegister", method = RequestMethod.GET)
	public void roductRegister(Model model) throws Exception {
	 log.info("get goods register");
	 
//	 List<Product_CategoryVO> category = null;
	 List<Product_CategoryVO> category = adminService.category();
	 model.addAttribute("category", category);
	}
		
	


	
		
	
	

	
			
	

	
	
	

	
	
	
	
	
}	
	

