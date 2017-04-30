package ahu.edu.cn.JavaBeans;

import java.util.Date;
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
/**
 * 评论表 与表comment映射
 * @author 朱
 *
 */
@Entity
@Table(name="web_comment")
public class CommentInfo {
	
	private Long commentId;
	private BlogInfo blog;
	private Long reviewerId;//评论者id
	private String content;
	private Date time;
	private CommentInfo commentParent;
	
	private Set<CommentInfo> commentChildren;
	
	
	public CommentInfo(){
		
	}
	
	
	@Id
	@Column(name="comment_id")
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment",strategy="increment")
	public Long getCommentId() {
		return commentId;
	}



	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

    @ManyToOne
    @JoinColumn(name="blog_id")
	public BlogInfo getBlog() {
		return blog;
	}


	public void setBlog(BlogInfo blogBase) {
		this.blog = blogBase;
	}

    @Column(name="reviewer_id")
	public Long getReviewerId() {
		return reviewerId;
	}


	public void setReviewerId(Long reviewerId) {
		this.reviewerId = reviewerId;
	}

    @Column(name="content")
	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

    @Column(name="time")
	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}

    @ManyToOne
    @JoinColumn(name="comment_parent_id")
	public CommentInfo getCommentParent() {
		return commentParent;
	}


	public void setCommentParent(CommentInfo commentParent) {
		this.commentParent = commentParent;
	}

    
	@OneToMany
	@JoinColumn(name="comment_parent_id")
	public Set<CommentInfo> getCommentChildren() {
		return commentChildren;
	}


	public void setCommentChildren(Set<CommentInfo> commentChildren) {
		this.commentChildren = commentChildren;
	}



	
}
