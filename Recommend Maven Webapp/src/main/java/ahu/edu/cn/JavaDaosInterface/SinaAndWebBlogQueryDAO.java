package ahu.edu.cn.JavaDaosInterface;

import ahu.edu.cn.JavaBeans.BlogBase;
import ahu.edu.cn.Util.subPage;

public interface SinaAndWebBlogQueryDAO {
	public subPage<BlogBase> findUserAllBlog(Long UserId,int PageNum,int PageSize);
	/**
	 * 查询所有微博
	 * @param PageNum 查询第几页数据
	 * @param PageSize 每页多少数据
	 * @param recState 
	 * @return subPage 对象
	 */
	public subPage<BlogBase> findAllBlog(int PageNum,int PageSize, int recState);
//	/**
//	 * 查询所有热门微博
//	 * @param PageNum 查询第几页数据
//	 * @param PageSize 每页多少数据
//	 * @return subPage 对象
//	 */
	public subPage<BlogBase> findAllHotBlog(int PageNum,int PageSize);
	/*
	 * 获得推荐微博
	 * @param PageNum 查询第几页数据
	 * @param PageSize 每页多少数据
	 * @return subPage 对象
	 */
	public subPage<BlogBase> findRecBlog(Long UserId,int PageNum, int PageSize);
}
