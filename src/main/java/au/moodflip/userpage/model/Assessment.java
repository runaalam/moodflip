package au.moodflip.userpage.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import au.moodflip.personalisation.model.User;

@Entity
@Table(name = "assessment")
public class Assessment{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")	
	private User user;
	
	@Column(name = "date")
	//@Temporal(TemporalType.DATE)
	private Date date;
	
	@Column(name = "score")
	private int score;
	
	@Column(name = "depressionScale")
	private int depressionScale;
	
	@OneToMany(mappedBy="assessment")
	private List<Response> responseList;
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="assessment")
	private ResultDetails resultDetails;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getDepressionScale() {
		return depressionScale;
	}

	public void setDepressionScale(int depressionScale) {
		this.depressionScale = depressionScale;
	}

	public List<Response> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<Response> responseList) {
		this.responseList = responseList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ResultDetails getResultDetails() {
		return resultDetails;
	}

	public void setResultDetails(ResultDetails resultDetails) {
		this.resultDetails = resultDetails;
	}
}
