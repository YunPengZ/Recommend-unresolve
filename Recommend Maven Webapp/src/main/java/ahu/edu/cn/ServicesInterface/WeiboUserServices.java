package ahu.edu.cn.ServicesInterface;

import java.util.List;

import ahu.edu.cn.JavaBeans.PraiserInfo;
import ahu.edu.cn.JavaBeans.UserBase;
import ahu.edu.cn.JavaBeans.UserFans;
import ahu.edu.cn.JavaBeans.UserFollow;
import ahu.edu.cn.JavaBeans.WeiboUser;
import ahu.edu.cn.Util.subPage;

public interface WeiboUserServices {
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 用户
	 * @return WeiboUser对象
	 */
	public WeiboUser Login(String username,String password);
	/**
	 * 用户注册
	 * @param user WeiboUser对象
	 * @return 
	 */
	public WeiboUser Register(WeiboUser user);
	/**
	 * 用户是否已经注册
	 * @param username 用户
	 * @return 布尔值
	 */
	public boolean UserIsExist(String username);
	/**
	 * 将用户标签存放到数据库
	 * @param userid 用户id
	 * @param labels 用户标签
	 * */
	public void setLabels(Long userid,String labels);
	/**
	 * 查找个人信
	 * @param userId 用户id
	 * @return WeiboUser对象
	 * */
	public WeiboUser getUserInfo(Long userId);
	/**
	 * 将用户个人简介存放到个人资料中
	 * @param userid 用户id
	 * @param user_introduction 
	 * */
	public void setIntroduction(Long userid, String user_introduction);
	/**
	 * 将用户信息存储放在一个函数功能中
	 * @param userid 用户id
	 * @param email 邮箱
	 * @param tel 用户电话
	 * @param sex 性别
	 * @param birth 生日
	 * @param region 籍贯
	 */
	public void setInformation(Long userid,String tel, String email, String sex, String birth, String region);
	/**
	 *更新评论消息队列中的时间参数
	 *@param userId 用户id
	 * */
	public void updateByReviewList(Long userId);
	/**
	 * @param userId 用户id
	 * */
	public void updateByPraiserList(Long userId);
	/**
	 * 获得用户关注列表
	 * @param pageNum 分页系统页号
	 * @param pageSize 每一页大小
	 * @param userId 用户id
	 * @return 返回分页结果
	 * */
	public subPage<UserFollow> FindUserFollower(int pageNum, int pageSize, Long userId);
	/**
	 * 获得用户关注列表
	 * @param pageNum 分页系统页号
	 * @param pageSize 每一页大小
	 * @param userId 用户id
	 * @return 返回分页结果
	 * */
	public subPage<UserFans> FindUserFans(int pageNum, int pageSize, Long userId);
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserBase> findWeiboUserById(Long userId);
}
