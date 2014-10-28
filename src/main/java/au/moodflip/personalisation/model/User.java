package au.moodflip.personalisation.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import au.moodflip.cardgame.model.TaskEvent;
import au.moodflip.personalisation.annotation.UniqueUsername;

@Entity
@Table(name = "User")
public class User implements Serializable {
	
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	public enum Privacy{
		OPEN("Open"), 
		FRIEND("Friends"),
		SELF("Self");
		
		private final String text;

		private Privacy(final String text){
			this.text = text;
		}

		public String getText() {
			return text;
		}
		
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "name")
	private String name;
	
	@Size(min = 3, message = "Name must be at least 3 characters!")
	@Column(name = "username", unique = true)
	@UniqueUsername(message = "Such username already exists!")
	private String username;
	
	
	@Size(min = 6, message = "Password must be at least 6 characters!")
	@Column(name = "password")
	@JsonIgnore
	@NotNull
	private String password;

	@Column(name = "banned")
	private boolean banned;

	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "privacy")
	@NotNull
	private Privacy privacy;
	public void setPrivacy(Privacy privacy){
		this.privacy = privacy;
	}
	public Privacy getPrivacy(){
		return privacy;
	}
	
	@Column(name = "friends")

	
	@ManyToMany
	@JoinTable
	@Fetch(FetchMode.SELECT)
	@JsonIgnore
	private Set<Role> roles;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public boolean isBanned(){
		return banned;
	}
	
	public Date createAt(){
		return createdAt;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public void setPassword(String password){ 
				this.password = password;
	}
	
	public void setBanned(boolean banned){
		this.banned = banned;
	}
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setCreateAt(Date createdAt){
		this.createdAt = createdAt;
	}
	

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	/* card game properties */
	
	@OneToOne
	@JoinColumn(name="task_event_id")
	private TaskEvent currentTaskEvent;
	public TaskEvent getCurrentTaskEvent() { return currentTaskEvent; }
	public void setCurrentTaskEvent(TaskEvent currentTaskEvent) { this.currentTaskEvent = currentTaskEvent; }
	
	@Column(name="points")
	public int getPoints() { return points; }
	public void setPoints(int points) { this.points = points; }
	private int points;

	@Column(name="completions")
	public int getCompletions() { return completions; }
	public void setCompletions(int completions) { this.completions = completions; }
	private int completions;
	
	@Column(name="attempts")
	public int getAttempts() { return attempts; }
	public void setAttempts(int attempts) { this.attempts = attempts; }
	private int attempts;
	
}
