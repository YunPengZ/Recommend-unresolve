package ahu.edu.cn.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ahu.edu.cn.JavaBeans.BlogBase;
import ahu.edu.cn.JavaBeans.BlogInfo;
import ahu.edu.cn.JavaBeans.TopicInfo;
import ahu.edu.cn.JavaDaosInterface.WeiboOperationDao;
import ahu.edu.cn.JavaDaosInterface.SinaTopicDao;
import ahu.edu.cn.ServicesInterface.SinaTopicServices;
import ahu.edu.cn.Util.subPage;

public class SinaTopicServicesImp implements SinaTopicServices {
	@Autowired
	SinaTopicDao topicDao;
	
	public SinaTopicDao getTopicDao() {
		return topicDao;
	}

	public void setTopicDao(SinaTopicDao topicDao) {
		this.topicDao = topicDao;
	}

	@Override
	public subPage<TopicInfo> findHotTopic() {
		return  topicDao.findHotTopic();
	}

	@Override
	public void TopicHotDegree() {
		topicDao.TopicHotDegree();
	}

	@Override
	public subPage<BlogBase> findTopic(String topic, int pageNum, int pageSize) {
		return topicDao.findTopic(topic, pageNum, pageSize);
	}

	
}
