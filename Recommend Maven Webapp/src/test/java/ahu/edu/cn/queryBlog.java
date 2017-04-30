package ahu.edu.cn;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ahu.edu.cn.ServicesInterface.SinaAndWebBlogQueryServices;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class queryBlog {
	@Resource(name="blogServices")
	private SinaAndWebBlogQueryServices blogser;
	@Test
	public void tset(){
		blogser.findAllBlog(1, 6);
		
	}
}
