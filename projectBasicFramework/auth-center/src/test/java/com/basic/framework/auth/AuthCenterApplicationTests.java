package com.basic.framework.auth;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.basic.framework.auth.pojo.Account;
import com.basic.framework.auth.pojo.PlatformUser;
import com.basic.framework.auth.service.BasicUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthCenterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthCenterApplicationTests {
	
	@Autowired
	private BasicUserService basicUserService;

	@Test
	public void userAddTest() throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		PlatformUser user = new PlatformUser();
		user.setUserCode("00001");
		user.setUserStatus(0);
		user.setUserName("admin");
		user.setCreateBy(1L);
		user.setCreateDate(new Date());
		
		Account account = new Account();
		account.setAccCode("00001");
		account.setAccName("高铭潮");
		account.setAccPassword(encoder.encode("123456"));
		account.setCreateDate(new Date());
		account.setCreateBy(1L);
		account.setPlatformUserId(user);
		
		user.setAccount(account);
		
		basicUserService.userSave(user);
	}
	
}
