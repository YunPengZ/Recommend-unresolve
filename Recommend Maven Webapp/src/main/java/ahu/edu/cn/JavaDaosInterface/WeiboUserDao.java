package ahu.edu.cn.JavaDaosInterface;

import java.util.List;

import ahu.edu.cn.JavaBeans.PraiserInfo;
import ahu.edu.cn.JavaBeans.UserBase;
import ahu.edu.cn.JavaBeans.UserFans;
import ahu.edu.cn.JavaBeans.UserFollow;
import ahu.edu.cn.JavaBeans.WeiboUser;
import ahu.edu.cn.Util.subPage;
/**
 * 用户操作接口-用户对用户操作
 * @author 朱
 *
 */
public interface WeiboUserDao {
	/**
	 * 用户登录
	 * @param username 用户名 
	 * @param password 密码
	 * @return 登录后的用户函数
	 */
	public WeiboUser Login(String username,String password);
	/**
	 * 用户注册
	 * @param user 注册已封装的用户user
	 * @return 
	 */
	public WeiboUser Register(WeiboUser user);
	/**
	 * 用户是否已经注册
	 * @param username 用户名
	 * @return 返回布尔变量，检查用户是否存在
	 */ 
	public boolean UserIsExist(String username);
	/**
	 * 将标签存入数据库中
	 * @param userid 用户id
	 * @param labels 用户标签
	 * 
	 * */
	public void setLabels(Long userid,String labels);
	/**
	 * 
	 * 查找用户信息
	 * @param userId 用户id
	 * @return 用户信息类
	 * */
	public WeiboUser getUserInfo(Long userId);
//	/**
//	 * 为用户发表微博
//	 * @param userId 用户id
//	 * @param content 发微博的内容
//	 * @return 添加微博是否成功
//	 * 
//	 * */
//	public boolean sendBlog(Long userId, String content);
	/**
	 * 将用户个人简介添加到个人资料中
	 * @param userid 用户id
	 * @param user_introduction 用户简介
	 * 
	 * */
	public void setIntroduction(Long userid, String user_introduction);
	/**
	 * 存储用户信息
	 * @param userid 用户id
	 * @param tel 电话
	 * @param email 邮箱
	 * @param sex 性别
	 * @param birth 生日
	 * @param region 籍贯
	 *
	 * */
	public void setInformation(Long userid, String tel, String email, String sex, String birth, String region);
	/**
	 * 更新评论相关列表
	 * @param userId 用户id
	 * */
	public void updateByReviewList(Long userId);
	/**
	 * 更新点赞相关列表
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
	 * 获得用户粉丝列表
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
