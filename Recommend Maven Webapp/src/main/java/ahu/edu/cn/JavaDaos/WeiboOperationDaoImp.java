package ahu.edu.cn.JavaDaos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ahu.edu.cn.JavaBeans.BlogBase;
import ahu.edu.cn.JavaBeans.BlogInfo;
import ahu.edu.cn.JavaBeans.CommentInfo;
import ahu.edu.cn.JavaBeans.PraiserInfo;
import ahu.edu.cn.JavaBeans.TopicInfo;
import ahu.edu.cn.JavaBeans.UserBase;
import ahu.edu.cn.JavaBeans.UserFans;
import ahu.edu.cn.JavaBeans.UserFollow;
import ahu.edu.cn.JavaBeans.WeiboUser;
import ahu.edu.cn.JavaDaosInterface.WeiboOperationDao;
import ahu.edu.cn.Util.JdbcUtil;
import ahu.edu.cn.Util.subPage;

@Repository
public class WeiboOperationDaoImp implements WeiboOperationDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public subPage<BlogBase> searchBlog(String text, int PageNum, int PageSize) {
		Session session = sessionFactory.getCurrentSession();
		String web_hql="SELECT COUNT(*) AS weiTotalRecord from BlogInfo b  where b.blogContent like '%"+text+"%'";
		int webTotalRecord=((Number) session.createQuery(web_hql).list().get(0)).intValue();
		List<BlogBase> bloglist;
		String sina_sql="SELECT COUNT(*) AS sinaTotalRecord from sina_blog a where a.blog_content like '%"+text+"%'";
		int sinaTotalRecord=((Number) session.createSQLQuery(sina_sql).list().get(0)).intValue();
		int totalRecord=sinaTotalRecord+webTotalRecord;
		
