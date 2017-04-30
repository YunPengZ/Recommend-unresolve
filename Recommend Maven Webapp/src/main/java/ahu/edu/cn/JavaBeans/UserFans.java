package ahu.edu.cn.JavaBeans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 用户粉丝 与表sina_user_fans
 * @author 朱
 *
 */
@Entity
@Table(name="sina_user_fans")

public class UserFans {
	private long id;
	private long userid;
	private long fansid;
	private String fansname;
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
	@Column(name="fans_id")
	public long getFansid() {
		return fansid;
	}
	public void setFansid(long fansid) {
		this.fansid = fansid;
	}
	@Column(name="fans_name")
	public String getFansname() {
		return fansname;
	}
	public void setFansname(String fansname) {
		this.fansname = fansname;
	}
}
