package au.moodflip.cardgame.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="Cg_users")
public class CgUser {
	@Id
	@Column(name="cg_user_id")
//	@GeneratedValue
	public long getCgUserId() { return cgUserId; }
	public void setCgUserId(long cgUserId) { this.cgUserId = cgUserId; }
	private long cgUserId;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="cg_user_id")
	@Cascade(CascadeType.ALL)
	public Set<Card> getCards() { return cards; }
	public void setCards(Set<Card> cards) {	this.cards = cards;	}
	private Set<Card> cards;
	
}
