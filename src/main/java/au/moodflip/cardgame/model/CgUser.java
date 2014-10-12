package au.moodflip.cardgame.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="mission_id")
	public Task getCurrentTask() { return currentTask; }
	public void setCurrentTask(Task currentTask) { this.currentTask = currentTask; }
	private Task currentTask;
}
