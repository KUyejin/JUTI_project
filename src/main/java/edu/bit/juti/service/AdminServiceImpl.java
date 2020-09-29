package edu.bit.juti.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.bit.juti.dao.AdminDaoImpl;
import edu.bit.juti.dao.MemberDaoImpl;
import edu.bit.juti.mapper.UserMapper;
import edu.bit.juti.vo.ProductVO;
import edu.bit.juti.vo.Product_CategoryVO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@AllArgsConstructor
@Service
public class AdminServiceImpl implements AdminService{
	
	@Inject 
	 AdminDaoImpl adminDao;
	
    //��ǰ���
	@Override
	public List<ProductVO> productList() {
		System.out.println("��ǰ����Ʈ");
		return adminDao.productList();
	}

	//��ǰ���
	@Override
	public void productRegister(ProductVO productVO) {
		adminDao.productRegister(productVO);
		
	}

	@Override
	public List<Product_CategoryVO> category() {
		return adminDao.category();
	}

}