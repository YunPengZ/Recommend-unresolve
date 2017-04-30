package ahu.edu.cn.JavaBeans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 点赞表 与表paiser_record表映射
 * @author 朱
 *
 */
@Entity
@Table(name="blog_like_record")
public class PraiserInfo {
	private int RecordId;
	private Long blogId;
	private Long paiserid;
	private Long bypraiserid;
	private Date time;
	
	
	
	//private Long praisedUser;
	@Id
	@Column(name="id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment",strategy="increment")
	public int getRecordId() {
		return RecordId;
	}
	public void setRecordId(int RecordId) {
		this.RecordId = RecordId;
	}
	
	@Column(name="blog_id")
	public Long getBlogId() {
		return blogId;
	}
	public void setBlogId(Long blogId2) {
		this.blogId = blogId2;
	}
	@Column(name="blog_like_userid")
	public Long getPaiserid() {
		return paiserid;
	}
	public void setPaiserid(Long paiserid) {
		this.paiserid = paiserid;
	}
	@Column(name="blog_by_like_userid")
	public Long getBypraiserid() {
		return bypraiserid;
	}
	public void setBypraiserid(Long bypraiserid) {
		this.bypraiserid = bypraiserid;
	}
	@Column(name="like_time")
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
}
