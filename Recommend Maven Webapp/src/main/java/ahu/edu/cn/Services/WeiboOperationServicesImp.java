package ahu.edu.cn.Services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ahu.edu.cn.JavaBeans.BlogBase;
import ahu.edu.cn.JavaBeans.BlogInfo;
import ahu.edu.cn.JavaBeans.CommentInfo;
import ahu.edu.cn.JavaBeans.PraiserInfo;
import ahu.edu.cn.JavaBeans.TopicInfo;
import ahu.edu.cn.JavaBeans.UserBase;
import ahu.edu.cn.JavaBeans.UserFans;
import ahu.edu.cn.JavaBeans.UserFollow;
import ahu.edu.cn.JavaDaosInterface.WeiboOperationDao;
import ahu.edu.cn.ServicesInterface.WeiboOperationServices;
import ahu.edu.cn.Util.subPage;

public class WeiboOperationServicesImp implements WeiboOperationServices{
	
	@Autowired
	WeiboOperationDao operationDao;
	public WeiboOperationDao getOperationDao() {
		return operationDao;
	}

	public void setOperationDao(WeiboOperationDao operationDao) {
		this.operationDao = operationDao;
	}

	

	/*
	 * 实现查询微博、话题、用户
	 * */
	@Override
	public subPage<BlogBase> searchBlog(String text, int PageNum, int PageSize) {
		return operationDao.searchBlog(text, PageNum, PageSize);
	}

	/*
	 * 加载微博评论
	 * */
	@Override
	public List<CommentInfo> findComment(int blogId, int commentNum) {
		return operationDao.findComment( blogId, commentNum);
	}

	@Override
	public void addComment(Long blogId, Long userId, String content) {
		operationDao.addComment( blogId, userId,content);
		return ;
	}


	@Override
	public boolean PraiserIsExist(Long blogId, Long userId) {
		return operationDao.PraiserIsExist(blogId,userId);
	}

	@Override
	public void Praise(PraiserInfo praiser,Long blogId) {
		operationDao.Praise(praiser,blogId);
	}

	/*
	 * 转发微博
	 * */
	@Override
	public boolean transferBlog(Long blogId, Long userId, String content) {
		return operationDao.transferBlog(blogId, userId,content);
	}

	@Override
	public void deleteweibo(int blogId,Long UserId) {
		operationDao.deleteweibo(blogId,UserId);
	}

	@Override
	public List<CommentInfo> queryComment(Long userId) {
		return operationDao.queryComment(userId);
	}
	@Override
	public List<PraiserInfo> queryUserPraise(Long userId) {
		
		return operationDao.queryUserPraise(userId);
	}

	@Override
	public String GetRelateInfo(Long userId) {
		return operationDao.GetRelateInfo(userId);
	}

	@Override
	public BlogBase findblogById(Long blogId) {

		return operationDao.findBlogById(blogId);
	}

	@Override
	public boolean sendBlog(Long userId, String content) {
		return operationDao.sendBlog(userId,content);
	}

	@Override
	public subPage<UserFans> queryUserFans(Long id, int page_num,int pageSize) {
		return operationDao.queryUserFans(id,page_num,pageSize);
	}

	@Override
	public subPage<UserFollow> queryUserFollower(Long id, int page_num,int pageSize) {
		return operationDao.queryUserFollower(id,page_num,pageSize);
	}

	@Override
	public void setUserInformation(String userName, String userSex, String userTel, String userEmail, String userLabels,
			String userBirth, String userAddress, String userIntroduction) {
		 operationDao.setUserInformation(userName,userSex,userTel,userEmail,userLabels,userBirth,userAddress,userIntroduction);
		 return ;
	}
}
