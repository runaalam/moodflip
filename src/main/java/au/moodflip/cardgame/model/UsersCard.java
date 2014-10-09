package au.moodflip.cardgame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Users_cards")
public class UsersCard implements Comparable<UsersCard>{
	public UsersCard() { }
	public UsersCard(long cardId) { this.cardId = cardId; }
	
	@Id
	@GeneratedValue
	private long usersCardId;
	
	@Column(name="card_id")
	public Long getCardId() { return cardId; }
	public void setCardId(Long cardId) { this.cardId = cardId; }
	private Long cardId;

	@Override
	public int compareTo(UsersCard c2) {
		return this.cardId.compareTo(c2.getCardId());
	}
	
	
}
