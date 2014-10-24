package au.moodflip.comm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import au.moodflip.cardgame.model.Card;
import au.moodflip.personalisation.model.User;

@Entity
@Table(name = "CardSuggest")
public class CardSuggest implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "up_vote")
	private int upVote;

	@Column(name = "down_vote")
	private int downVote;
	
	@ManyToOne
	private Topic topic;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Card card;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getUpVote() {
		return upVote;
	}

	public void setUpVote(int upVote) {
		this.upVote = upVote;
	}

	public int getDownVote() {
		return downVote;
	}

	public void setDownVote(int downVote) {
		this.downVote = downVote;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
}
