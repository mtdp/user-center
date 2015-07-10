package me.wanx.usercenter.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.wanx.usercenter.bean.Region;
import me.wanx.usercenter.dao.IAddressDao;

@Service
public class AddressServiceImpl implements IAddressService {
	private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);
	
	@Autowired
	private IAddressDao<Region> addressDao;
	
	@Override
	public List<Region> getAllProvice() {
		return addressDao.getAllProvice();
	}

	@Override
	public List<Region> getCity(String parentCode) {
		return addressDao.getCityOrCountyOrAreas(parentCode);
	}

	@Override
	public List<Region> getCountyOrAreas(String parentCode) {
		return addressDao.getCityOrCountyOrAreas(parentCode);
	}

	@Override
	public Region getCityOrCountyOrArea(String regCode) {
		return addressDao.getCountyOrArea(regCode);
	}

}
