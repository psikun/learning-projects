package com.demo;

import com.demo.config.WxPayConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.PrivateKey;

@SpringBootTest
class PaymentDemoApplicationTests {

	@Autowired
	private WxPayConfig wxPayConfig;


	@Test
	void contextLoads() {
	}


//	@Test
//	void testGetPrivateKey(){
//		String privateKeyPath = wxPayConfig.getPrivateKeyPath();
//		PrivateKey privateKey = wxPayConfig.getPrivateKey(privateKeyPath);
//		System.out.println(privateKey);
//	}


}
