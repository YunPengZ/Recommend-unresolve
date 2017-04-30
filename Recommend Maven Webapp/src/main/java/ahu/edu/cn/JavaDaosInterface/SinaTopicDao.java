package ahu.edu.cn.JavaDaosInterface;

import java.util.List;

import ahu.edu.cn.JavaBeans.BlogBase;
import ahu.edu.cn.JavaBeans.BlogInfo;
import ahu.edu.cn.JavaBeans.TopicInfo;
import ahu.edu.cn.Util.subPage;

public interface SinaTopicDao {
	/**
	 * 获得热门话题
	 * @return List
	 * */
	public subPage<TopicInfo> findHotTopic();
	/**
	 * 定时执行设置话题result
	 */
	public void TopicHotDegree();
	/**
	 * 获得热门话题
	 * @param topic 话题内容
	 * @param pageNum 第几页的数据
	 * @param pageSize 页面的数据数量
	 * @return subPage 对象
	 * */
	public subPage<BlogBase> findTopic(String topic, int pageNum, int pageSize);
}
