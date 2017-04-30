package ahu.edu.cn.JavaBeans;

import java.sql.Date;

public class BlogBase {
	private Long blogId;
	
	private UserBase blogAuthor;
	private BlogBase blogParent;
	private String blogContent;
	private String blogTopic;
	private Long blogCommentNum;
	private Long blogTransferNum;
	private Long blogLikeNum;
	private Date blogTime;
	private double blogHotDegree;
	private double blogCosSimilar;
	private String userLike;
	private String isWeb;
	public BlogBase(){}
	public Long getBlogId() {
		return blogId;
	}
	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}
	public UserBase getBlogAuthor() {
		return blogAuthor;
	}
	public void setBlogAuthor(UserBase blogAuthor) {
		this.blogAuthor = blogAuthor;
	}
	public BlogBase getBlogParent() {
		return blogParent;
	}
	public void setBlogParent(BlogBase blogParent) {
		this.blogParent = blogParent;
	}
	public String getBlogContent() {
		return blogContent;
	}
	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	public String getBlogTopic() {
		return blogTopic;
	}
	public void setBlogTopic(String blogTopic) {
		this.blogTopic = blogTopic;
	}
	public Long getBlogCommentNum() {
		return blogCommentNum;
	}
	public void setBlogCommentNum(Long blogCommentNum) {
		this.blogCommentNum = blogCommentNum;
	}
	public Long getBlogTransferNum() {
		return blogTransferNum;
	}
	public void setBlogTransferNum(Long blogTransferNum) {
		this.blogTransferNum = blogTransferNum;
	}
	public Long getBlogLikeNum() {
		return blogLikeNum;
	}
	public void setBlogLikeNum(Long blogLikeNum) {
		this.blogLikeNum = blogLikeNum;
	}
	public Date getBlogTime() {
		return blogTime;
	}
	public void setBlogTime(Date blogTime) {
		this.blogTime = blogTime;
	}
	public double getBlogHotDegree() {
		return blogHotDegree;
	}
	public void setBlogHotDegree(double blogHotDegree) {
		this.blogHotDegree = blogHotDegree;
	}
	public double getBlogCosSimilar() {
		return blogCosSimilar;
	}
	public void setBlogCosSimilar(double blogCosSimilar) {
		this.blogCosSimilar = blogCosSimilar;
	}
	public String getUserLike() {
		return userLike;
	}
	public void setUserLike(String userLike) {
		this.userLike = userLike;
	}
	public String getIsWeb() {
		return isWeb;
	}
	public void setIsWeb(String isWeb) {
		this.isWeb = isWeb;
	}

	
	
}
