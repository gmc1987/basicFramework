/**
 * 
 */
package com.basic.framework.api.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gmc
 *
 */

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("sayHi")
	public String sayHi() throws Exception{
//		throw new Exception("发生异常");
		return "Hi ! I'm the first testing request.";
	}
}
