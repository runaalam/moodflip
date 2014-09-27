package au.moodflip.cardgame.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Missions")
public class Mission implements Serializable{
	public Mission(){
		
	}
	
	public Mission(String text){
		this.text = text;
	}
	
	@Id
	@GeneratedValue
	@Column(name="mission_id")
	public long getMissionId() { return missionId; }
	public void setMissionId(long missionId) { this.missionId = missionId; }
	private long missionId;
	

	public String getText() { return text; }
	public void setText(String text) { this.text = text; }
	private String text;
}
