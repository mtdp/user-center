package me.wanx.usercenter.dao;

import java.util.List;

import me.wanx.common.core.persistence.BaseMapper;
import me.wanx.usercenter.bean.Region;

public interface IAddressDao<T extends Region> extends BaseMapper<T> {
	/**
	 * 获取所有的省份
	 * @return
	 */
	public List<Region> getAllProvice();
	
	/**
	 * 根据省份获取市 
	 * 根据市获取县(区)
	 * @param parentCode
	 * @return
	 */
	public List<Region> getCityOrCountyOrAreas(String parentCode);
	
	/**
	 * 根据regCode获取一个省 市县(区)
	 * @param postCode
	 * @return
	 */
	public Region getCountyOrArea(String regCode);
	
}
