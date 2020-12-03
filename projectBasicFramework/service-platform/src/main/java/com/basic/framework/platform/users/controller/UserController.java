/**
 * 
 */
package com.basic.framework.platform.users.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.basic.framework.platform.basic.common.utils.PageMode;
import com.basic.framework.platform.basic.constant.PlatformConstant;
import com.basic.framework.platform.basic.controller.ServiceBaseController;
import com.basic.framework.platform.basic.dto.ResultDto;
import com.basic.framework.platform.users.IService.IUserService;
import com.basic.framework.platform.users.dto.PlatformUserDto;
import com.basic.framework.platform.users.pojo.PlatformUser;

/**
 * @author gmc
 * 
 */
@RestController
@RequestMapping("/PlatformUsers")
public class UserController extends ServiceBaseController {
	
	@Qualifier("userService")
	@Autowired
	private IUserService userService;

	@GetMapping("/getUserList")
	public ResultDto<PlatformUserDto> getUserList(PlatformUserDto dto, @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize")int pageSize){
		
		ResultDto<PlatformUserDto> resultDto = new ResultDto<PlatformUserDto>();
		PageMode<PlatformUserDto> pageMode = null;
		
		try {
			pageMode = userService.findSystemUserBySQL(dto, pageNumber, pageSize);
			
			if(pageMode != null) {
				resultDto.setRetCode(PlatformConstant.SUCCESS);
				resultDto.setRetMsg(PlatformConstant.SUCCESS_MSG);
				resultDto.setPageMode(pageMode);
			} else {
				resultDto.setRetCode(PlatformConstant.QUERY_EMPTY);
				resultDto.setRetMsg(PlatformConstant.QUERY_EMPTY_MSG);
			}
			
		} catch(Exception e) {
			resultDto.setRetCode(PlatformConstant.FAIL);
			resultDto.setRetMsg(PlatformConstant.FAIL_MSG);
			e.printStackTrace();
		}
		
		return resultDto;
	}
	
	@PostMapping("/PlatformUserEditer")
	public ResultDto<PlatformUserDto> PlatformUserEdit(@RequestBody PlatformUser PlatformUser){
		
		ResultDto<PlatformUserDto> resultDto = new ResultDto<PlatformUserDto>();
		
		PageMode<PlatformUserDto> pageMode = null;
		
		PlatformUserDto dto = new PlatformUserDto();
		
		try {
			if(PlatformUser != null && PlatformUser.getUserId() != null) {
				PlatformUser.setLastUpdateBy(getUserInfo().getUser().getUserId());
				PlatformUser.setLastUpdateDate(new Date());
				PlatformUser.getAccount().setLastUpdateBy(PlatformUser.getCreateBy());
				PlatformUser.getAccount().setLastUpdateDate(new Date());
				String password = PlatformUser.getAccount().getAccPassword();
				if(StringUtils.isNotEmpty(password) && password.length() < 60) {
					PlatformUser.getAccount().setAccPassword(new BCryptPasswordEncoder().encode(password));
				}
			} else {
				PlatformUser.setCreateBy(getUserInfo().getUser().getUserId());
				PlatformUser.setCreateDate(new Date());
				PlatformUser.getAccount().setCreateBy(PlatformUser.getCreateBy());
				PlatformUser.getAccount().setCreateDate(new Date());
				String password = PlatformUser.getAccount().getAccPassword();
				if(StringUtils.isNotEmpty(password)) {
					PlatformUser.getAccount().setAccPassword(new BCryptPasswordEncoder().encode(password));
				}
			}
			PlatformUser.getAccount().setPlatformUserId(PlatformUser);
			userService.update(PlatformUser);
			
			pageMode = userService.findSystemUserBySQL(dto, 1, 10);
			
			if(pageMode != null) {
				resultDto.setRetCode(PlatformConstant.SUCCESS);
				resultDto.setRetMsg(PlatformConstant.SUCCESS_MSG);
				resultDto.setPageMode(pageMode);
			} else {
				resultDto.setRetCode(PlatformConstant.QUERY_EMPTY);
				resultDto.setRetMsg(PlatformConstant.QUERY_EMPTY_MSG);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			resultDto.setRetCode(PlatformConstant.FAIL);
			resultDto.setRetMsg(PlatformConstant.FAIL_MSG);
		}
		
		
		return resultDto;
	}
	
	/**
	 * 根据ID获取用户信息
	 * @param PlatformUserId
	 * @return
	 */
	@GetMapping("/PlatformUserInfo")
	public ResultDto<PlatformUser> getUserInfo(@RequestParam("PlatformUserId") Long PlatformUserId){
		
		ResultDto<PlatformUser> resultDto = new ResultDto<PlatformUser>();
		try {
			PlatformUser PlatformUser = userService.get(PlatformUser.class, PlatformUserId);
			List<PlatformUser> record = new ArrayList<PlatformUser>();
			if(PlatformUser != null) {
//				System.out.println("当前账号编码:"+PlatformUser.getAccount().getAccCode());
				record.add(PlatformUser);
				resultDto.setRecord(record);
				resultDto.setRetCode(PlatformConstant.SUCCESS);
				resultDto.setRetMsg(PlatformConstant.SUCCESS_MSG);
			} else {
				resultDto.setRetCode(PlatformConstant.QUERY_EMPTY);
				resultDto.setRetMsg(PlatformConstant.QUERY_EMPTY_MSG);
			}
			
		} catch (Exception e) {
			resultDto.setRetCode(PlatformConstant.FAIL);
			resultDto.setRetMsg(PlatformConstant.FAIL_MSG);
			e.printStackTrace();
		}
		
		return resultDto;
	}
	
	@DeleteMapping("/delete")
	public ResultDto<PlatformUser> deletePlatformUser(@RequestParam("PlatformUserId") Long PlatformUserId){
		ResultDto<PlatformUser> resultDto = new ResultDto<PlatformUser>();
		try {
			PlatformUser user = userService.get(PlatformUser.class, PlatformUserId);
			
			if(user != null) {
				userService.delete(user);
				resultDto.setRetCode(PlatformConstant.SUCCESS);
				resultDto.setRetMsg(PlatformConstant.SUCCESS_MSG);
			} else {
				resultDto.setRetCode(PlatformConstant.QUERY_EMPTY);
				resultDto.setRetMsg(PlatformConstant.QUERY_EMPTY_MSG);
			}
			
		} catch (Exception e) {
			resultDto.setRetCode(PlatformConstant.FAIL);
			resultDto.setRetMsg(PlatformConstant.FAIL_MSG);
			e.printStackTrace();
		}
		
		return resultDto;
	}
	
}
