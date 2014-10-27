package au.moodflip.cardgame.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Mission extends Task implements Serializable{
	private static final long serialVersionUID = 1L;

	public Mission(){ }
	public Mission(String text){ 
		this.text = text;
	}
	
	@Column(name="mission_text", length=2000)
	@NotBlank
	public String getText() { return text; }
	public void setText(String text) { this.text = text; }
	private String text;
	
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("taskId[" + getTaskId() + "] ");
		buffer.append("text[" + text + "]");
		return buffer.toString();
	}
}
