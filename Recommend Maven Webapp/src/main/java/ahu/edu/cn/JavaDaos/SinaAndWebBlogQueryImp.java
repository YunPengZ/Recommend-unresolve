package ahu.edu.cn.JavaDaos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ahu.edu.cn.JavaBeans.BlogBase;
import ahu.edu.cn.JavaBeans.BlogInfo;
import ahu.edu.cn.JavaBeans.UserBase;
import ahu.edu.cn.JavaDaosInterface.SinaAndWebBlogQueryDAO;
import ahu.edu.cn.Util.JdbcUtil;
import ahu.edu.cn.Util.subPage;

@Repository
public class SinaAndWebBlogQueryImp implements SinaAndWebBlogQueryDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public subPage<BlogBase> findAllBlog(int pageNum, int pageSize,int recState) {
		if(recState<=0)return findRandomRec(pageNum, pageSize);
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT COUNT(*) AS totalRecord FROM web_blog a "
				+ "JOIN web_user b ON a.blog_author_id=b.user_id "
				+ "LEFT JOIN web_blog c ON a.blog_parent_id=c.blog_id "
				+ "LEFT JOIN web_user d ON c.blog_author_id=d.user_id where d.user_labels=?";
		SQLQuery query = session.createSQLQuery(sql);
		query.setParameter(0, String.valueOf(recState));
		List list = query.list();
		List<BlogBase> bloglist;
		int totalRecord = ((Number) list.get(0)).intValue();
		String sinaSql =  "SELECT COUNT(*) AS totalRecord FROM sina_blog a "
				+ "JOIN sina_user b ON a.blog_author_id=b.user_id "
				+ "LEFT JOIN sina_blog c ON a.blog_parent_id=c.blog_id "
				+ "LEFT JOIN sina_user d ON c.blog_author_id=d.user_id where d.user_labels=?";
		query = session.createSQLQuery(sinaSql);
		query.setParameter(0, String.valueOf(recState));
		list = query.list();
		totalRecord += ((Number) list.get(0)).intValue();
		
