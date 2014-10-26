package au.moodflip.cardgame.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class MissionEvent extends TaskEvent{
	public MissionEvent(){ }
	public MissionEvent(Task mission, int points, Date date){ 
		setTask(mission);
		setDate(date);
		this.points = points;
	}
	
	@Column(name="points")
	public int getPoints() { return points; }
	public void setPoints(int points) { this.points = points; }
	private int points;

	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("missionEventId[" + getId() + "] ");
		buffer.append("date[" + getDate() + "] ");
		buffer.append("pts[" + points + "] ");
		return buffer.toString();
	}
}
