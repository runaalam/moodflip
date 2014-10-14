package au.moodflip.cardgame.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.InheritanceType; 


@Entity
@Table(name="Tasks")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Task {
	public Task(){ }
	
	@Id
	@GeneratedValue
	@Column(name="task_id")
	public long getTaskId() { return taskId; }
	public void setTaskId(long taskId) { this.taskId = taskId; }
	private long taskId;
	
	@ManyToOne
//	@JoinColumn(name="card_id", nullable=false)
	@JoinColumn(name="card_idFK", updatable=false, insertable=false)
	public Card getCard() { return card; }
	public void setCard(Card card) { this.card = card; }
	private Card card;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="task_prev_id")
	public Task getPrev() { return prev; }
	public void setPrev(Task prev) { this.prev = prev; }
	private Task prev;

	@OneToOne(mappedBy="prev")
	public Task getNext() { return next; }
	public void setNext(Task next) { this.next = next; }
	private Task next;

	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("taskId[" + taskId + "] ");
		return buffer.toString();
	}
}
