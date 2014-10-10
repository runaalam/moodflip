package au.moodflip.cardgame.model;

import java.io.Serializable;
import java.util.Iterator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Missions")
public class Mission implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Mission(){ }
	public Mission(String text, Card card){ 
		this.text = text;
		this.card = card;
	}
	
	@Id
	@GeneratedValue
	@Column(name="mission_id")
	public long getMissionId() { return missionId; }
	public void setMissionId(long missionId) { this.missionId = missionId; }
	private long missionId;
	
	@ManyToOne
	@JoinColumn(name="card_id", nullable=false)
	public Card getCard() { return card; }
	public void setCard(Card card) { this.card = card; }
	private Card card;
	
	public String getText() { return text; }
	public void setText(String text) { this.text = text; }
	private String text;
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("missionId[" + missionId + "] ");
		buffer.append("cardId[" + card.getCardId() + "] ");
		buffer.append("text[" + text + "]");
		return buffer.toString();
	}
}
