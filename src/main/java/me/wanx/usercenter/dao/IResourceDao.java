package me.wanx.usercenter.dao;

import java.util.List;
import java.util.Map;

import me.wanx.common.core.persistence.BaseMapper;
import me.wanx.usercenter.bean.Resource;

/**
 * 
* @ClassName: IResourceDao 
* @Description: TODO 
* @author gqwang
* @date 2015年6月19日 上午11:07:06 
*
 */
public interface IResourceDao<T extends Resource> extends BaseMapper<T> {
	
	/**
	 * 资源分页
	 * @param map
	 * @return
	 */
	public List<Resource> searchResPagination(Map<String,Object> map);
	public Integer searchCountRes(Map<String,Object> map);
	
	/**
	 * 获取顶级菜单
	 * @return
	 */
	public List<Resource> getMenuResources();
	
	/**
	 * 获取resId下的子菜单
	 * @param resId
	 * @return
	 */
	public List<Resource> getSubMenuResources(Integer resId);
	
	/**
	 * 获取resId菜单下的按钮
	 * @param resId
	 * @return
	 */
	public List<Resource> getButtonMenuRes(Integer resId);

}
