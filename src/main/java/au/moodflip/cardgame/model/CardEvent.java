package au.moodflip.cardgame.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import org.hibernate.annotations.IndexColumn;

import au.moodflip.personalisation.model.User;

@Entity
@Table(name="Card_events")
public class CardEvent {
	public CardEvent(){ }
	public CardEvent(User user, 
					Card card, 
					Date date, 
					int points, 
					boolean complete){
		this.user = user;
		this.card = card;
		this.date = date;
		this.points = points;
		this.complete = complete;
	}
	
	@Id
	@GeneratedValue
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	private long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }
	private User user;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="card_id")
	public Card getCard() { return card; }
	public void setCard(Card card) { this.card = card; }
	private Card card;
	
	@Column(name="date")
	public Date getDate() { return date; }
	public void setDate(Date date) { this.date = date; }
	private Date date;
	
	@Column(name="points")
	public int getPoints() { return points; }
	public void setPoints(int points) { this.points = points; }
	private int points;
	
	@Column(name="complete")
	public boolean isComplete() { return complete; }
	public void setComplete(boolean complete) { this.complete = complete; }
	private boolean complete;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="card_event_idFK")
	@IndexColumn(name = "mission_event_index")
	public List<TaskEvent> getTaskEvents() { return taskEvents; }
	public void setTaskEvents(List<TaskEvent> taskEvents) { this.taskEvents = taskEvents; }
	private List<TaskEvent> taskEvents;
}
