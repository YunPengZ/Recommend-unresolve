package ahu.edu.cn.ServicesInterface;

import ahu.edu.cn.JavaBeans.BlogBase;
import ahu.edu.cn.JavaBeans.BlogInfo;
import ahu.edu.cn.Util.subPage;

public interface SinaAndWebBlogQueryServices {
	public subPage<BlogBase> findUserAllBlog(Long UserId,int PageNum,int PageSize);
//	/**
//	 * 查询用户朋友圈的微博
//	 * @param UserId 用户ID
//	 * @param PageNum 查询第几页数据
//	 * @param PageSize 每页多少数据
//	 * @return @see subPage 对象
//	 */
//	public subPage<BlogInfo> findUserFriendsCircleBlog(int UserId,int PageNum,int PageSize);
//	/**
//	 * 查询用户推荐微博
//	 * @param UserId 用户ID
//	 * @param PageNum 查询第几页数据
//	 * @param PageSize 每页多少数据
//	 * @return @see subPage 对象
//	 */
//	public subPage<BlogInfo> findUserRecommendBlog(int UserId,int PageNum,int PageSize);
	/**
	 * 查询所有微博
	 * @param PageNum 查询第几页数据
	 * @param PageSize 每页多少数据
	 * @return subPage 对象
	 */
	public subPage<BlogBase> findAllBlog(int PageNum,int PageSize);
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
	public subPage<BlogBase> findRecBlog(Long UserId, int labelId,int PageNum, int PageSize);
}
