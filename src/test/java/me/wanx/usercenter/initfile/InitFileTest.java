package me.wanx.usercenter.initfile;


import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath*:/spring-context.xml"})
public class InitFileTest {
	
	@Test
	public void testHelloworld() {
	    //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
	    Factory<SecurityManager> factory =
	            new IniSecurityManagerFactory("classpath:shiro.ini");
	    //2、得到SecurityManager实例 并绑定给SecurityUtils
	    SecurityManager securityManager = factory.getInstance();
	    SecurityUtils.setSecurityManager(securityManager);
	    //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
	    Subject subject = SecurityUtils.getSubject();
	    UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
	    try {
	        //4、登录，即身份验证
	        subject.login(token);
	        System.out.println("sessionId="+subject.getSession().getId());
	    } catch (AuthenticationException e) {
	        //5、身份验证失败
	    	System.out.println("login faile:"+e);
	    }

	    Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

	    //6、退出
	    subject.logout();
	}
	
	@Test
    public void testCustomRealm() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
    }
	
	 //@Test
	    public void testJDBCRealm() {
	        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
	        Factory<org.apache.shiro.mgt.SecurityManager> factory =
	                new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");

	        //2、得到SecurityManager实例 并绑定给SecurityUtils
	        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
	        SecurityUtils.setSecurityManager(securityManager);

	        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
	        Subject subject = SecurityUtils.getSubject();
	        UsernamePasswordToken token = new UsernamePasswordToken("wanx", "123");

	        try {
	            //4、登录，即身份验证
	            subject.login(token);
	        } catch (AuthenticationException e) {
	            //5、身份验证失败
	            e.printStackTrace();
	        }

	        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

	        //6、退出
	        subject.logout();
	    }
	 
	 private void login(String config,String a,String p){
		 //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<org.apache.shiro.mgt.SecurityManager> factory =
                new IniSecurityManagerFactory(config);

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(a, p);
        //4、登录，即身份验证
        subject.login(token);
	 }
	 
	 private Subject subject() {
        return SecurityUtils.getSubject();
    }
 
    //@Test
    public void testHasRole() {
        login("classpath:shiro-role.ini", "zhang", "123");
        //判断拥有角色：role1
        Assert.assertTrue(subject().hasRole("role1"));
        //判断拥有角色：role1 and role2
        Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1", "role2")));
        //判断拥有角色：role1 and role2 and !role3
        boolean[] result = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertEquals(true, result[0]);
        Assert.assertEquals(true, result[1]);
        Assert.assertEquals(false, result[2]);
        
        boolean b = subject().isPermitted("user:create");
        System.out.println("==============b="+b);
        try{
        	subject().checkPermission("user:update");
        }catch(UnauthorizedException e){
        	e.printStackTrace();
        }
    }

}
