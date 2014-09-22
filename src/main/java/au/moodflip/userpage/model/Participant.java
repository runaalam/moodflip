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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "assessment_perticipent")
public class Participant implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	// **TO BE CHANGED**
	@Column(name = "user_id")
	private long userId;
	
	//@OneToMany(cascade = CascadeType.ALL)
	//@JoinColumn(name = "assessment_id")
	private int assessmentid;
	
	@Column(name = "score")
	private int score;
	
	@Column(name = "submit_date")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date submitDate;

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

	public int getAssessmentid() {
		return assessmentid;
	}

	public void setAssessmentid(int assessmentid) {
		this.assessmentid = assessmentid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}
	
}
