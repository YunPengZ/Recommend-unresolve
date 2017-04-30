package ahu.edu.cn.Controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ahu.edu.cn.JavaBeans.BlogBase;
import ahu.edu.cn.JavaBeans.CommentInfo;
import ahu.edu.cn.JavaBeans.PraiserInfo;
import ahu.edu.cn.JavaBeans.UserBase;
import ahu.edu.cn.JavaBeans.UserFans;
import ahu.edu.cn.JavaBeans.UserFollow;
import ahu.edu.cn.JavaBeans.WeiboUser;
import ahu.edu.cn.ServicesInterface.WeiboOperationServices;
import ahu.edu.cn.Util.subPage;
import ahu.edu.cn.enumerate.Sex;

@Controller
public class OperationController {
	@Autowired
	private WeiboOperationServices services;
	@RequestMapping("/query/like")
	@ResponseBody
	public String Like(Long blogId,Long userId){
		String result="";
		if(!services.PraiserIsExist(blogId,userId)){//当用户无点赞记录时
			PraiserInfo praiser =new PraiserInfo();
			praiser.setBlogId(blogId);
			praiser.setPaiserid(userId);
			praiser.setBypraiserid(services.findblogById(blogId).getBlogAuthor().getUserId());
			Date time = new Date();
			praiser.setTime(time);
			services.Praise(praiser,blogId);	
			result="0";
		}else{
			result="1";
		}
		System.out.println(result);
		return result;
	}
	@RequestMapping("/query/comment")
	@ResponseBody
	public List<CommentInfo> QueryComment(Long id){
		if(services.queryComment(id).isEmpty())return services.queryComment(id);
		else return services.queryComment(id);
		
	}
	@RequestMapping("/query/addcomment")
	@ResponseBody
	public String  AddComment(Long blogId,Long userId,String content){
		//String result=null;
		 services.addComment(blogId, userId, content);
		 //String content=request.getParameter("content");
		 String result ="分配成功";
		 return result;
	}
	@RequestMapping("/operation/transfer")
	@ResponseBody
	public String Transfer(Long blogId,Long userId,String content){
		String result="";
		services.transferBlog(blogId, userId, content);
		return result;
	}
	@RequestMapping("/operation/publish")
	@ResponseBody
	public String SendBlog(Long userId,String content){
		String result="1";
		services.sendBlog(userId, content);
		//result=isSendBlog+"publish";
		//System.out.print("result"+result);
		return result;
	}
	@RequestMapping("/operation/search_afterLogin")
	@ResponseBody
	public subPage<BlogBase> searchBlogAfterLogin(int page_num,String text,Long id){
		subPage<BlogBase> bloglist=services.searchBlog(text, page_num, 6);
		for(BlogBase blog : bloglist.getDataList()){
			if(services.PraiserIsExist(blog.getBlogId(),id)){
				blog.setUserLike("true");
			}
		}
		
		return bloglist;
		//return services.searchBlog(text, page_num, 6);
		
	}
	@RequestMapping("/query/user/fans")
	@ResponseBody
	public subPage<UserFans> getUserFans(Long id,int page_num){
		return services.queryUserFans(id,page_num,6);
	}
	@RequestMapping("/query/user/follower")
	@ResponseBody
	public subPage<UserFollow> getUserFollow(Long id,int page_num){
		return services.queryUserFollower(id,page_num,6);
	}
	@RequestMapping("/update/userinfo")
	@ResponseBody
	public String  updateUserInfo(String userName,String userSex,String userTel,String userEmail,String userLabels
			,String userBirth,String userAddress,String userIntroduction){
		String result="0";
		/*for(Sex sex:Sex.values()){
			switch(userSex)
			System.out.println(sex.toString());
		}-*/
		services.setUserInformation(userName,userSex,userTel,userEmail,userLabels,userBirth,userAddress,userIntroduction);
		System.out.println("result"+result);
		return result;
	}
	
}
