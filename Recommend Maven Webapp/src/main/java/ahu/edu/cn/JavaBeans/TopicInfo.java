package ahu.edu.cn.JavaBeans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 设置话题信息，与表hot_topic映射
 * @author G50-70
 *
 */
@Entity
@Table(name="web_hot_topic")
public class TopicInfo {
	private int topicid;
	private String topic;
	private int topichotdegree;
	private Date topiclasttime;
	@Id
	@Column(name="topic_id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment",strategy="increment")
	public int getTopicid() {
		return topicid;
	}
	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}
	@Column(name="topic_hot_degree")
	public int getTopichotdegree() {
		return topichotdegree;
	}
	public void setTopichotdegree(int topichotdegree) {
		this.topichotdegree = topichotdegree;
	}
	
	@Column(name="topic")
	public String getTopic(){
		return topic;
	}
	public void setTopic(String text)
	{
		this.topic=text;
	}
	@Column(name="topic_last_time")
	public Date getTopiclasttime() {
		return topiclasttime;
	}
	public void setTopiclasttime(Date topiclasttime) {
		this.topiclasttime = topiclasttime;
	}
	
	

	

}
