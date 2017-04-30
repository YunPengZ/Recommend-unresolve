package ahu.edu.cn.JavaBeans;

import java.sql.Date;

import ahu.edu.cn.enumerate.Sex;

public class UserBase {
	private Long userId;
	private String userName;
	private Sex userSex;
	private String userTel;
	private String userEmail;
	private String userLabels;
	private Date userBirth;
	private String userAddress;
	private Long  userBlogNum;
	private Long userFollowNum;
	private Long userFansNum;
	private String userIntroduction;
	public UserBase(String userName) {
		super();
		this.userName = userName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Sex getUserSex() {
		return userSex;
	}
	public void setUserSex(Sex userSex) {
		this.userSex = userSex;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserLabels() {
		return userLabels;
	}
	public void setUserLabels(String userLabels) {
		this.userLabels = userLabels;
	}
	public Date getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public Long getUserBlogNum() {
		return userBlogNum;
	}
	public void setUserBlogNum(Long userBlogNum) {
		this.userBlogNum = userBlogNum;
	}
	public Long getUserFollowNum() {
		return userFollowNum;
	}
	public void setUserFollowNum(Long userFollowNum) {
		this.userFollowNum = userFollowNum;
	}
	public Long getUserFansNum() {
		return userFansNum;
	}
	public void setUserFansNum(Long userFansNum) {
		this.userFansNum = userFansNum;
	}
	public String getUserIntroduction() {
		return userIntroduction;
	}
	public void setUserIntroduction(String userIntroduction) {
		this.userIntroduction = userIntroduction;
	}
	
	
}
