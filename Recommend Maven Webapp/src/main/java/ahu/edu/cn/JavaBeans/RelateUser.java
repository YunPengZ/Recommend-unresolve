package ahu.edu.cn.JavaBeans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 与表Relate_user映射，记录用户查看评论和点赞的时间
 * */
@Entity
@Table(name="Relate_User")
public class RelateUser {
	public RelateUser(){
	}
	private int recordid;
	
	private long userid;
	private Date relatecomtime;
	private Date relatepratime;
	@Id
	@Column(name="Record_Id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment",strategy="increment")
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	@Column(name="User_Id")
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	@Column(name="Reviewer_Record_Time")
	public Date getRelatecomtime() {
		return relatecomtime;
	}
	public void setRelatecomtime(Date relatecomtime) {
		this.relatecomtime = relatecomtime;
	}
	@Column(name="Paiser_Record_Time")
	public Date getRelatepratime() {
		return relatepratime;
	}
	public void setRelatepratime(Date relatepratime) {
		this.relatepratime = relatepratime;
	}
}
