package au.moodflip.cardgame.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.HibernateException;
import org.hibernate.annotations.CascadeType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cascade;
import org.hibernate.cfg.Configuration;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.Transactional;


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
	
	public Card() { }
	public Card(String title, 
				int level, 
				Symptom symptom, 
				String intro, 
				List<Mission> missions, 
				String outro,
				long attempts,
				long completions,
				double avgRating){
		this.title = title;
		this.level = level;
		this.symptom = symptom;
		this.intro = intro;
		this.missions = missions;
		this.outro = outro;
		this.attempts = attempts;
		this.completions = completions;
		this.avgRating = avgRating;
	}
	
	@Id
	@GeneratedValue
	@Column(name="card_id")
	public long getCardId() { return cardId; }
	public void setCardId(long cardId) { this.cardId = cardId; }
	private long cardId;
	
//	@Column(name="cg_user_id")
//	public long getUserId() { return userId; }
//	public void setUserId(long userId) { this.userId = userId; }
//	private long userId;
	
	@Column(name="title")
	@NotBlank
	@Size(max=50)
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	private String title;
	
	@Column(name="symptom")
	@NotNull
	public Symptom getSymptom() { return symptom; }
	public void setSymptom(Symptom symptom) { this.symptom = symptom; }
	private Symptom symptom;
	
	@Column(name="level")
	@Min(1) @Max(7)
	public int getLevel() { return level; }
	public void setLevel(int level) { this.level = level; }
	private int level;
		
	
	@Column(name="completions")
	public long getCompletions() { return completions; }
	public void setCompletions(long completions) { this.completions = completions; }
	private long completions;
	
	
	@Column(name="attempts")
	public long getAttempts() { return attempts; }
	public void setAttempts(long attempts) { this.attempts = attempts; }
	private long attempts;
	
	
	@Column(name="intro")
	@NotBlank
	public String getIntro() { return intro; }
	public void setIntro(String intro) { this.intro = intro; }
	private String intro;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="card_id")
	@OrderColumn(name="missions_order")
	@Cascade(CascadeType.ALL)
	public List<Mission> getMissions() { return missions; }
	public void setMissions(List<Mission> missions) { this.missions = missions; }
	private List<Mission> missions;
	
	
	@Column(name="outro")
	@NotBlank
	public String getOutro() { return outro; }
	public void setOutro(String outro) { this.outro = outro; }
	private String outro;
	
	@Column(name="average_rating")
	public double getAvgRating() { return avgRating; }
	public void setAvgRating(double avgRating) { this.avgRating = avgRating; }
	private double avgRating;

	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("Title[" + title + "] ");
		buffer.append("Card id[" + cardId + "] ");
		buffer.append("Intro[" + intro + "] ");
		buffer.append("End msg[" + outro + "] ");
		buffer.append("Avg rating[" + avgRating + "] ");
		buffer.append("Level[" + level + "] ");
		buffer.append("Symptom group[" + symptom + "] ");
		buffer.append("Attempts[" + attempts + "] ");
		buffer.append("Completions[" + completions + "]\n");
		if (!missions.isEmpty()){
			Iterator<Mission> ms = missions.iterator();
			for (int i=0; ms.hasNext(); i++){
				Mission m = ms.next();
				buffer.append("\tMission " + i + "[" + m.getText() + "]\n");
			}
		}
		return buffer.toString();
	}
	
	@Bean
	public MessageSource messageSource(){
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
}
