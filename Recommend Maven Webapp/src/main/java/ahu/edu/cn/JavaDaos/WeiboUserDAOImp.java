package ahu.edu.cn.JavaDaos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ahu.edu.cn.JavaBeans.BlogInfo;
import ahu.edu.cn.JavaBeans.PraiserInfo;
import ahu.edu.cn.JavaBeans.RelateUser;
import ahu.edu.cn.JavaBeans.UserBase;
import ahu.edu.cn.JavaBeans.UserFans;
import ahu.edu.cn.JavaBeans.UserFollow;
import ahu.edu.cn.JavaBeans.PraiserInfo;
import ahu.edu.cn.JavaBeans.WeiboUser;
import ahu.edu.cn.JavaDaosInterface.WeiboUserDao;
import ahu.edu.cn.ServicesInterface.WeiboUserServices;
import ahu.edu.cn.Util.HibernateUtil;
import ahu.edu.cn.Util.JdbcUtil;
import ahu.edu.cn.Util.subPage;
import ahu.edu.cn.enumerate.Sex;



/**
 * 用户登录，注册业务逻辑
 * @author 杨喆
 *
 */
@Repository
public class WeiboUserDAOImp implements WeiboUserDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 用户信息  WeiboUser 实例
	 */
	public WeiboUser Login(String username, String password) {
		WeiboUser user=null;
		Session session = sessionFactory.getCurrentSession();
		//获取符合查询条件的微博数量
		String Hql = "FROM WeiboUser WHERE userName=? and userPassword=?";
		Query query = session.createQuery(Hql);
		query.setString(0, username);
		query.setString(1, password);
		List<WeiboUser> UserResult=query.list();
		if(UserResult.isEmpty()||UserResult==null){
			
		}else{
			user=UserResult.get(0);
		}
		
		return user;
	}
	/**
	 * 保存用户信息到数据库中
	 * @param user WeiboUser 实例
	 * @return 
	 */
	@Override
	public WeiboUser Register(WeiboUser user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		WeiboUser userNew=null;
		String Hql = "FROM WeiboUser WHERE userName=?";
		Query query = session.createQuery(Hql);
		query.setString(0, user.getUserName());
		List<WeiboUser> UserResult=query.list();
		if(UserResult.isEmpty()||UserResult==null){
			
		}else{
			userNew=UserResult.get(0);
		}
		
		return userNew; 
	}
	/**
	 * 判断用户名是否存在于数据库中
	 * @param username 用户名
	 * @return 存在则返回true;不存在则返回false
	 */
	@Override
	public boolean UserIsExist(String username) {
		boolean isExist=false;
		WeiboUser user=null;
		Session session = sessionFactory.getCurrentSession();
		//获取符合查询条件的微博数量
		String Hql = "FROM WeiboUser WHERE userName=?";
		Query query = session.createQuery(Hql);
		query.setString(0, username);
		
		List<WeiboUser> UserResult=query.list();
		if(UserResult.isEmpty()||UserResult==null){
			
		}else{
			isExist=true;
		}
		
		return isExist;
	}
	/**
	 * 将标签存入数据库中
	 * @param userid 用户id
	 * @param labels 用户标签
	 * 
	 * */
	@Override
	public void setLabels(Long userid, String labels) {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("update WeiboUser as user set user.labels=?0 where user.id=?1").setParameter("0", labels).setParameter("1", userid).executeUpdate();
	}
	/**
	 * 
	 * 查找用户信息
	 * @param userId 用户id
	 * @return 用户信息类
	 * */
	@Override
	public WeiboUser getUserInfo(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql="FROM WeiboUser user where user.id=?";
		Query query=session.createQuery(hql);
		query.setParameter(0, userId);
		List<WeiboUser> user=query.list();
		if(user.isEmpty()||user==null)return null;
		else return user.get(0);
	}
