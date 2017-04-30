package ahu.edu.cn.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ahu.edu.cn.JavaBeans.BlogBase;
import ahu.edu.cn.JavaBeans.TopicInfo;
import ahu.edu.cn.ServicesInterface.SinaTopicServices;
import ahu.edu.cn.Util.subPage;

@Controller
public class SinaTopicQueryController {
	@Autowired
	private SinaTopicServices services;
	@RequestMapping("/query/topic")
	@ResponseBody
	public subPage<TopicInfo> queryTopic(){
		 return services.findHotTopic();
		
	}
	@RequestMapping("/query/findtopic")
	@ResponseBody
	public subPage<BlogBase> findTopic(int page_num,String topic){
		return services.findTopic(topic, page_num, 6);
		
	}
	
}
