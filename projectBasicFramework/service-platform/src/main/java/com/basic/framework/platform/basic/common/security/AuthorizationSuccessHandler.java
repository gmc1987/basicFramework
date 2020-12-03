/**
 * 
 */
package com.basic.framework.platform.basic.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @author gmc
 * @see 自定义校验成功处理
 */
public class AuthorizationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	//验证成功，生成token
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		
	}
}
