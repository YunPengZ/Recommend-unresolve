package ahu.edu.cn.JavaBeans;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="sina_blog")
public class SinaBlogInfo {
private Long blogId;
	
	private SinaUser blogAuthor;
	/**
	 * 转发本微博的微博与本条微博多对一的映射关系
	 */
	private SinaBlogInfo blogParent;
	private String blogContent;
	private String blogTopic;
	private Long blogCommentNum;
	private Long blogTransferNum;
	private Long blogLikeNum;
	private Date blogTime;
	private double blogHotDegree;
	private double blogCosSimilar;
	/**
	 * 本条微博与转发本微博的微博一对多的映射关系
	 */
	private Set<SinaBlogInfo> childrenBlog=new HashSet<SinaBlogInfo>();
	
	
	
	public SinaBlogInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@OneToMany
	@JoinColumn(name="blog_parent_id")
	public Set<SinaBlogInfo> getChildrenBlog() {
		return childrenBlog;
	}
	public void setChildrenBlog(Set<SinaBlogInfo> childrenBlog) {
		this.childrenBlog = childrenBlog;
	}
	
	
	@ManyToOne
	@JoinColumn(name="blog_author_id")
	public SinaUser getBlogAuthor() {
		return blogAuthor;
	}
	public void setBlogAuthor(SinaUser blogAuthor) {
		this.blogAuthor = blogAuthor;
	}
	
	@ManyToOne
	@JoinColumn(name="blog_parent_id")
	public SinaBlogInfo getBlogParent() {
		return blogParent;
	}
	public void setBlogParent(SinaBlogInfo blogParent) {
		this.blogParent = blogParent;
	}
	
	@Column(name="blog_content")
	public String getBlogContent() {
		return blogContent;
	}
	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	
	@Column(name="blog_topic")
	public String getBlogTopic() {
		return blogTopic;
	}
	public void setBlogTopic(String blogTopic) {
		this.blogTopic = blogTopic;
	}
	
	@Column(name="blog_comment_num")
	public Long getBlogCommentNum() {
		return blogCommentNum;
	}
	public void setBlogCommentNum(Long blogCommentNum) {
		this.blogCommentNum = blogCommentNum;
	}
	
	@Column(name="blog_transfer_num")
	public Long getBlogTransferNum() {
		return blogTransferNum;
	}
	public void setBlogTransferNum(Long blogTransferNum) {
		this.blogTransferNum = blogTransferNum;
	}
	
	@Column(name="blog_like_num")
	public Long getBlogLikeNum() {
		return blogLikeNum;
	}
	public void setBlogLikeNum(Long blogLikeNum) {
		this.blogLikeNum = blogLikeNum;
	}
	
	@Column(name="blog_time")
	public Date getBlogTime() {
		return blogTime;
	}
	public void setBlogTime(Date blogTime) {
		this.blogTime = blogTime;
	}
	
	@Column(name="blog_hotdegree")
	public double getBlogHotDegree() {
		return blogHotDegree;
	}
	public void setBlogHotDegree(double blogHotDegree) {
		this.blogHotDegree = blogHotDegree;
	}
	
	@Column(name="blog_cossimilar")
	public double getBlogCosSimilar() {
		return blogCosSimilar;
	}
	public void setBlogCosSimilar(double blogCosSimilar) {
		this.blogCosSimilar = blogCosSimilar;
	}
	
	@Id
	@Column(name="blog_id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment",strategy="increment")
	public Long getBlogId() {
		return blogId;
	}
	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}
}
