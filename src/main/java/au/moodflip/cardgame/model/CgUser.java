package au.moodflip.cardgame.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Cg_users")
public class CgUser {
	public CgUser() { }
	public CgUser(long cgUserId) { this.cgUserId = cgUserId; }
	
	@Id
	@Column(name="cg_user_id")
	public long getCgUserId() { return cgUserId; }
	public void setCgUserId(long cgUserId) { this.cgUserId = cgUserId; }
	private long cgUserId;
	
	@OneToOne//(cascade=CascadeType.ALL)
	@JoinColumn(name="task_event_id")
	public TaskEvent getCurrentTaskEvent() { return currentTaskEvent; }
	public void setCurrentTaskEvent(TaskEvent currentTaskEvent) { this.currentTaskEvent = currentTaskEvent; }
	private TaskEvent currentTaskEvent;
	
	@Column(name="points")
	public int getPoints() { return points; }
	public void setPoints(int points) { this.points = points; }
	private int points;

	@Column(name="completions")
	public int getCompletions() { return completions; }
	public void setCompletions(int completions) { this.completions = completions; }
	private int completions;
	
	@Column(name="attempts")
	public int getAttempts() { return attempts; }
	public void setAttempts(int attempts) { this.attempts = attempts; }
	private int attempts;

	
}
