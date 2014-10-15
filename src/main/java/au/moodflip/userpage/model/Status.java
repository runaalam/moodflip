package au.moodflip.userpage.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Status")
public class Status implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	// **TO BE CHANGED**
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "submit_date")
	private Date submitDate;
	
	@Column(name = "pending")
	private Boolean pending;
	
	@Column(name = "approved")
	private Boolean approved;
	
	@Column(name= "approvedBy")
	private Boolean approvedBy;
	
	@Column(name = "deleted")
	private Boolean deleted;
	
	@Column(name = "deletedDate")
	private Date deletedDate;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="status")
	private Set<StatusComment> statusComments;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Boolean getPending() {
		return pending;
	}

	public void setPending(Boolean pending) {
		this.pending = pending;
	}

	public Boolean getApproved() {
		return approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public Boolean getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Boolean approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public Set<StatusComment> getStatusComments() {
		return statusComments;
	}

	public void setStatusComments(Set<StatusComment> statusComments) {
		this.statusComments = statusComments;
	}	
}
