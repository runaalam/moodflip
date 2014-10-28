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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import au.moodflip.personalisation.model.User;

@Entity
@Table(name = "activity")
public class Activity {
	public Activity() { }
	public Activity(User user, String desc, Date date) { 
		this.user = user;
		this.description = desc;
		this.activityDate = date;
	}
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")	
	private User user;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "activity_date")
	@Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date activityDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}
}