		if((PageNum-1)*PageSize>totalRecord)PageNum=totalRecord/PageSize+1;
		if(totalRecord==0)bloglist= null;
		bloglist = GetSearchResultBlog(text,PageNum, PageSize);
		subPage<BlogBase> Result = new subPage<BlogBase>(PageNum, PageSize, bloglist, totalRecord);
		return Result;
	}
	
	private List<BlogBase> GetSearchResultBlog(String text, int PageNum, int PageSize) {
		List<BlogBase> bloglist = new ArrayList<BlogBase>();
		String sql = " SELECT a.blog_id+1000000 as blog_id, a.blog_content,a.blog_comment_num,a.blog_transfer_num,a.blog_like_num,a.blog_time,b.user_name authorName,"
				+ "c.blog_id refBlog_Id, c.blog_content refContent,c.blog_comment_num refCommentnum,c.blog_transfer_num refTransfernum,c.blog_like_num refPaisernum,c.blog_time refTime,d.user_name refauthorName FROM sina_blog a "
				+ "JOIN sina_user b ON a.blog_author_id=b.user_id "
				+ "LEFT JOIN sina_blog c ON a.blog_parent_id=c.blog_id "
				+ "LEFT JOIN sina_user d ON c.blog_author_id=d.user_id where a.blog_content like'%" +text+ "%'"
				+ " UNION "
				+ "SELECT a.blog_id, a.blog_content,a.blog_comment_num,a.blog_transfer_num,a.blog_like_num,a.blog_time,b.user_name authorName, "
				+ "c.blog_id refBlog_Id, c.blog_content refContent,c.blog_comment_num refCommentnum,c.blog_transfer_num refTransfernum,c.blog_like_num refPaisernum,c.blog_time refTime,d.user_name refauthorName FROM web_blog a "
				+ "JOIN web_user b ON a.blog_author_id=b.user_id "
				+ "LEFT JOIN web_blog c ON a.blog_parent_id=c.blog_id "
				+ "LEFT JOIN web_user d ON c.blog_author_id=d.user_id where a.blog_content like'%" +text+ "%'"
				+ " ORDER BY blog_time LIMIT ?, ?;";
		
		Connection con = JdbcUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, (PageNum - 1) * PageSize);
			pstm.setInt(2, PageSize);
			
			res = pstm.executeQuery();
			//System.out.println(res.getString("blog_content"));
			while (res.next()) {
				System.out.println(res.getString("blog_content"));
				Long blogId = res.getLong("blog_id");
				String authorName = res.getString("authorName");
				String content = res.getString("blog_content");
				Long commentNum = res.getLong("blog_comment_num");
				Long transferNum = res.getLong("blog_transfer_num");
				Long paiserNum = res.getLong("blog_like_num");
				Date time = res.getDate("blog_time");
				BlogBase blog = new BlogBase();
				blog.setBlogParent(null);
				if (res.getObject("refBlog_Id") != null) {
					Long refblogId = res.getLong("refBlog_Id");
					String refauthorName = res.getString("refauthorName");
					String refcontent = res.getString("refContent");
					Long refcommentNum = res.getLong("refCommentnum");
					Long reftransferNum = res.getLong("refTransfernum");
					Long refpaiserNum = res.getLong("refPaisernum");
					Date reftime = res.getDate("refTime");
					BlogBase refblog = new BlogBase();
					refblog.setBlogAuthor(new UserBase(authorName));
					refblog.setBlogId(refblogId);
					refblog.setBlogCommentNum(refcommentNum);
					refblog.setBlogContent(refcontent);
					refblog.setBlogParent(null);
					refblog.setBlogTime(reftime);
					refblog.setBlogTransferNum(reftransferNum);
					refblog.setBlogLikeNum(refpaiserNum);
					blog.setBlogParent(refblog);
				}
				blog.setBlogAuthor(new UserBase(authorName));
				blog.setBlogId(blogId);
				blog.setBlogCommentNum(commentNum);
				blog.setBlogContent(content);
				blog.setBlogLikeNum(paiserNum);
				blog.setBlogTime(time);
				blog.setBlogTransferNum(transferNum);
				System.out.println(blog.getBlogContent());
				bloglist.add(blog);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(con, pstm, res);
		}
		
		return bloglist;
	}

	@Override
	public List<CommentInfo> findComment(int blogId, int commentNum) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void addComment(Long blogId, Long userId, String content) {
		Session session = sessionFactory.getCurrentSession();
		CommentInfo comment=new CommentInfo();
		//SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date time = new java.util.Date();
		//System.out.println(utilDate+"java.util.Date");
		/**
		 * 需要改定，将点赞和评论的blogId改为1000000以上
		 */
		comment.setContent(content);
		comment.setReviewerId(userId);
		comment.setBlog(findBlogInfoById(blogId));
		comment.setTime(time);
		session.save(comment);
		if(blogId>1000000){
			String hql="update sina_blog as blog set blog.blog_comment_num=blog.blog_comment_num+1 where blog.blog_id=?0";
			SQLQuery query=session.createSQLQuery(hql);
			query.setParameter("0", blogId-1000000);
			query.executeUpdate();
		}
		else{
			String hql="update BlogInfo as blog set blog.blogCommentNum=blog.blogCommentNum+1 where blog.blogId=?0";
			Query query=session.createQuery(hql);
			query.setParameter("0", blogId);
			query.executeUpdate();
		}
		
		return ;
	}
	private BlogInfo findBlogInfoById(Long referId) {
		List<BlogInfo> bloglist = new ArrayList<BlogInfo>();
		String sql = "SELECT a.blog_id, a.blog_content,a.blog_comment_num,a.blog_transfer_num,a.blog_like_num,a.blog_time,b.user_name authorName, "
				+ "c.blog_id refBlog_Id, c.blog_content refContent,c.blog_comment_num refCommentnum,c.blog_transfer_num refTransfernum,c.blog_like_num refPaisernum,c.blog_time refTime,d.user_name refauthorName FROM web_blog a "
				+ "JOIN web_user b ON a.blog_author_id=b.user_id "
				+ "LEFT JOIN web_blog c ON a.blog_parent_id=c.blog_id "
				+ "LEFT JOIN web_user d ON c.blog_author_id=d.user_id where a.blog_id=?;";
				//不懂为什么这样写

		Connection con = JdbcUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, referId);
			
			res = pstm.executeQuery();
			//System.out.println(res.getString("blog_content"));
			if (res.next()) {
				System.out.println(res.getString("blog_content"));
				Long blogId = res.getLong("blog_id");
				String authorName = res.getString("authorName");
				String content = res.getString("blog_content");
				Long commentNum = res.getLong("blog_comment_num");
				Long transferNum = res.getLong("blog_transfer_num");
				Long paiserNum = res.getLong("blog_like_num");
				Date time = res.getDate("blog_time");
				BlogInfo blog = new BlogInfo();
				blog.setBlogParent(null);
				if (res.getObject("refBlog_Id") != null) {
					Long refblogId = res.getLong("refBlog_Id");
					String refauthorName = res.getString("refauthorName");
					String refcontent = res.getString("refContent");
					Long refcommentNum = res.getLong("refCommentnum");
					Long reftransferNum = res.getLong("refTransfernum");
					Long refpaiserNum = res.getLong("refPaisernum");
					Date reftime = res.getDate("refTime");
					BlogInfo refblog = new BlogInfo();
					refblog.setBlogAuthor(new WeiboUser());
					refblog.setBlogId(refblogId);
					refblog.setBlogCommentNum(refcommentNum);
					refblog.setBlogContent(refcontent);
					refblog.setBlogParent(null);
					refblog.setBlogTime(reftime);
					refblog.setBlogTransferNum(reftransferNum);
					refblog.setBlogLikeNum(refpaiserNum);
					blog.setBlogParent(refblog);
				}
				blog.setBlogAuthor(new WeiboUser());
				blog.setBlogId(blogId);
				blog.setBlogCommentNum(commentNum);
				blog.setBlogContent(content);
				blog.setBlogLikeNum(paiserNum);
				blog.setBlogTime(time);
				blog.setBlogTransferNum(transferNum);
				System.out.println(blog.getBlogContent());
				return blog;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(con, pstm, res);
		}
		return null;
	
	}

	@Override
	public boolean PraiserIsExist(Long blogId, Long userId) {
		Long PraiserNum=null;
		if(blogId>1000000)blogId-=1000000;
		boolean isExist=true;
		Session session = sessionFactory.getCurrentSession();
		//获取符合查询条件的微博数量
		try{
		Query query = session.createQuery("select count(*) FROM PraiserInfo WHERE blogId=?0 and paiserid=?1");
		//query.setString(0, username);
		query.setParameter("0", blogId);
		query.setParameter("1", userId);
		PraiserNum=(Long)query.uniqueResult();
		 System.out.print("记录数"+PraiserNum);
		 if(PraiserNum==0)isExist=false;//查询不到记录时说明该用户未对该微博点赞
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	 return isExist;
	}

	@Override
	public void Praise(PraiserInfo praiser, Long blogId) {
		Session session = sessionFactory.getCurrentSession();
		session.save(praiser);
		if(blogId>1000000){
			session.createQuery("update sina_blog as blog set blog.blog_like_num=blog.blog_like_num+1 where blog.blog_id=?0").setParameter("0", blogId-1000000).executeUpdate();
		}
		else{
			session.createQuery("update BlogInfo as blog set blog.blogLikeNum=blog.blogLikeNum+1 where blog.blogId=?0").setParameter("0", blogId).executeUpdate();
		}
		
		
	}

	@Override
	public boolean transferBlog(Long blogId, Long userId, String content) {
		Session session=sessionFactory.getCurrentSession();
		//boolean state=false;
		java.util.Date utilDate=new java.util.Date();
		Date sqlDate=new Date(utilDate.getTime());
		BlogInfo blog =new BlogInfo();
		boolean state=true;
		System.out.println(blogId+"blogId");
		blog.setBlogAuthor(findWeiboUserByUserId(userId));
		//blog.setUserName(findUserNameById(userId));
		blog.setBlogParent(findBlogInfoById(blogId));
		blog.setBlogContent(content);
		blog.setBlogTime(sqlDate);
		session.save(blog);
		if(blogId>1000000){
			session.createSQLQuery("update sina_blog as blog set blog.blog_transfer_num=blog.blog_transfer_num+1 where blog.blog_id=?").setParameter(0, blogId-1000000).executeUpdate();
		}
		else
		session.createQuery("update BlogInfo as blog set blog.blogTransferNum=blog.blogTransferNum+1 where blog.blogId=?").setParameter(0, blogId).executeUpdate();
		return state;
	}
	private WeiboUser findWeiboUserByUserId(Long userId) {
		Session session=sessionFactory.getCurrentSession();
		System.out.println(userId);
		Query query=session.createQuery("from WeiboUser user where user.id=?");
		WeiboUser user=(WeiboUser) query.setParameter(0, userId).list().get(0);
		//System.out.println(UserName);
		return user;
	}

	/**
	 * 删除自己的微博
	 * @param blogId 微博id
	 * @param UserId 用户id
	 * */
	@Override
	public void deleteweibo(int blogId, Long UserId) {
		Session session=sessionFactory.getCurrentSession();
		Query query =session.createQuery("delete from BlogInfo blog where blog.blogId=?");
		query.setParameter(0, blogId).executeUpdate();
		//updateUserBlogNum(blogId,UserId);
	}
	private void updateUserBlogNum(int blogId,Long UserId) {
		Session session=sessionFactory.getCurrentSession();
		Query query =session.createQuery("update WeiboUser as user set user.blognum=user.blognum-1 where user.id=?");
		query.setParameter(0, UserId).executeUpdate();
	}
	@Override
	public List<CommentInfo> queryComment(Long blogId) {
		if(blogId>1000000)blogId-=1000000;
		List<CommentInfo> commentList=new ArrayList<CommentInfo>();
		Session session = sessionFactory.getCurrentSession();
		String hql="select comment_id,blog_id,reviewer_id,content,comment_parent_id,time from web_comment where blog_id=? order by time desc";
		Connection con = JdbcUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			pstm = con.prepareStatement(hql);
			System.out.println(blogId);
			pstm.setLong(1,blogId);
			res = pstm.executeQuery();
			while (res.next()) {
				CommentInfo comment=new CommentInfo();
				Long commentId=res.getLong("comment_id");
				Long reviewerId=res.getLong("reviewer_id");
				String content=res.getString("content");
				Long commentParentId=res.getLong("comment_parent_id");
				Date date=res.getDate("time");
				comment.setBlog(findBlogInfoById(blogId));
				comment.setCommentId(commentId);
				comment.setContent(content);
				comment.setReviewerId(reviewerId);
				comment.setTime(date);
				commentList.add(comment);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.closeAll(con, pstm, res);
		}
		//List<CommentInfo> commentList=query.list();
		//System.out.println(commentList.get(0).getContent());
		return commentList;
	}
	@Override
	public List<PraiserInfo> queryUserPraise(Long userId) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String GetRelateInfo(Long userId) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public BlogBase findBlogById(Long referId) {
		List<BlogBase> bloglist = new ArrayList<BlogBase>();
		String sql = "SELECT a.blog_id, a.blog_content,a.blog_comment_num,a.blog_transfer_num,a.blog_like_num,a.blog_time,b.user_name authorName, "
				+ "c.blog_id refBlog_Id, c.blog_content refContent,c.blog_comment_num refCommentnum,c.blog_transfer_num refTransfernum,c.blog_like_num refPaisernum,c.blog_time refTime,d.user_name refauthorName FROM web_blog a "
				+ "JOIN web_user b ON a.blog_author_id=b.user_id "
				+ "LEFT JOIN web_blog c ON a.blog_parent_id=c.blog_id "
				+ "LEFT JOIN web_user d ON c.blog_author_id=d.user_id where a.blog_id=?;";
				//不懂为什么这样写

		Connection con = JdbcUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, referId);
			
			res = pstm.executeQuery();
			//System.out.println(res.getString("blog_content"));
			if (res.next()) {
				System.out.println(res.getString("blog_content"));
				Long blogId = res.getLong("blog_id");
				String authorName = res.getString("authorName");
				String content = res.getString("blog_content");
				Long commentNum = res.getLong("blog_comment_num");
				Long transferNum = res.getLong("blog_transfer_num");
				Long paiserNum = res.getLong("blog_like_num");
				Date time = res.getDate("blog_time");
				BlogBase blog = new BlogBase();
				blog.setBlogParent(null);
				if (res.getObject("refBlog_Id") != null) {
					Long refblogId = res.getLong("refBlog_Id");
					String refauthorName = res.getString("refauthorName");
					String refcontent = res.getString("refContent");
					Long refcommentNum = res.getLong("refCommentnum");
					Long reftransferNum = res.getLong("refTransfernum");
					Long refpaiserNum = res.getLong("refPaisernum");
					Date reftime = res.getDate("refTime");
					BlogBase refblog = new BlogBase();
					refblog.setBlogAuthor(new UserBase(authorName));
					refblog.setBlogId(refblogId);
					refblog.setBlogCommentNum(refcommentNum);
					refblog.setBlogContent(refcontent);
					refblog.setBlogParent(null);
					refblog.setBlogTime(reftime);
					refblog.setBlogTransferNum(reftransferNum);
					refblog.setBlogLikeNum(refpaiserNum);
					blog.setBlogParent(refblog);
				}
				blog.setBlogAuthor(new UserBase(authorName));
				blog.setBlogId(blogId);
				blog.setBlogCommentNum(commentNum);
				blog.setBlogContent(content);
				blog.setBlogLikeNum(paiserNum);
				blog.setBlogTime(time);
				blog.setBlogTransferNum(transferNum);
				System.out.println(blog.getBlogContent());
				return blog;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(con, pstm, res);
		}
		return null;
	}

	@Override
	public boolean sendBlog(Long userId, String content) {
		Session session=sessionFactory.getCurrentSession();
		//WeiboOperationDaoImp weibo_operation =new WeiboOperationDaoImp();
		java.util.Date utilDate=new java.util.Date();
		Date sqlDate=new Date(utilDate.getTime());
		BlogInfo blog =new BlogInfo();
		boolean state=true;
		blog.setBlogAuthor(findWeiboUserByUserId(userId));
		blog.setBlogTime(sqlDate);
		blog.setBlogCommentNum((long) 0);
		blog.setBlogCosSimilar(0);
		blog.setBlogHotDegree(0);
		blog.setBlogLikeNum((long)0);
		blog.setBlogTransferNum((long)0);
		blog.setBlogContent(content);
		session.save(blog);
		Query query=session.createQuery("update WeiboUser user set user.userBlogNum=user.userBlogNum+1 where user.userId=?");
		query.setParameter(0,userId);
		query.executeUpdate();
		return state;
	}

	@Override
	public subPage<UserFollow> queryUserFollower(Long userId, int pageNum,int pageSize) {
		int totalRecord = 0;
		Session session = sessionFactory.getCurrentSession();
		//获取符合查询条件的微博数量
		String Hql = "SELECT COUNT(*) AS totalRecord from UserFollow user WHERE user.userid=?";
		Query query = session.createQuery(Hql);
		query.setParameter(0,userId);
		List list = query.list();
		totalRecord = ((Number) list.get(0)).intValue();
		if(totalRecord>300)totalRecord=300;
		//System.out.println("follower"+totalRecord+userId);
		
		//如果PageNum页没有数据，那么PageNum的值被修改为最后一页
		if((pageNum-1)*pageSize>totalRecord){
			pageNum=totalRecord/pageSize+1;
		}
		List<UserFollow> followList = GetUserFollower(userId,pageNum, pageSize);
		subPage<UserFollow> Result = new subPage<UserFollow>(pageNum, pageSize, followList, totalRecord);
		return Result;
	}

	private List<UserFollow> GetUserFollower(Long userId, int pageNum, int pageSize) {
		//TopicHotDegree();
		List<UserFollow> followlist = new ArrayList<UserFollow>();
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UserFollow a  WHERE a.userid=? order by  a.id";

		Query query = session.createQuery(hql);
		query.setFirstResult((pageNum - 1) * pageSize);
		query.setMaxResults(pageSize);
		query.setLong(0, userId);
		followlist = query.list();
		//System.out.println("followname"+followlist.get(0).getFollowername());
		return followlist;
	}

	@Override
	public subPage<UserFans> queryUserFans(Long userId, int pageNum,int pageSize) {
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
	public Object setUserInformation(String userName, String userSex, String userTel, String userEmail,
			String userLabels, String userBirth, String userAddress, String userIntroduction) {
		// TODO 自动生成的方法存根
		return null;
	}
}
