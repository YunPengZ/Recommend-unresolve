package ahu.edu.cn.JavaDaos;

import org.springframework.scheduling.annotation.Scheduled;    
import org.springframework.stereotype.Component;  
  /**
   * 设置定时任务
   * @author 朱
   *
   */
@Component("taskJob")  
public class TaskJob {  
    @Scheduled(cron = "0 0 0 * * ?")  
    /**
     * 定时任务  设置每一个微博的热度和话题热度
     */
    public void job1() {  
    	//WeiboBlogQueryDaoImp weiboblog=new WeiboBlogQueryDaoImp();
    	SinaTopicDaoImp topicDaoImp=new SinaTopicDaoImp();
    	topicDaoImp.TopicHotDegree();
        System.out.println("任务进行中。。。");  
    }  
}  