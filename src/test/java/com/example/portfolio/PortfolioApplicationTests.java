package com.example.portfolio;

import com.example.portfolio.entity.CartEntity;
import com.example.portfolio.repository.CartRepository;
import com.example.portfolio.repository.ItemCategoryRepository;
import com.example.portfolio.service.ItemCategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PortfolioApplicationTests {
	@Autowired
	ItemCategoryRepository itemCategoryRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	ItemCategoryService itemCategoryService;

	@Test
	void contextLoads() {
		String userid = "admin";

		//System.out.println("aaaaaa"+cartRepository.findByUserid(userid).getUserid());
	}


}
