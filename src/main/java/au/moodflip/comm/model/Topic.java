package au.moodflip.comm.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "Topic")
public class Topic implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "name")
	@NotBlank
	private String name;

	@Column(name = "content")
	@Type(type="text")
	@NotBlank
	private String content;

	// **TO BE CHANGED**
	@Column(name = "user_id")
	private long userId;

	@Column(name = "up_vote")
	private int upVote;

	@Column(name = "down_vote")
	private int downVote;

	@Column(name = "pinned")
	private boolean pinned;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "edited_at")
	private Date editedAt;

	@ManyToOne
	private Forum forum;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "comment_id")
	private Set<TopicComment> topicComments;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getUpVote() {
		return upVote;
	}

	public void setUpVote(int upVote) {
		this.upVote = upVote;
	}

	public int getDownVote() {
		return downVote;
	}

	public void setDownVote(int downVote) {
		this.downVote = downVote;
	}

	public boolean isPinned() {
		return pinned;
	}

	public void setPinned(boolean pinned) {
		this.pinned = pinned;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getEditedAt() {
		return editedAt;
	}

	public void setEditedAt(Date editedAt) {
		this.editedAt = editedAt;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public Set<TopicComment> getTopicComments() {
		return topicComments;
	}

	public void setTopicComments(Set<TopicComment> topicComments) {
		this.topicComments = topicComments;
	}

}
