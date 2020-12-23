package com.basic.framework.auth;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.basic.framework.auth.pojo.BasicAccount;
import com.basic.framework.auth.pojo.BasicUser;
import com.basic.framework.auth.service.BasicUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthCenterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthCenterApplicationTests {
	
	@Autowired
	private BasicUserService basicUserService;

	@Test
	public void userAddTest() throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		BasicUser user = new BasicUser();
		user.setUserCode("00001");
		user.setUserStatus(0);
		user.setNickName("admin");
		user.setCreateBy(1L);
		user.setCreateDate(new Date());
		
		BasicAccount account = new BasicAccount();
		account.setAccountCode("00001");
		account.setAccountName("高铭潮");
		account.setAccPassword(encoder.encode("123456"));
		account.setCreateDate(new Date());
		account.setCreateBy(1L);
		account.setUser(user);
		
		user.setAccount(account);
		
		basicUserService.userSave(user);
	}
	
}
