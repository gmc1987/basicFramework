/**
 * 
 */
package com.basic.framework.api.gateway.common.hystrix.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author gmc
 *
 */
@RestController
@RequestMapping("/apiHystrix")
public class HystrixController {
	
	private static final Logger log = LoggerFactory.getLogger(HystrixController.class);
	
	@HystrixCommand(commandKey="authHystrixCommand")
	public void authHystrixCommand() {
		log.info("触发熔断器");
	}
	
	@RequestMapping("/hystrixTimeout")
	public void hystrixTimeout() {
		log.info("服务请求触发熔断器");
	}

}
