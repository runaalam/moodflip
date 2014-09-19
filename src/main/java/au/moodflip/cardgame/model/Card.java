package au.moodflip.cardgame.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Cards")
public class Card implements Serializable{
	public enum Symptom{
//		SADNESS, LOSS_OF_INTEREST, APPETITE, SLEEP, THINKING, GUILT, TIRED, MOVEMENT, SUICIDAL_IDEATION;
		SADNESS("SADNESS", "Sadness"), 
		LOSS_OF_INTEREST("LOSS_OF_INTEREST", "Loss of interest"),
		APPETITE("APPETITE", "Appetite"),
		SLEEP("SLEEP", "Sleep"),
		THINKING("THINKING", "Thinking / Concentration"),
		GUILT("GUILT", "Guilt / worthlessness"),
		TIRED("TIRED", "Tired"),
		MOVEMENT("MOVEMENT", "Movement"),
		SUICIDAL_IDEATION("SUICIDAL_IDEATION", "Suicidal ideation");
		
		private final String text;
		private final String id;
		
		private Symptom(final String id, final String text){
			this.id = id;
			this.text = text;
		}

		public String getText() {
			return text;
		}

		public String getId() {
			return id;
		}
	}
	
//	public static void main(String[] args) {
//		Symptom s = Symptom.APPETITE;
//		Symptom ret = Symptom.valueOf("APPETITE");
//		System.out.println("return " + ret);
//	}
	
	@Id
	@GeneratedValue
	@Column(name="card_id")
	private long cardId;
	
	@Column(name="user_id")
	private long userId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="symptom")
	private Symptom symptom;
	
	@Column(name="level")
	private int level;
	
	@Column(name="completions")
	private long completions;
	
	@Column(name="attempts")
	private long attempts;
	
	@Column(name="intro")
	private String intro;
	
	@Column(name="outro")
	private String outro;
	
	@Column(name="average_rating")
	private double avgRating;

	public long getCardId() {
		return cardId;
	}

	public void setCardId(long cardId) {
		this.cardId = cardId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Symptom getSymptom() {
		return symptom;
	}

	public void setSymptom(Symptom symptom) {
		this.symptom = symptom;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getCompletions() {
		return completions;
	}

	public void setCompletions(long completions) {
		this.completions = completions;
	}

	public long getAttempts() {
		return attempts;
	}

	public void setAttempts(long attempts) {
		this.attempts = attempts;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getOutro() {
		return outro;
	}

	public void setOutro(String outro) {
		this.outro = outro;
	}

	public double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}

	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Title: " + title + ";");
		buffer.append("Card id: " + cardId + ";");
		buffer.append("User id: " + userId + ";");
		buffer.append("Intro: " + intro + ";");
		buffer.append("End msg: " + outro + ";");
		buffer.append("Avg rating: " + avgRating + ";");
		buffer.append("Level: " + level + ";");
		buffer.append("Symptom group: " + symptom + ";");
		buffer.append("Attempts: " + attempts + ";");
		buffer.append("Completions: " + completions + ";");
		return buffer.toString();
	}
}
