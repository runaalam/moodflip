package au.moodflip.personalisation.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "User")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;


	@Column(name = "banned")
	private boolean banned;

	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "user_level")
	private int userLevel;
	
	@Column(name = "privacy_setting")
	private String privacySetting;

	

	//@ManyToOne
//private Forum forum;

	public long getId() {
		return id;
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
	
	public int userLevel(){
		return userLevel;
	}
	
	public String getPrivacySetting(){
		return privacySetting;
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
	
	public void setPrivacySetting(String privacySetting){
		this.privacySetting = privacySetting;
	}
	
	
	
}