//	/**
//	 * 为用户发表微博
//	 * @param userId 用户id
//	 * @param content 发微博的内容
//	 * @return 添加微博是否成功
//	 * 
//	 * */
//	@Override
//	public boolean sendBlog(Long userId, String content) {
//		Session session=sessionFactory.getCurrentSession();
//		//WeiboOperationDaoImp weibo_operation =new WeiboOperationDaoImp();
//		java.util.Date utilDate=new Date();
//		BlogInfo blog =new BlogInfo();
//		boolean state=true;
//		blog.setUserId(userId);
//		//blog.setUserName(findUserNameById(userId));
//		blog.setUserId(userId);
//		blog.setTime(utilDate);
//		blog.setContent(content);
//		session.save(blog);
//		Query query=session.createQuery("update WeiboUser user set user.blognum=user.blognum+1 where user.id=?");
//		query.setParameter(0,userId);
//		query.executeUpdate();
//		return state;
//	}
	public String findUserNameById(Long UserId)
	{
		Session session=sessionFactory.getCurrentSession();
		String UserName=session.createQuery("select username from WeiboUser user where user.id=?").setParameter(0, UserId).list().get(0).toString();
		System.out.println(UserName);
		return UserName;
		
	}
	/**
	 * 将用户个人简介添加到个人资料中
	 * @param userid 用户id
	 * @param user_introduction 用户简介
	 * 
	 * */
	@Override
	public void setIntroduction(Long userid, String user_introduction) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("update WeiboUser user set user.intro=?0 where user.id=?1");
		query.setParameter("0", user_introduction);
		query.setParameter("1", userid);
		query.executeUpdate();
	}
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
	@Override
	public void setInformation(Long userid, String tel, String email, String sex,String birth,String region) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("update WeiboUser user set user.tel=?0,user.email=?1,user.sex=?2,user.birth=?3,user.addr=?4 where user.id=?5");
		query.setParameter("0", tel);
		query.setParameter("1", email);
		query.setParameter("2", sex);
		query.setParameter("3", birth);
		query.setParameter("4", region);
		query.setParameter("5", userid);
		query.executeUpdate();
	}
	/**
	 * 更新评论相关列表
	 * @param UserId 用户id
	 * */
	@Override
	public void updateByReviewList(Long UserId) {
		Session session=sessionFactory.getCurrentSession();
		//RelateUser relation=new RelateUser();
		if(!checkByReviewList(UserId)){
			RelateUser relate=new RelateUser();
			relate.setUserid(UserId);
			Date time =new Date();
			relate.setRelatecomtime(time);
			session.save(relate);
		}else{
			Query query =session.createQuery("update RelateUser relate set relate.relatecomtime=?0 where relate.userid=?1");
			Date time =new Date();
			query.setParameter("0", time);
			query.setParameter("1", UserId);
			query.executeUpdate();
		}
		
	}
	private boolean checkByReviewList(Long userId) {
		Session session=sessionFactory.getCurrentSession();
		boolean checkstate=false;
		Query query =session.createQuery("select count(*) from RelateUser relate where relate.userid=?");
		query.setParameter(0, userId);
		Long record=(Long) query.uniqueResult();
		if(record!=0) checkstate=true;
		return checkstate;
	}
	/**
	 * 更新点赞相关列表
	 * @param UserId 用户id
	 * */
	@Override
	public void updateByPraiserList(Long UserId) {
		Session session=sessionFactory.getCurrentSession();
		//RelateUser relation=new RelateUser();
		if(!checkByPraiseList(UserId)){
			RelateUser relate=new RelateUser();
			relate.setUserid(UserId);
			Date time =new Date();
			relate.setRelatepratime(time);
			session.save(relate);
		}else{
			Query query =session.createQuery("update RelateUser relate set relate.relatepratime=?0 where relate.userid=?1");
			Date time =new Date();
			query.setParameter("0", time);
			query.setParameter("1", UserId);
			query.executeUpdate();
		}
		
	}
	private boolean checkByPraiseList(Long userId) {
		Session session=sessionFactory.getCurrentSession();
		boolean checkstate=false;
		Query query =session.createQuery("select count(*) from RelateUser relate where relate.userid=?");
		query.setParameter(0, userId);
		Long record=(Long) query.uniqueResult();
		if(record!=0) checkstate=true;
		return checkstate;
	}
	/**
	 * 获得用户关注列表
	 * @param pageNum 分页系统页号
	 * @param pageSize 每一页大小
	 * @param userId 用户id
	 * @return 返回分页结果
	 * */
	@Override
	public subPage<UserFollow> FindUserFollower(int pageNum, int pageSize, Long userId) {
		int totalRecord = 0;
		Session session = sessionFactory.getCurrentSession();
		//获取符合查询条件的微博数量
		String Hql = "SELECT COUNT(*) AS totalRecord from UserFollow user WHERE user.userid=?";
		Query query = session.createQuery(Hql);
		query.setParameter(0,userId);
		List list = query.list();
		totalRecord = ((Number) list.get(0)).intValue();
		if(totalRecord>300)totalRecord=300;
		System.out.println("follower"+totalRecord+userId);
		
		//如果PageNum页没有数据，那么PageNum的值被修改为最后一页
		if((pageNum-1)*pageSize>totalRecord){
			pageNum=totalRecord/pageSize+1;
		}
		List<UserFollow> bloglist = GetUserFollower(userId,pageNum, pageSize);
		subPage<UserFollow> Result = new subPage<UserFollow>(pageNum, pageSize, bloglist, totalRecord);
		return Result;
	}
	private List<UserFollow> GetUserFollower(Long userId, int pageNum, int pageSize) {
		List<UserFollow> followlist = new ArrayList<UserFollow>();
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UserFollow a  WHERE a.userid=? order by  a.id";

		Query query = session.createQuery(hql);
		query.setFirstResult((pageNum - 1) * pageSize);
		query.setMaxResults(pageSize);
		query.setLong(0, userId);
		followlist = query.list();
		return followlist;
	}
	/**
	 * 获得用户粉丝列表
	 * @param pageNum 分页系统页号
	 * @param pageSize 每一页大小
	 * @param userId 用户id
	 * @return 返回分页结果
	 * */
	@Override
	public subPage<UserFans> FindUserFans(int pageNum, int pageSize, Long userId) {
		int totalRecord = 0;
		Session session = sessionFactory.getCurrentSession();
		//获取符合查询条件的微博数量
		String Hql = "SELECT COUNT(*) AS totalRecord from UserFans user WHERE user.userid=?";
		Query query = session.createQuery(Hql);
		query.setLong(0,userId);
		List list = query.list();
		totalRecord = ((Number) list.get(0)).intValue();
		if(totalRecord>300)totalRecord=300;
		//System.out.println(totalRecord);
		
		//如果PageNum页没有数据，那么PageNum的值被修改为最后一页
		if((pageNum-1)*pageSize>totalRecord){
			pageNum=totalRecord/pageSize+1;
		}
		List<UserFans> bloglist = GetUserFans(userId,pageNum, pageSize);
		subPage<UserFans> Result = new subPage<UserFans>(pageNum, pageSize, bloglist, totalRecord);
		return Result;

}
	private List<UserFans> GetUserFans(Long userId, int pageNum, int pageSize) {
		List<UserFans> fanslist = new ArrayList<UserFans>();
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UserFans a  WHERE a.userid=? order by  a.id";

		Query query = session.createQuery(hql);
		query.setFirstResult((pageNum - 1) * pageSize);
		query.setMaxResults(pageSize);
		query.setLong(0, userId);
		fanslist = query.list();
		return fanslist;
	}
	@Override
	public List<UserBase> findWeiboUserById(Long userId) {
		
		Session session = sessionFactory.getCurrentSession();
		List<UserBase> userList=new ArrayList<UserBase>();
		UserBase user;
		String hql="select count(*) as weiboRecord from WeiboUser user where user.userId=?";
		Query query=session.createQuery(hql);
		query.setParameter(0, userId);
		//List<WeiboUser> userList=new ArrayList<WeiboUser>();
		int weiboTotalRecord = ((Number) query.list().get(0)).intValue();
		System.err.println("weiboTotalRecord"+weiboTotalRecord+"userId"+userId);
		if(weiboTotalRecord>0){
			user=getWeiboUser(userId);
		}
		else {
			String sinaSql="select count(*) as sinaRecord from sina_user where user_id=?";
			SQLQuery q=session.createSQLQuery(sinaSql);
			q.setParameter(0, userId);
			int sinaTotalRecord =((Number)q.list().get(0)).intValue();
			System.err.println("sinaTotalRecord"+sinaTotalRecord+"userId"+userId);
			if(sinaTotalRecord<=0)return null;
			user=getSinaUser(userId);
		}
		userList.add(user);
		return userList;
	}
	private UserBase getSinaUser(Long userId) {
		String sql="select user_id,user_address,user_birth,user_blog_num,"
				+ "user_email,user_fans_num,user_follow_num,user_introduction,"
				+ "user_labels,user_name,user_sex,user_tel from sina_user where user_id=?";
		Connection con = JdbcUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet res = null;
		UserBase user=null;
		try{
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, userId);
			res=pstm.executeQuery();
			while(res.next()){
				//Long userId = res.getLong("user_id");
				
				String userAddress = res.getString("user_address");
				Long userBlogNum = res.getLong("user_blog_num");
				Long userFansNum = res.getLong("user_fans_num");
				String userEmail = res.getString("user_email");
				Long userFollowNum = res.getLong("user_follow_num");
				String userIntroduction=res.getString("user_introduction");
				String userLabels= res.getString("user_labels");
				String userSex=res.getString("user_sex");
				String userTel=res.getString("user_tel");
				String userName=res.getString("user_name");
				java.sql.Date userBirth = res.getDate("user_birth");
				user=new UserBase(userName);
				user.setUserAddress(userAddress);
				user.setUserBirth(userBirth);
				user.setUserBlogNum(userBlogNum);
				user.setUserEmail(userEmail);
				user.setUserFansNum(userFansNum);
				user.setUserFollowNum(userFollowNum);
				user.setUserIntroduction(userIntroduction);
				user.setUserLabels(userLabels);
				///Sex userSex=
				//user.setUserSex("男");
				user.setUserTel(userTel);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			JdbcUtil.closeAll(con, pstm, res);
		}
		return user;
	}
	private UserBase getWeiboUser(Long userId) {
		String sql="select user_id,user_address,user_birth,user_blog_num,"
				+ "user_email,user_fans_num,user_follow_num,user_introduction,"
				+ "user_labels,user_name,user_sex,user_tel from web_user where user_id=?";
		Connection con = JdbcUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet res = null;
		UserBase user=null;
		try{
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, userId);
			res=pstm.executeQuery();
			while(res.next()){
				//Long userId = res.getLong("user_id");
				
				String userAddress = res.getString("user_address");
				Long userBlogNum = res.getLong("user_blog_num");
				Long userFansNum = res.getLong("user_fans_num");
				String userEmail = res.getString("user_email");
				Long userFollowNum = res.getLong("user_follow_num");
				String userIntroduction=res.getString("user_introduction");
				String userLabels= res.getString("user_labels");
				String userSex=res.getString("user_sex");
				String userTel=res.getString("user_tel");
				String userName=res.getString("user_name");
				java.sql.Date userBirth = res.getDate("user_birth");
				user=new UserBase(userName);
				user.setUserAddress(userAddress);
				user.setUserBirth(userBirth);
				user.setUserBlogNum(userBlogNum);
				user.setUserEmail(userEmail);
				user.setUserFansNum(userFansNum);
				user.setUserFollowNum(userFollowNum);
				user.setUserIntroduction(userIntroduction);
				user.setUserLabels(userLabels);
				///Sex userSex=
				//user.setUserSex("男");
				user.setUserTel(userTel);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			JdbcUtil.closeAll(con, pstm, res);
		}
		return user;
	}
}