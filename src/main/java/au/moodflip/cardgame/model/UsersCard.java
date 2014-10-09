package au.moodflip.cardgame.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Users_cards")
public class UsersCard implements Comparable<UsersCard>{
	@EmbeddedId
	public UsersCardPK getId() { return id; }
	public void setId(UsersCardPK id) { this.id = id; }
	private UsersCardPK id = null;

	public UsersCard() { }
	public UsersCard(long userId, long cardId) {
		id = new UsersCardPK();
		id.setUserId(userId);
		id.setCardId(cardId); 
	}

	@Override
	public int compareTo(UsersCard c2) {
		if (id.getCardId() == c2.id.getCardId())	return 0;
		if (id.getCardId() < c2.id.getCardId())		return -1;
		return 1;
	}
	
	@Embeddable
	public static class UsersCardPK implements Serializable{
		private static final long serialVersionUID = 1L;
		
		@Column(name="user_id")
		private long userId;
		@Column(name="card_id")
		private long cardId;
		public UsersCardPK() { }
		public UsersCardPK(long userId, long cardId) { 
			this.userId = userId;
			this.cardId = cardId;
		}
		
		public long getUserId() {
			return userId;
		}
		public void setUserId(long userId) {
			this.userId = userId;
		}
		public long getCardId() {
			return cardId;
		}
		public void setCardId(long cardId) {
			this.cardId = cardId;
		}
		
		@Override
		public int hashCode(){
			return (int)(userId + cardId);
		}
		
		@Override
		public boolean equals(Object o){
			if(o == null)                return false;
		    if(!(o instanceof UsersCard)) return false;
		    UsersCard other = (UsersCard)o;
		    if (this.userId != other.id.userId)	return false;
		    if (this.cardId != other.id.cardId)	return false;
		    return true;
		}
	}
}
