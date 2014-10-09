package au.moodflip.cardgame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Cg_users")
public class CgUser {
	public CgUser() { }
	public CgUser(long cgUserId) { this.cgUserId = cgUserId; }
	
	@Id
	@Column(name="cg_user_id")
//	@GeneratedValue
	public long getCgUserId() { return cgUserId; }
	public void setCgUserId(long cgUserId) { this.cgUserId = cgUserId; }
	private long cgUserId;
}
