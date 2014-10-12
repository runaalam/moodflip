package au.moodflip.cardgame.model;

import java.io.Serializable;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
//@Table(name="Missions")
public class Mission extends Task implements Serializable{
	private static final long serialVersionUID = 1L;

	public Mission(){ }
	public Mission(String text){ 
		this.text = text;
	}
	
	@Column(name="mission_text")
	public String getText() { return text; }
	public void setText(String text) { this.text = text; }
	private String text;
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("taskId[" + getTaskId() + "] ");
//		buffer.append("cardId[" + getCard().getCardId() + "] ");
		buffer.append("text[" + text + "]");
		return buffer.toString();
	}
}
