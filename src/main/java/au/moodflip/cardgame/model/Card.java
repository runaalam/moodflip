package au.moodflip.cardgame.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;


@Entity
@Table(name="Cards")
public class Card implements Serializable{
	public enum Symptom{
		SADNESS("Sadness"), 
		LOSS_OF_INTEREST("Loss of interest"),
		APPETITE("Appetite"),
		SLEEP("Sleep"),
		THINKING("Thinking / Concentration"),
		GUILT("Guilt / worthlessness"),
		TIRED("Tired"),
		MOVEMENT("Movement"),
		SUICIDAL_IDEATION("Suicidal ideation");
		
		private final String text;

		private Symptom(final String text){
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
	
//	public static void main(String[] args) {
//		Symptom s = Symptom.APPETITE;
//		Symptom ret = Symptom.valueOf("APPETITE");
//		System.out.println("return " + ret + " toString:" + Symptom.GUILT);
//	}
	
	@Id
	@GeneratedValue
	@Column(name="card_id")
	private long cardId;
	
	@Column(name="user_id")
	private long userId;
	
	@Column(name="title")
	@NotBlank
	@Size(max=50)
	private String title;
	
	@Column(name="symptom")
	@NotNull
	private Symptom symptom;
	
	@Column(name="level")
	@Min(1) @Max(7)
	private int level;
	
	@Column(name="completions")
	private long completions;
	
	@Column(name="attempts")
	private long attempts;
	
	@Column(name="intro")
	@NotBlank
	private String intro;
	
	@Column(name="outro")
	@NotBlank
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
	
	@Bean
	public MessageSource messageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
}
