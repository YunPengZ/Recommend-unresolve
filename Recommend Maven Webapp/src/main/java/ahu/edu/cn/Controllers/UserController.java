package ahu.edu.cn.Controllers;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ahu.edu.cn.JavaBeans.UserBase;
import ahu.edu.cn.JavaBeans.WeiboUser;
import ahu.edu.cn.ServicesInterface.WeiboUserServices;
import ahu.edu.cn.enumerate.Sex;

@Controller
public class UserController {
    @Autowired
	public WeiboUserServices userServices;

    
	@RequestMapping("/register/check/username")
	@ResponseBody
	public  String isExist(String userName){
		boolean is=userServices.UserIsExist(userName);
		String result = is==true?"0":"1";
		return result;
	}
	@RequestMapping("/register/save")
	@ResponseBody
	public String register(WeiboUser user,HttpServletRequest request){
		String result;
		WeiboUser userNew=userServices.Register(user);
		if(user == null){
			result = "0";
		}else{
			request.getSession().setAttribute("user", userNew);
			result = "1";
		}
		return result;
		
	}
	
	@RequestMapping("/login/check/username")
	@ResponseBody
	public String loginExist(String userName){
		boolean is=userServices.UserIsExist(userName);
		String result = is==true?"0":"1";
		return result;
		
	}
	
	@RequestMapping("/login/log")
	@ResponseBody
	public String login(String userName,String userPassword,HttpServletRequest request){
	    String result;
		WeiboUser user=userServices.Login(userName, userPassword);
		if(user == null){
			result = "0";
		}else{
			request.getSession().setAttribute("user", user);
			result = "1";
		}
		return  result;
		
	}
	@RequestMapping("/user/getinfo")
	@ResponseBody
	public List<UserBase> getUserInfo(Long userId){
		return userServices.findWeiboUserById(userId);
	}

}
