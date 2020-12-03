/**
 * 
 */
package com.basic.framework.platform.menus.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.basic.framework.platform.basic.SideException.CustException;
import com.basic.framework.platform.basic.baseServiceImpl.BasicServiceImpl;
import com.basic.framework.platform.basic.common.utils.DetachedCriteriaTS;
import com.basic.framework.platform.menus.IDao.IMenuDao;
import com.basic.framework.platform.menus.IService.IMenuService;
import com.basic.framework.platform.menus.dto.MenuDto;
import com.basic.framework.platform.menus.pojo.Menus;

/**
 * @author gmc
 */
@Service("menuservice")
public class MenuserviceImpl extends BasicServiceImpl<Menus> implements IMenuService {
	
	@Autowired
	@Qualifier("menuDao")
	private IMenuDao menuDao;

	@Override
	public List<Menus> getParents() throws Exception {
		List<Menus> menus = new ArrayList<Menus>();
		DetachedCriteriaTS<Menus> criteria = new DetachedCriteriaTS<Menus>(Menus.class);
		criteria.add(Restrictions.eq("isParent", 0));
		criteria.addOrder(Order.asc("menuSort"));
		menus = menuDao.findAll(criteria);
		return menus;
	}

	@Override
	public List<Menus> getChildByParentId(Integer parentId) throws CustException {
		List<Menus> menus = new ArrayList<Menus>();
		Menus parentMenu = menuDao.get(Menus.class, parentId);
		DetachedCriteriaTS<Menus> criteria = new DetachedCriteriaTS<Menus>(Menus.class);
		criteria.add(Restrictions.eq("parentMenu", parentMenu));
		criteria.addOrder(Order.asc("menuSort"));
		menus = menuDao.findAll(criteria);
		return menus;
	}

	@Override
	public List<Menus> findMenuByKey(MenuDto dto) throws CustException {
		List<Menus> pageModel = null;
		DetachedCriteriaTS<Menus> criteria = new DetachedCriteriaTS<Menus>(Menus.class);
		if(dto != null) {
			if (StringUtils.isNotEmpty(dto.getMenuCode())) {
				criteria.add(Restrictions.ilike("menuCode", dto.getMenuCode(), MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotEmpty(dto.getMenuName())) {
				criteria.add(Restrictions.ilike("menuName", dto.getMenuName(), MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotEmpty(dto.getMenuPath())) {
				criteria.add(Restrictions.ilike("menuPath", dto.getMenuPath(), MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotEmpty(dto.getParentId())) {
				Menus parent = new Menus();
				parent.setMenuId(Integer.valueOf(dto.getParentId()));
				criteria.add(Restrictions.eq("parentMenu", parent));
			}
			if (StringUtils.isNotEmpty(dto.getKey())) {
				criteria.add(Restrictions.or(Restrictions.ilike("menuCode", dto.getKey(), MatchMode.ANYWHERE), 
							Restrictions.ilike("menuName", dto.getKey(), MatchMode.ANYWHERE), 
							Restrictions.ilike("menuPath", dto.getKey(), MatchMode.ANYWHERE)));
			}
			if (dto.getIsParent() != null) {
				criteria.add(Restrictions.eq("isParent", dto.getIsParent()));
			}
			if(dto.getMenuType() != null) {
				criteria.add(Restrictions.eq("menuType", Integer.parseInt(dto.getMenuType())));
			}
		}
		criteria.addOrder(Order.asc("menuSort"));
		pageModel = menuDao.findAll(criteria);
		return pageModel;
	}

	@Override
	@Transactional
	public void editMenuByDto(MenuDto dto) throws CustException {
		
		Menus menu = null;
		
		if(dto != null) {
			if(StringUtils.isNotEmpty(dto.getMenuId()) && !"0".equals(dto.getMenuId())) {
				menu = menuDao.get(Menus.class, Integer.parseInt(dto.getMenuId()));
				menu.setMenuName(dto.getMenuName());
				menu.setMenuPath(dto.getMenuPath());
				menu.setMenuCode(dto.getMenuCode());
				menu.setMenuSort(dto.getMenuSort());
				menu.setMenuType(Integer.parseInt(dto.getMenuType()));
				menu.setLastUpdateBy(Integer.parseInt(dto.getCurrentUser()));
				menu.setLastUpdateDate(dto.getDate());
				if(StringUtils.isNotEmpty(dto.getIcon())) {
					menu.setIcon(dto.getIcon());
				}
			} else {
				menu = new Menus();
				menu.setMenuName(dto.getMenuName());
				menu.setMenuPath(dto.getMenuPath());
				menu.setMenuCode(dto.getMenuCode());
				menu.setMenuSort(dto.getMenuSort());
				menu.setMenuType(0);
				menu.setLastUpdateBy(Integer.parseInt(dto.getCurrentUser()));
				menu.setLastUpdateDate(dto.getDate());
				menu.setCreateBy(Integer.parseInt(dto.getCurrentUser()));
				menu.setCreateDate(dto.getDate());
				menu.setIsParent(dto.getIsParent());
				if(dto.getParentMenu() != null) {
					Menus parent = menuDao.get(Menus.class, dto.getParentMenu());
					if(parent != null) {
						menu.setParentMenu(parent);
					}
				}
				
			}
		}
		
		menuDao.saveOrUpdate(menu);
	}

	@Override
	@Transactional
	public void delMenuByDto(MenuDto dto) throws CustException {
		if(StringUtils.isNotBlank(dto.getMenuId())) {
			Menus menu = menuDao.get(Menus.class, Integer.valueOf(dto.getMenuId()));
			if(menu != null) {
				menuDao.delete(menu);
			} else {
				throw new CustException("没有此菜单");
			}
		} else {
			throw new CustException("参数有误");
		}
	}

	@Override
	public List<Menus> getChildMenusByMenuType(MenuDto dto) throws CustException {
		List<Menus> menus = new ArrayList<Menus>();
		DetachedCriteriaTS<Menus> criteria = new DetachedCriteriaTS<Menus>(Menus.class);
		criteria.add(Restrictions.eq("isParent", 1));
		
		if(dto.getMenuType() != null) {
			criteria.add(Restrictions.eq("menuType", dto.getMenuType()));
		}
		
		menus = menuDao.findAll(criteria);
		
		return menus;
	}
	
}
