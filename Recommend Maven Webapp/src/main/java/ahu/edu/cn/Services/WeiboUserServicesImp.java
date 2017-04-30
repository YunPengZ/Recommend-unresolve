package ahu.edu.cn.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ahu.edu.cn.JavaBeans.PraiserInfo;
import ahu.edu.cn.JavaBeans.UserBase;
import ahu.edu.cn.JavaBeans.UserFans;
import ahu.edu.cn.JavaBeans.UserFollow;
import ahu.edu.cn.JavaBeans.WeiboUser;

import ahu.edu.cn.JavaDaosInterface.WeiboUserDao;
import ahu.edu.cn.ServicesInterface.WeiboUserServices;
import ahu.edu.cn.Util.subPage;
@Service
public class WeiboUserServicesImp implements WeiboUserServices {
	@Autowired
	private WeiboUserDao userDao;
	
	public WeiboUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(WeiboUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public WeiboUser Login(String username, String password) {
		
		return userDao.Login(username, password);
	}

	@Override
	public WeiboUser Register(WeiboUser user) {
		return userDao.Register(user);

	}

	@Override
	public void setLabels(Long userid, String labels) {
		userDao.setLabels(userid, labels);
	}
	@Override
	public boolean UserIsExist(String username) {
		return userDao.UserIsExist(username);
	}

	@Override
	public WeiboUser getUserInfo(Long userId) {
		return userDao.getUserInfo(userId);
	}

//	@Override
//	public boolean sendBlog(Long userId, String content) {
//		return weibo_user_dao.sendBlog(userId,content);
//	}

	@Override
	public void setIntroduction(Long userid, String user_introduction) {
		userDao.setIntroduction(userid, user_introduction);
		
	}

	@Override
	public void setInformation(Long userid, String tel, String email, String sex,String birth,String region) {
		userDao.setInformation(userid,tel,email,sex,birth,region);		
	}

	@Override
	public void updateByReviewList(Long UserId) {
		userDao.updateByReviewList(UserId);
	}

	@Override
	public void updateByPraiserList(Long userId) {
		userDao.updateByPraiserList(userId);
	}

	@Override
	public subPage<UserFollow> FindUserFollower(int pageNum, int pageSize, Long userId) {
		
		return userDao.FindUserFollower(pageNum,pageSize,userId);
	}

	@Override
	public subPage<UserFans> FindUserFans(int pageNum, int pageSize, Long userId) {
		return userDao.FindUserFans(pageNum,pageSize,userId);
	}

	@Override
	public List<UserBase> findWeiboUserById(Long userId) {
		return userDao.findWeiboUserById(userId);
	}

}
