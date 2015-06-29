package me.wanx.usercenter.test.dao;

import java.util.ArrayList;
import java.util.List;

import me.wanx.common.core.utils.DateUtil;
import me.wanx.usercenter.bean.Resource;
import me.wanx.usercenter.bean.Role;
import me.wanx.usercenter.bean.User;
import me.wanx.usercenter.dao.IResourceDao;
import me.wanx.usercenter.dao.IRoleDao;
import me.wanx.usercenter.dao.IUserDao;
import me.wanx.usercenter.service.IResourceService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/spring-context.xml"})
public class TestDao {
	
	@Autowired
	private IUserDao<User> userDao;
	@Autowired
	private IRoleDao<Role> roleDao;
	@Autowired
	private IResourceDao<Resource> resourceDao;
	
	@Test
	public void testUserDao(){
		System.out.println("hello user center");
		User t = new User();
		t.setUserName("gqwang");
		t.setAccount("wanx");
		t.setPassword("wanx.me");
		t.setEmail("594566751@qq.com");
		t.setMobile("18621176637");
		t.setStatus("1");
		t.setCreateTime(DateUtil.getCurrentTime());
		t.setUpdateTime(DateUtil.getCurrentTime());
		userDao.add(t);
		System.out.println("user dao add success");
		List<User> users = userDao.gets();
		System.out.println("user list:"+users);
	}
	
	@Test
	public void testUserDao2(){
		User t = new User();
		t.setUserId(1);
		Role role = new Role();
		role.setRoleId(1);
		List<Role> roles = new ArrayList<Role>();
		roles.add(role);
		userDao.addUserRoles(t.getUserId(), roles);
	}
	
	@Test
	public void testSaveRoleDao(){
		Role t = new Role();
		t.setRoleName("开始菜单");
		t.setRoleCode("start_menu");
		t.setStatus("1");
		t.setCreateByUserId(1);
		t.setLastUpdateUserId(1);
		t.setCreateTime(DateUtil.getCurrentTime());
		t.setUpdateTime(DateUtil.getCurrentTime());
		roleDao.add(t);
	}
	@Test
	public void testSaveRoleDao2(){
		Role t = new Role();
		t.setRoleId(1);
		List<Resource> res = new ArrayList<Resource>();
		Resource e = new Resource();
		e.setResId(1);
		res.add(e);
		t.setResources(res);
		roleDao.addRoleRescources(t.getRoleId(), res);
	}
	
	@Test
	public void testUpdateRoleDao(){
		Role t = new Role();
		t.setRoleId(1);
		t.setRoleName("第一个角色");
		t.setRoleCode("w");
		t.setStatus("1");
		t.setLastUpdateUserId(1);
		t.setUpdateTime(DateUtil.getCurrentTime());
		roleDao.update(t);
	}

	@Test
	public void testSaveResouceDao(){
		Resource t = new Resource();
		t.setResName("百度");
		t.setResURI("http://www.baidu.com/index.php");
		t.setIsMenu("0");
		t.setStatus("1");
		t.setCreateTime(DateUtil.getCurrentTime());
		t.setUpdateTime(DateUtil.getCurrentTime());
		resourceDao.add(t);
	}
	
	
	
}
