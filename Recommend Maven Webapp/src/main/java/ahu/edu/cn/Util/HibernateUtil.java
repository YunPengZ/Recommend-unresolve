package ahu.edu.cn.Util;

import java.sql.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


import ahu.edu.cn.JavaBeans.BlogInfo;
import ahu.edu.cn.JavaBeans.WeiboUser;

/**
 * 使用Hibernate连接数据库
 * @author 杨喆
 *
 */
public class HibernateUtil {

	private static  SessionFactory sessionFactory = null;
	
	private static Session session=null;
	static {
		Configuration config=new Configuration().configure();
		StandardServiceRegistryBuilder ssrb=new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		StandardServiceRegistry ssr=ssrb.build();
		sessionFactory=config.buildSessionFactory(ssr);
	}
	
	public static SessionFactory GetSessionFactor(){
		return sessionFactory;
	}
	public static Session GetSession(){
		session=sessionFactory.openSession();
		return session;
	}
	public static void CloseSession(Session session){
		if(session!=null&&session.isOpen()){
			session.close();
		}
	}
	public static void main(String[] args) {
		Session se=HibernateUtil.GetSession();
		
//			WeiboUser user=new WeiboUser();
//			user.setBlognum(0);
//			user.setEmail("wsdsg825@qq.com");
//			user.setFansnum(0);
//			user.setFollownum(0);
//			user.setPassword("1243");
//			user.setSex("男");
//			user.setTel("15454");
//			user.setUsername("杨哲");
//			BlogInfo pblog=(BlogInfo)se.get(BlogInfo.class, 1);
//			BlogInfo blog1=new BlogInfo();
//			
//			blog1.setCommentNum(0);
//			blog1.setContent("sdfsdgsgsddcs");
//			blog1.setPaiserNum(0);
//		
//			blog1.setTransferNum(0);
//			blog1.setTime(new Date(324234));
//			blog1.setReferenceBlog(pblog);
//			user.getBlogset().add(blog1);
//			Transaction trx = se.beginTransaction();
//			 
//			se.save(user);
//			se.save(blog1);
//			 
//			trx.commit();
//			String hql="from BlogInfo a join a.author left join a.referenceBlog b left join b.author";
//			
//			Query query=se.createQuery(hql);
//			List<Object[]> list=query.list();
//			System.out.println(list.size());
//			for(Object[] i:list){
//				BlogInfo blogc=(BlogInfo)i[0];
//				WeiboUser user=(WeiboUser)i[1];
//				System.out.println(user.getUsername());
//				
//			}
//		
		
		
		
	}
	
}
