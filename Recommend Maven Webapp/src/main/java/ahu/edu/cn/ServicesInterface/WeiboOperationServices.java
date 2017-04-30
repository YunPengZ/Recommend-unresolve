package ahu.edu.cn.ServicesInterface;

import java.util.Collection;
import java.util.List;

import ahu.edu.cn.JavaBeans.BlogBase;
import ahu.edu.cn.JavaBeans.BlogInfo;
import ahu.edu.cn.JavaBeans.CommentInfo;
import ahu.edu.cn.JavaBeans.PraiserInfo;
import ahu.edu.cn.JavaBeans.TopicInfo;
import ahu.edu.cn.JavaBeans.UserBase;
import ahu.edu.cn.JavaBeans.UserFans;
import ahu.edu.cn.JavaBeans.UserFollow;
import ahu.edu.cn.Util.subPage;
/**
 * 用户操作接口-用户对微博操作
 * @author 朱
 *
 */
public interface WeiboOperationServices {
	
	/**
	 * 搜索微博、话题、用户
	 * @param text 搜索内容
	 * @param pageNum 第几页的数据
	 * @param pageSize 页面的数据数量
	 * @return subPage 对象
	 * */
	
	public subPage<BlogBase> searchBlog(String text, int PageNum, int PageSize);
	/**
	 * 发表微博
	 * @param userId 用户id
	 * @param content 微博内容
	 * @return bool值
	 * */
	public boolean sendBlog(Long userId, String content);
	/**
	 * 加载微博评论
	 * @param blogId 微博id
	 * @param commentNum 评论数
	 * @return List
	 * 
	 * */
	public List<CommentInfo> findComment(int blogId, int commentNum);
	/**
	 *发表评论
	 * @param blogId 微博id
	 * @param userId 用户id
	 * @param content 评论内容
	 * @return 
	 * */
	public void addComment(Long blogId, Long userId, String content);
	
	/**
	 * 判断是否存在点赞记录
	 * @param blogId 微博id
	 * @param userId 用户Id
	 * @return boolean
	 * */
	public boolean PraiserIsExist(Long blogId,Long userId);
	/**
	 * 点赞操作
	 * @param praiser PraiserInfo对象
	 * @param blogId 微博id
	 * */
	public void Praise(PraiserInfo praiser, Long blogId);
	
	/**
	 * 转发微博
	 * @param blogId 微博id
	 * @param userId 用户id
	 * @param content 转发内容
	 * @return bool值
	 * */
	public boolean transferBlog(Long blogId, Long userId,String content);
	/**
	 * 删除自己的微博
	 * @param blogId 微博id
	 * @param userId 用户id
	 * */
	public void deleteweibo(int blogId, Long userId);
	/**
	 * 导入与自己有关的评论
	 * @param userId 用户id
	 * @return List
	 * */
	public List<CommentInfo> queryComment(Long userId);
	/**
	 * @param userId 用户id
	 * @return List
	 * */
	public List<PraiserInfo> queryUserPraise(Long userId);
	/**
	 * 获得用户相关的信息
	 * @param userId 用户id
	 * @return 用户相关所有信息
	 * */
	public String GetRelateInfo(Long userId);
/**
 * 
 * @param blogId
 * @return
 */
	public BlogBase findblogById(Long blogId);
	/**
	 * 
	 * @param id
	 * @param page_num
	 * @param pageSize 
	 * @return
	 */
	public subPage<UserFans> queryUserFans(Long id, int page_num, int pageSize);
	/**
	 * 
	 * @param id
	 * @param page_num
	 * @return
	 */
	public subPage<UserFollow> queryUserFollower(Long id, int page_num,int pageSize);
	/**
	 * 
	 * @param userName
	 * @param userSex
	 * @param userTel
	 * @param userEmail
	 * @param userLabels
	 * @param userBirth
	 * @param userAddress
	 * @param userIntroduction
	 */
	public void setUserInformation(String userName, String userSex, String userTel, String userEmail, String userLabels,
			String userBirth, String userAddress, String userIntroduction);
	
}
