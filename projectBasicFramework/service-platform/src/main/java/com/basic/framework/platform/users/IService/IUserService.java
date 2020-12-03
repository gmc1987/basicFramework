/**
 * 
 */
package com.basic.framework.platform.users.IService;

import java.util.List;

import com.basic.framework.platform.basic.IbaseService.IBasicService;
import com.basic.framework.platform.basic.common.utils.PageMode;
import com.basic.framework.platform.users.dto.PlatformUserDto;
import com.basic.framework.platform.users.pojo.PlatformUser;

/**
 * @author gmc
 *
 */
public interface IUserService extends IBasicService<PlatformUser>{

	/**
	 * 根据用户编码查询用户信息
	 * @param code
	 * @return
	 */
	public PlatformUser findUserByCode(String code);
	
	/**
	 * 查询所有用户
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	public List<PlatformUser> findAllUser(PlatformUserDto dto) throws Exception;
	
	/**
	 * 分页查询用户信息
	 * @param dto
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageMode<PlatformUser> findUserForPages(PlatformUserDto dto, int pageNumber, int pageSize) throws Exception;
	
	/**
	 * 用户编辑(新增/修改)
	 * @param PlatformUser
	 * @throws Exception
	 */
	public void userEditer(PlatformUser PlatformUser) throws Exception;
	
	/**
	 * 根据SQL查询系统用户
	 * @param dto 查询实体
	 * @param pageNumber 当前记录
	 * @param pageSize	当前页数
	 * @return
	 * @throws Exception
	 */
	public PageMode<PlatformUserDto> findSystemUserBySQL(PlatformUserDto dto, int pageNumber, int pageSize) throws Exception;
	
	/**
	 * 用户注册
	 * @param PlatformUser
	 * @throws Exception
	 */
	public void userRegist(PlatformUser PlatformUser) throws Exception;
	
}