		// 如果PageNum页没有数据，那么PageNum的值被修改为最后一页
		if ((pageNum - 1) * pageSize > totalRecord) {
			pageNum = totalRecord / pageSize + 1;
		}
		System.out.println(totalRecord);
		if(totalRecord<=0)bloglist= null;
		else bloglist = GetList(pageNum, pageSize,recState);
		//System.out.println(bloglist.get(0).getBlogContent() +"   "+ bloglist.get(0).getBlogAuthor().getUserName());
		subPage<BlogBase> Result = new subPage<BlogBase>(pageNum, pageSize,
				bloglist, totalRecord);
		return Result;

	}

	private subPage<BlogBase> findRandomRec(int pageNum, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT COUNT(*) AS totalRecord FROM BlogInfo";
		Query query = session.createQuery(hql);
		List list = query.list();
		List<BlogBase> bloglist;
		int totalRecord = ((Number) list.get(0)).intValue();
		String sinaSql =  "SELECT COUNT(*) AS totalRecord FROM SinaBlogInfo";
		query = session.createQuery(sinaSql);
		list = query.list();
		totalRecord += ((Number) list.get(0)).intValue();
		
		// 如果PageNum页没有数据，那么PageNum的值被修改为最后一页
		if ((pageNum - 1) * pageSize > totalRecord) {
			pageNum = totalRecord / pageSize + 1;
		}
		System.out.println(totalRecord);
		if(totalRecord<=0)bloglist= null;
		else bloglist = GetRandomRecList(pageNum, pageSize);
		//System.out.println(bloglist.get(0).getBlogContent() +"   "+ bloglist.get(0).getBlogAuthor().getUserName());
		subPage<BlogBase> Result = new subPage<BlogBase>(pageNum, pageSize,
				bloglist, totalRecord);
		return Result;

	}

	private List<BlogBase> GetRandomRecList(int pageNum, int pageSize) {
		List<BlogBase> bloglist = new ArrayList<BlogBase>();
		String sql = " SELECT distinct a.blog_id+1000000 as blog_id, a.blog_content,a.blog_comment_num,a.blog_transfer_num,a.blog_like_num,a.blog_time,"
				+ "b.user_name authorName, b.user_id userId,b.user_blog_num userBlogNum,b.user_fans_num userFansNum,b.user_follow_num userFollowNum,"
				+ "c.blog_id refBlog_Id, c.blog_content refContent,c.blog_comment_num refCommentnum,c.blog_transfer_num refTransfernum,c.blog_like_num refPaisernum,c.blog_time refTime,d.user_name refauthorName FROM sina_blog a "
				+ "JOIN sina_user b ON a.blog_author_id=b.user_id "
				+ "LEFT JOIN sina_blog c ON a.blog_parent_id=c.blog_id "
				+ "LEFT JOIN sina_user d ON c.blog_author_id=d.user_id "
				+ " UNION "
				+ "SELECT distinct a.blog_id, a.blog_content,a.blog_comment_num,a.blog_transfer_num,a.blog_like_num,a.blog_time,"
				+ "b.user_name authorName, b.user_id userId,b.user_blog_num userBlogNum,b.user_fans_num userFansNum,b.user_follow_num userFollowNum,"
				+ "c.blog_id refBlog_Id, c.blog_content refContent,c.blog_comment_num refCommentnum,c.blog_transfer_num refTransfernum,c.blog_like_num refPaisernum,c.blog_time refTime,d.user_name refauthorName FROM web_blog a "
				+ "JOIN web_user b ON a.blog_author_id=b.user_id "
				+ "LEFT JOIN web_blog c ON a.blog_parent_id=c.blog_id "
				+ "LEFT JOIN web_user d ON c.blog_author_id=d.user_id "
				+ " ORDER BY blog_time LIMIT ?, ?;";
		Connection con = JdbcUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, (pageNum - 1) * pageSize);
			pstm.setInt(2, pageSize);
			res = pstm.executeQuery();
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
				UserBase user=new UserBase(authorName);
				user.setUserId(res.getLong("userId"));
				user.setUserBlogNum(res.getLong("userBlogNum"));
				user.setUserFansNum(res.getLong("userFansNum"));
				user.setUserFollowNum(res.getLong("userFollowNum"));
				blog.setBlogAuthor(user);
				blog.setBlogId(blogId);
				blog.setBlogCommentNum(commentNum);
				blog.setBlogContent(content);
				blog.setBlogLikeNum(paiserNum);
				blog.setBlogTime(time);
				blog.setBlogTransferNum(transferNum);
				blog.setUserLike("false");
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

	protected List<BlogBase> GetList(int pageNum, int pageSize,int recState) {

		List<BlogBase> bloglist = new ArrayList<BlogBase>();
		String sql = " SELECT distinct a.blog_id+1000000 as blog_id, a.blog_content,a.blog_comment_num,a.blog_transfer_num,a.blog_like_num,a.blog_time,"
				+ "b.user_name authorName, b.user_id userId,b.user_blog_num userBlogNum,b.user_fans_num userFansNum,b.user_follow_num userFollowNum,"
				+ "c.blog_id refBlog_Id, c.blog_content refContent,c.blog_comment_num refCommentnum,c.blog_transfer_num refTransfernum,c.blog_like_num refPaisernum,c.blog_time refTime,d.user_name refauthorName FROM sina_blog a "
				+ "JOIN sina_user b ON a.blog_author_id=b.user_id "
				+ "LEFT JOIN sina_blog c ON a.blog_parent_id=c.blog_id "
				+ "LEFT JOIN sina_user d ON c.blog_author_id=d.user_id where d.user_labels=?"
				+ " UNION "
				+ "SELECT distinct a.blog_id, a.blog_content,a.blog_comment_num,a.blog_transfer_num,a.blog_like_num,a.blog_time,"
				+ "b.user_name authorName, b.user_id userId,b.user_blog_num userBlogNum,b.user_fans_num userFansNum,b.user_follow_num userFollowNum,"
				+ "c.blog_id refBlog_Id, c.blog_content refContent,c.blog_comment_num refCommentnum,c.blog_transfer_num refTransfernum,c.blog_like_num refPaisernum,c.blog_time refTime,d.user_name refauthorName FROM web_blog a "
				+ "JOIN web_user b ON a.blog_author_id=b.user_id "
				+ "LEFT JOIN web_blog c ON a.blog_parent_id=c.blog_id "
				+ "LEFT JOIN web_user d ON c.blog_author_id=d.user_id where d.user_labels=?"
				+ " ORDER BY blog_time LIMIT ?, ?;";
		Connection con = JdbcUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1,String.valueOf(recState));
			pstm.setString(2,String.valueOf(recState));
			pstm.setInt(3, (pageNum - 1) * pageSize);
			pstm.setInt(4, pageSize);
			res = pstm.executeQuery();
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
				UserBase user=new UserBase(authorName);
				user.setUserId(res.getLong("userId"));
				user.setUserBlogNum(res.getLong("userBlogNum"));
				user.setUserFansNum(res.getLong("userFansNum"));
				user.setUserFollowNum(res.getLong("userFollowNum"));
				blog.setBlogAuthor(user);
				blog.setBlogId(blogId);
				blog.setBlogCommentNum(commentNum);
				blog.setBlogContent(content);
				blog.setBlogLikeNum(paiserNum);
				blog.setBlogTime(time);
				blog.setBlogTransferNum(transferNum);
				blog.setUserLike("false");
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
	public subPage<BlogBase> findUserAllBlog(Long UserId, int PageNum, int PageSize) {
		Session session = sessionFactory.getCurrentSession();
		String Hql = "SELECT COUNT(*) AS totalRecord from web_blog where blog_author_id=?";
		SQLQuery query = session.createSQLQuery(Hql);
		query.setLong(0, UserId);
		//List list = query.list();
		List<BlogBase> bloglist;
		int webTotalRecord = ((Number) query.list().get(0)).intValue();
		//System.out.println("totalRecord"+webTotalRecord);
		String sinaSql = "SELECT COUNT(*) AS totalRecord from sina_blog where blog_author_id=?";
		SQLQuery sinaQuery = session.createSQLQuery(sinaSql);
		sinaQuery.setLong(0, UserId);
		int sinaTotalRecord=((Number)sinaQuery.list().get(0)).intValue();
		int totalRecord=sinaTotalRecord+webTotalRecord;
		
		// 如果PageNum页没有数据，那么PageNum的值被修改为最后一页
		if ((PageNum - 1) * PageSize > totalRecord) {
			PageNum = totalRecord / PageSize + 1;
		}
		if(totalRecord<=0)bloglist= null;
		else bloglist = GetUserAllBlog(UserId,PageNum, PageSize);
		subPage<BlogBase> Result = new subPage<BlogBase>(PageNum, PageSize,
				bloglist, totalRecord);
		System.out.println("userBlogTotalNum"+totalRecord);
		return Result;

	}
	protected List GetUserAllBlog(Long UserId,int PageNum, int PageSize) {

		List<BlogBase> bloglist = new ArrayList<BlogBase>();
		String sql = " SELECT distinct a.blog_id, a.blog_content,a.blog_comment_num,a.blog_transfer_num,a.blog_like_num,a.blog_time,"
				+ "b.user_name authorName, b.user_id userId,b.user_blog_num userBlogNum,b.user_fans_num userFansNum,b.user_follow_num userFollowNum,"
				+ "c.blog_id refBlog_Id, c.blog_content refContent,c.blog_comment_num refCommentnum,c.blog_transfer_num refTransfernum,c.blog_like_num refPaisernum,c.blog_time refTime,d.user_name refauthorName FROM sina_blog a "
				+ "JOIN sina_user b ON a.blog_author_id=b.user_id "
				+ "LEFT JOIN sina_blog c ON a.blog_parent_id=c.blog_id "
				+ "LEFT JOIN sina_user d ON c.blog_author_id=d.user_id where a.blog_author_id=? "
				+ " UNION "
				+"SELECT distinct a.blog_id, a.blog_content,a.blog_comment_num,a.blog_transfer_num,a.blog_like_num,a.blog_time,"
				+ "b.user_name authorName, b.user_id userId,b.user_blog_num userBlogNum,b.user_fans_num userFansNum,b.user_follow_num userFollowNum,"
				+ "c.blog_id refBlog_Id, c.blog_content refContent,c.blog_comment_num refCommentnum,c.blog_transfer_num refTransfernum,c.blog_like_num refPaisernum,c.blog_time refTime,d.user_name refauthorName FROM web_blog a "
				+ "JOIN web_user b ON a.blog_author_id=b.user_id "
				+ "LEFT JOIN web_blog c ON a.blog_parent_id=c.blog_id "
				+ "LEFT JOIN web_user d ON c.blog_author_id=d.user_id where a.blog_author_id=? "
				+ " ORDER BY blog_time LIMIT ?, ?;";
				//不懂为什么这样写

		Connection con = JdbcUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, UserId);
			pstm.setLong(2, UserId);
			//pstm.setLong(2, UserId);
			pstm.setInt(3, (PageNum - 1) * PageSize);
			pstm.setInt(4, PageSize);
			
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
				UserBase user=new UserBase(authorName);
				user.setUserId(res.getLong("userId"));
				user.setUserBlogNum(res.getLong("userBlogNum"));
				user.setUserFansNum(res.getLong("userFansNum"));
				user.setUserFollowNum(res.getLong("userFollowNum"));
				blog.setBlogAuthor(user);
				blog.setBlogId(blogId);
				blog.setBlogCommentNum(commentNum);
				blog.setBlogContent(content);
				blog.setBlogLikeNum(paiserNum);
				blog.setBlogTime(time);
				blog.setBlogTransferNum(transferNum);
				blog.setUserLike("false");
				///System.out.println(blog.getBlogContent());
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
	public subPage<BlogBase> findAllHotBlog(int PageNum, int PageSize) {
		Session session = sessionFactory.getCurrentSession();
		String Hql = "SELECT COUNT(*) AS totalRecord from BlogInfo";
		Query query = session.createQuery(Hql);
		List list = query.list();
		List<BlogBase> bloglist;
		int totalRecord = ((Number) list.get(0)).intValue();
		Hql = "SELECT COUNT(*) AS totalRecord from SinaBlogInfo";
		query = session.createQuery(Hql);
		list = query.list();
		totalRecord += ((Number) list.get(0)).intValue();
		if(totalRecord<=0)return null;
		// 如果PageNum页没有数据，那么PageNum的值被修改为最后一页
		if ((PageNum - 1) * PageSize > totalRecord) {
			PageNum = totalRecord / PageSize + 1;
		}
		System.out.println("hotBlogTotalRecord"+totalRecord);
		if(totalRecord<=0)bloglist= null;
		else bloglist = GetHotBlogList(PageNum, PageSize);
		subPage<BlogBase> Result = new subPage<BlogBase>(PageNum, PageSize,
				bloglist, totalRecord);
		return Result;
	}

	protected List<BlogBase> GetHotBlogList(int pageNum, int pageSize) {
		List<BlogBase> bloglist = new ArrayList<BlogBase>();
		String sql = " SELECT distinct a.blog_id+1000000 as blog_id, a.blog_content,a.blog_comment_num,a.blog_transfer_num,a.blog_like_num,a.blog_time,a.blog_hotdegree,"
				+ "b.user_name authorName, b.user_id userId,b.user_blog_num userBlogNum,b.user_fans_num userFansNum,b.user_follow_num userFollowNum,"
				+ "c.blog_id refBlog_Id, c.blog_content refContent,c.blog_comment_num refCommentnum,c.blog_transfer_num refTransfernum,c.blog_like_num refPaisernum,c.blog_time refTime,d.user_name refauthorName FROM sina_blog a "
				+ "JOIN sina_user b ON a.blog_author_id=b.user_id "
				+ "LEFT JOIN sina_blog c ON a.blog_parent_id=c.blog_id "
				+ "LEFT JOIN sina_user d ON c.blog_author_id=d.user_id "
				+ " UNION "
				+ "SELECT distinct a.blog_id, a.blog_content,a.blog_comment_num,a.blog_transfer_num,a.blog_like_num,a.blog_time,a.blog_hotdegree,"
				+ "b.user_name authorName, b.user_id userId,b.user_blog_num userBlogNum,b.user_fans_num userFansNum,b.user_follow_num userFollowNum,"
				+ "c.blog_id refBlog_Id, c.blog_content refContent,c.blog_comment_num refCommentnum,c.blog_transfer_num refTransfernum,c.blog_like_num refPaisernum,c.blog_time refTime,d.user_name refauthorName FROM web_blog a "
				+ "JOIN web_user b ON a.blog_author_id=b.user_id "
				+ "LEFT JOIN web_blog c ON a.blog_parent_id=c.blog_id "
				+ "LEFT JOIN web_user d ON c.blog_author_id=d.user_id  "
				+ " ORDER BY blog_hotdegree LIMIT ?, ?;";
				//不懂为什么这样写

		Connection con = JdbcUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, (pageNum - 1) * pageSize);
			pstm.setInt(2, pageSize);
			res = pstm.executeQuery();
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
				UserBase user=new UserBase(authorName);
				user.setUserId(res.getLong("userId"));
				user.setUserBlogNum(res.getLong("userBlogNum"));
				user.setUserFansNum(res.getLong("userFansNum"));
				user.setUserFollowNum(res.getLong("userFollowNum"));
				blog.setBlogAuthor(user);
				blog.setBlogId(blogId);
				blog.setBlogCommentNum(commentNum);
				blog.setBlogContent(content);
				blog.setBlogLikeNum(paiserNum);
				blog.setBlogTime(time);
				blog.setBlogTransferNum(transferNum);
				blog.setUserLike("false");
				//System.out.println(blog.getBlogContent());
				bloglist.add(blog);
			}
		

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(con, pstm, res);
		}
		
		return bloglist;
	}
	/**
	 * 推荐部分
	 */
	@Override
	public subPage<BlogBase> findRecBlog(Long UserId, int PageNum, int PageSize) {
		// TODO 自动生成的方法存根
		/**
		 * 需注意！！！
		 * 为区分爬下来的数据和本站数据，现将sina_blog表中blog_id+1000000处理，以区分.参考上个函数即可
		 */
		return null;
	}

}
