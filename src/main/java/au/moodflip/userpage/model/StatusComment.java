package au.moodflip.userpage.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

import au.moodflip.personalisation.model.User;

@Entity
@Table(name = "StatusComment")
public class StatusComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name = "content")
	@Type(type="text")
	@NotBlank
	private String content;

	@Column(name = "comment_date")
	private Date commentDate;

	@ManyToOne
	@JoinColumn(name="status_id")
	private Status status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
