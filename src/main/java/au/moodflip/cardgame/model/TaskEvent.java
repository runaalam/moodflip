package au.moodflip.cardgame.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Task_events")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class TaskEvent {
	public TaskEvent() {}
	public TaskEvent(Task task, Date date) {
		this.task = task;
		this.date = date;
	}
	
	@Id
	@GeneratedValue
	@Column(name="task_event_id")
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	private long id;
	
	@ManyToOne
	@JoinColumn(name="card_event_idFK")
	public CardEvent getCardEvent() { return cardEvent; }
	public void setCardEvent(CardEvent cardEvent) { this.cardEvent = cardEvent; }
	private CardEvent cardEvent;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="task_id")
	public Task getTask() { return task; }
	public void setTask(Task task) { this.task = task; }
	private Task task;
	
	@Column(name="date")
	public Date getDate() { return date; }
	public void setDate(Date date) { this.date = date; }
	private Date date;
}
