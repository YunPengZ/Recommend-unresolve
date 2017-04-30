package ahu.edu.cn.JavaDaos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
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
import ahu.edu.cn.JavaBeans.TopicInfo;
import ahu.edu.cn.JavaBeans.UserBase;
import ahu.edu.cn.JavaDaosInterface.SinaTopicDao;
import ahu.edu.cn.Util.JdbcUtil;
import ahu.edu.cn.Util.subPage;

@Repository
public class SinaTopicDaoImp implements SinaTopicDao{
	
	
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	
	/*
	 * 设置话题热度，也是定时执行
	 * */
	@Override
	public void TopicHotDegree() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM TopicInfo";
		Query query=session.createQuery(hql);
		List<TopicInfo> topicList=(List<TopicInfo>)query.list();
		for(TopicInfo topic:topicList){
			setTopicHotDegree(topic);
		}
	}

	private void setTopicHotDegree(TopicInfo topic) {
		Session session = sessionFactory.getCurrentSession();
		String topicSql="select count(*) as totalRecord from sina_blog_info where blog_topic=?";
		SQLQuery topicQuery=session.createSQLQuery(topicSql);
		topicQuery.setParameter(0, topic.getTopic());
		int topicNum=((Number)topicQuery.list().get(0)).intValue();
		if(topicNum<=0)return ;
		String hql="update TopicInfo as topic set topic.topichotdegree=? where topic.topicid=?";
		Query query=session.createQuery(hql);
		query.setParameter(0, topicNum);
		query.setParameter(1, topic.getTopicid());
		query.executeUpdate();
		
		//session.createQuery(s)
	}
		/*private Date getTopicLastTime(String text) {
			Session session = sessionFactory.getCurrentSession();
			String hql="from TopicInfo topic where topic.topic=?";
			Query query=session.createQuery(hql);
			query.setParameter(0, text);
			if(query.list()!=null){
				TopicInfo topic=(TopicInfo) query.list().get(0);
				System.out.println(topic.getLastTopicTime());
				return topic.getLastTopicTime();
			}
			else return null;
		}*/
		protected boolean isTopicExist(String text) {
			Session session = sessionFactory.getCurrentSession();
			String hql="SELECT COUNT(*) AS totalRecord from TopicInfo topic where topic.topic=?";
			Query query=session.createQuery(hql);
			query.setParameter(0, text);
			int topicNum=((Number) query.list().get(0)).intValue();
			System.out.println("topicNum"+topicNum);
			if(topicNum!=0)return true;//就是已有此话题
			else return false;
		}

		/*
		 * 统计topic在数据库中出现次数
		 * */
		protected double getTopicHotDegree(String topic) {
			Session session = sessionFactory.getCurrentSession();
			double topicNum;
			String hql="SELECT COUNT(*) AS totalRecord from sina_blog_info blog where blog.Topic=?";
			SQLQuery query=session.createSQLQuery(hql);
			query.setParameter(0, topic);
			topicNum=((Number) query.list().get(0)).doubleValue();
			return topicNum;
		}
		
	@Override
	public subPage<TopicInfo> findHotTopic() {
		Session session = sessionFactory.getCurrentSession();
		subPage<TopicInfo> topicResult;
		List<TopicInfo> topicList;
		String hql="SELECT COUNT(*) AS totalRecord FROM TopicInfo topic order by topic.id desc";
		Query query = session.createQuery(hql);
		int topicTotalRecord=((Number)(query.list().get(0))).intValue();
		if(topicTotalRecord<=0)topicList= null;
		else topicList=getTopicList();
		//List<TopicInfo> topiclist =randomQuery(query.list());
		//List<TopicInfo> topiclist =query.list();
		//System.out.println("size"+topiclist.get(0));
		topicResult=new subPage(1,6,topicList,topicTotalRecord);
		return topicResult;
	}
	private List<TopicInfo> getTopicList() {
		Session session = sessionFactory.getCurrentSession();
		List<TopicInfo> topicList;
		String hql="FROM TopicInfo topic order by topic.id desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(12);
		topicList=query.list();
		
		return topicList;
	}

	/**
	 *  按随机数导入话题
	 * @param list 对象
	 * @return list-TopicInfo
	 */
	private List<TopicInfo> randomQuery(List<TopicInfo> list) {
		HashSet<Integer> set=new HashSet<Integer>();
		randomSet(0,12,6,set);
		Iterator<Integer> it=set.iterator();
		int []arraylist=new int[6];
		int i=0;
		while(it.hasNext())
		{
			
			arraylist[i]=it.next();
			System.out.println(arraylist[i]);
			i++;
		}
			for(;i>0;i--){
				list.remove(list.get(arraylist[i-1]));
			}
			if(list.size()==6)System.out.println("true");
		return list;
	}
	/**
	 * 利用哈希表特性生成随机数
	 * @param min 最小值
	 * @param max 最大值
	 * @param n 生成数目
	 * @param set 生成结果哈希 序列
	 */
	 
	public static void randomSet(int min, int max, int n, HashSet<Integer> set) {  
	       if (n > (max - min + 1) || max < min) {  
	           return;  
	       }  
	       for (int i = 0; i < n; i++) {  
	           // 调用Math.random()方法  
	           int num = (int) (Math.random() * (max - min)) + min;  
	           set.add(num);// 将不同的数存入HashSet中  
	       }  
	       int setSize = set.size();  
	       // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小  
	       if (setSize < n) {  
	        randomSet(min, max, n - setSize, set);// 递归  
	       }  
	   }  
	@Override
	public subPage<BlogBase> findTopic(String topic, int pageNum, int pageSize) {
		Session session = sessionFactory.getCurrentSession();
		String hql="SELECT COUNT(*) AS totalRecord from sina_blog blog where blog.blog_topic=?";
		int totalRecord=((Number) session.createSQLQuery(hql).setParameter(0, topic).list().get(0)).intValue();
		//if(totalRecord==0)return null;
		List<BlogBase> bloglist;
		System.out.println("topicNum"+totalRecord);
		if((pageNum-1)*pageSize>totalRecord)pageNum=totalRecord/pageSize+1;
		if(totalRecord<=0){
			bloglist=null;
		}else bloglist = getTopic(topic,pageNum, pageSize);
		subPage<BlogBase> Result = new subPage<BlogBase>(pageNum, pageSize, bloglist, totalRecord);
		return Result;
	}
	protected List<BlogBase> getTopic(String topic, int PageNum, int PageSize) {
		List<BlogBase> bloglist = new ArrayList<BlogBase>();
		String sql = " SELECT a.blog_id+1000000 as blog_id, a.blog_content,a.blog_comment_num,a.blog_transfer_num,a.blog_topic,a.blog_like_num,a.blog_time,b.user_name authorName,"
				+ "c.blog_id refBlog_Id, c.blog_content refContent,c.blog_comment_num refCommentnum,c.blog_transfer_num refTransfernum,c.blog_like_num refPaisernum,c.blog_time refTime,d.user_name refauthorName FROM sina_blog a "
				+ "JOIN sina_user b ON a.blog_author_id=b.user_id "
				+ "LEFT JOIN sina_blog c ON a.blog_parent_id=c.blog_id "
				+ "LEFT JOIN sina_user d ON c.blog_author_id=d.user_id where a.blog_topic=?"
				+ " UNION "
				+ "SELECT a.blog_id+1000000 as blog_id, a.blog_content,a.blog_comment_num,a.blog_transfer_num,a.blog_topic,a.blog_like_num,a.blog_time,b.user_name authorName, "
				+ "c.blog_id refBlog_Id, c.blog_content refContent,c.blog_comment_num refCommentnum,c.blog_transfer_num refTransfernum,c.blog_like_num refPaisernum,c.blog_time refTime,d.user_name refauthorName FROM web_blog a "
				+ "JOIN web_user b ON a.blog_author_id=b.user_id "
				+ "LEFT JOIN web_blog c ON a.blog_parent_id=c.blog_id "
				+ "LEFT JOIN web_user d ON c.blog_author_id=d.user_id  where a.blog_topic=?"
				+ " ORDER BY blog_time LIMIT ?, ?;";
				//不懂为什么这样写

		Connection con = JdbcUtil.getConnection();
		PreparedStatement pstm = null;
		ResultSet res = null;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, topic);
			pstm.setString(2, topic);
			pstm.setInt(3, (PageNum - 1) * PageSize);
			pstm.setInt(4, PageSize);
			res = pstm.executeQuery();
			while (res.next()) {
				System.out.println(res.getString("blog_content"));
				Long blogId = res.getLong("blog_id");
				String authorName = res.getString("authorName");
				String content = res.getString("blog_content");
				Long commentNum = res.getLong("blog_comment_num");
				Long transferNum = res.getLong("blog_transfer_num");
				String blogTopic=res.getString("blog_topic");
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
				blog.setBlogTopic(blogTopic);
				System.out.println(blog.getBlogContent());
				bloglist.add(blog);
			}
		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.closeAll(con, pstm, res);
		}
		
		return bloglist;
	}
}
