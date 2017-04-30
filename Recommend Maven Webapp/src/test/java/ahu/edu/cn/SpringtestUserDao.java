package ahu.edu.cn;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ahu.edu.cn.JavaBeans.WeiboUser;
import ahu.edu.cn.ServicesInterface.WeiboUserServices;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringtestUserDao {

	@Resource(name="userServices")
	private WeiboUserServices userServices;
	@Test
	public void testUserDaoRegister(){
		
		WeiboUser user =new WeiboUser();
		user.setUserName("sdfsdf");
		user.setUserPassword("123");
		userServices.Register(user);
	}
	@Test
	public void testUserDaoIsExist(){
		
		boolean flag=userServices.UserIsExist("sdfsdf");
	    System.out.println(flag);
	}
	@Test
	public void testUserDaoLogin(){
		
		WeiboUser user = userServices.Login("sdfsdf", "123");
	    System.out.println(user.getUserName());
	}
	
}
