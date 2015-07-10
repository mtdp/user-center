package me.wanx.usercenter.test.service;

import java.util.List;

import me.wanx.usercenter.bean.Region;
import me.wanx.usercenter.service.IAddressService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/spring-context.xml"})
public class AddressServiceTest {
	
	@Autowired
	private IAddressService addressService;
	
	@Test
	public void getAllPro(){
		List<Region> list = addressService.getAllProvice();
		System.out.println(list);
	}
}
