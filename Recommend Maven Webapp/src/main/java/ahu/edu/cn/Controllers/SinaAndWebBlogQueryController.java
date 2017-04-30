package ahu.edu.cn.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ahu.edu.cn.JavaBeans.BlogBase;
import ahu.edu.cn.JavaBeans.WeiboUser;
import ahu.edu.cn.ServicesInterface.SinaAndWebBlogQueryServices;
import ahu.edu.cn.ServicesInterface.WeiboOperationServices;
import ahu.edu.cn.Util.subPage;
@Controller
public class SinaAndWebBlogQueryController {

	@Autowired
	private SinaAndWebBlogQueryServices services;
	@Autowired
	private WeiboOperationServices operationServices;
	@RequestMapping("/query/userblog")
	@ResponseBody
	public subPage<BlogBase> findUserBlog(int page_num,Long User_Id){
		subPage<BlogBase> bloglist=services.findUserAllBlog(User_Id, page_num, 6);
		//System.out.println("page_num:"+page_num+"\nUser_Id"+User_Id);
		for(int i=0;i<bloglist.getDataList().size();i++){
			System.out.println("satet"+operationServices.PraiserIsExist(bloglist.getDataList().get(i).getBlogId(),User_Id));
			System.out.println("new "+bloglist.getDataList().get(i).getUserLike());
			if(operationServices.PraiserIsExist(bloglist.getDataList().get(i).getBlogId(),User_Id)){
				bloglist.getDataList().get(i).setUserLike("true");
				System.out.println("new "+bloglist.getDataList().get(i).getUserLike());
			}
		}
		return bloglist;
		
	}
	@RequestMapping("/query/allblog")
	@ResponseBody
	public subPage<BlogBase> login(int page_num){

		return services.findAllBlog(page_num, 6);
		
	}
	@RequestMapping("/query/hotblog")
	@ResponseBody
	public subPage<BlogBase> findHotBlog(int page_num,Long User_Id){
		subPage<BlogBase> bloglist=services.findAllHotBlog(page_num, 6);
		for(BlogBase blog : bloglist.getDataList()){
			if(operationServices.PraiserIsExist(blog.getBlogId(),User_Id)){
				blog.setUserLike("true");
			}
		}
		
		return bloglist;
	}
	@RequestMapping("/query/recblog")
	@ResponseBody
	public subPage<BlogBase> findRecBlog(int page_num,Long User_Id,int labelid){
		subPage<BlogBase> bloglist=services.findRecBlog(User_Id,0,page_num, 6);
		for(BlogBase blog : bloglist.getDataList()){
			if(operationServices.PraiserIsExist(blog.getBlogId(),User_Id)){
				blog.setUserLike("true");
			}
		}
		
		return bloglist;
	}
	
}
