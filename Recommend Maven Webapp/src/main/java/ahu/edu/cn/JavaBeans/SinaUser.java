package ahu.edu.cn.JavaBeans;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import ahu.edu.cn.enumerate.Sex;
@Entity
@Table(name="sina_user")
public class SinaUser {
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
    /**
     * 与用户所发的微博形成一对多的映射
     */
	private Set<BlogInfo> blogset=new HashSet<BlogInfo>();
	
	public SinaUser() {
	}
	
	
	@OneToMany
	@JoinColumn(name="blog_author_id")
	public Set<BlogInfo> getBlogset() {
		return blogset;
	}
	public void setBlogset(Set<BlogInfo> blogset) {
		this.blogset = blogset;
	}

	@Id
	@Column(name="user_id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment",strategy="increment")
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	

    @Column(name="user_name")
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


    @Column(name="user_sex")
    @Enumerated(EnumType.STRING)
	public Sex getUserSex() {
		return userSex;
	}


	public void setUserSex(Sex userSex) {
		this.userSex = userSex;
	}

    @Column(name="user_tel")
	public String getUserTel() {
		return userTel;
	}


	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

    @Column(name="user_email")
	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

    @Column(name="user_labels")
	public String getUserLabels() {
		return userLabels;
	}


	public void setUserLabels(String userLabels) {
		this.userLabels = userLabels;
	}

    @Column(name="user_birth")
	public Date getUserBirth() {
		return userBirth;
	}


	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}

    @Column(name="user_address")
	public String getUserAddress() {
		return userAddress;
	}


	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

    @Column(name="user_blog_num")
	public Long getUserBlogNum() {
		return userBlogNum;
	}


	public void setUserBlogNum(Long userBlogNum) {
		this.userBlogNum = userBlogNum;
	}

    @Column(name="user_follow_num")
	public Long getUserFollowNum() {
		return userFollowNum;
	}


	public void setUserFollowNum(Long userFollowNum) {
		this.userFollowNum = userFollowNum;
	}

    @Column(name="user_fans_num")
	public Long getUserFansNum() {
		return userFansNum;
	}


	public void setUserFansNum(Long userFansNum) {
		this.userFansNum = userFansNum;
	}

    @Column(name="user_introduction")
	public String getUserIntroduction() {
		return userIntroduction;
	}


	public void setUserIntroduction(String userIntroduction) {
		this.userIntroduction = userIntroduction;
	}

}
