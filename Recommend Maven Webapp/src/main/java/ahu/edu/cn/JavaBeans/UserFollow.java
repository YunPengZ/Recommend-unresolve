package ahu.edu.cn.JavaBeans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 用户关注 与表sina_user_follow
 * @author 朱
 *
 */
@Entity
@Table(name="sina_user_follow")


public class UserFollow {
	private long id;
	private long userid;
	private long followid;
	
	private String followname;
	@Id
	@Column(name="id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment",strategy="increment")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name="user_id")
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	@Column(name="follow_id")
	public long getFollowid() {
		return followid;
	}
	public void setFollowid(long followid) {
		this.followid = followid;
	}
	@Column(name="follow_name")
	public String getFollowname() {
		return followname;
	}
	public void setFollowname(String followname) {
		this.followname = followname;
	}
	
}
