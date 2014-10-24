package au.moodflip.personalisation.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import au.moodflip.personalisation.annotation.UniqueUsername;

@Entity
@Table(name = "User")
public class User implements Serializable {

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
	
	@Column(name = "password")
	@JsonIgnore
	private String password;

	@Column(name = "banned")
	private boolean banned;

	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name = "privacy_setting")
	private String privacySetting;
	
	@ManyToMany
	@JoinTable
	@Fetch(FetchMode.SELECT)
	@JsonIgnore
	private Set<Role> roles;

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
	
}
