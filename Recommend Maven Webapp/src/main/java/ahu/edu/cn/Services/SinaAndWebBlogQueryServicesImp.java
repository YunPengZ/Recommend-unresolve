package ahu.edu.cn.Services;

import org.springframework.beans.factory.annotation.Autowired;

import ahu.edu.cn.JavaBeans.BlogBase;
import ahu.edu.cn.JavaBeans.BlogInfo;
import ahu.edu.cn.JavaDaosInterface.SinaAndWebBlogQueryDAO;
import ahu.edu.cn.ServicesInterface.SinaAndWebBlogQueryServices;
import ahu.edu.cn.Util.subPage;

public class SinaAndWebBlogQueryServicesImp implements
		SinaAndWebBlogQueryServices {

	@Autowired
	private SinaAndWebBlogQueryDAO blogDao;
	
	
	public SinaAndWebBlogQueryDAO getBlogDao() {
		return blogDao;
	}


	public void setBlogDao(SinaAndWebBlogQueryDAO blogDao) {
		this.blogDao = blogDao;
	}


	@Override
	public subPage<BlogBase> findAllBlog(int pageNum, int pageSize) {
		
		return  blogDao.findAllBlog(pageNum, pageSize);
	}


	@Override
	public subPage<BlogBase> findUserAllBlog(Long UserId, int PageNum, int PageSize) {
		// TODO 自动生成的方法存根
		return blogDao.findUserAllBlog(UserId, PageNum, PageSize);
	}


	@Override
	public subPage<BlogBase> findAllHotBlog(int PageNum, int PageSize) {
		// TODO 自动生成的方法存根
		return blogDao.findAllHotBlog(PageNum, PageSize);
	}


	@Override
	public subPage<BlogBase> findRecBlog(Long UserId, int labelId, int PageNum, int PageSize) {
		// TODO 自动生成的方法存根
		return blogDao.findRecBlog(UserId, labelId, PageNum, PageSize);
	}

}
