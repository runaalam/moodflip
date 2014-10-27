package au.moodflip.personalisation.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import au.moodflip.personalisation.annotation.UniqueUsername;

@Entity
@Table(name = "Friend")
public class Friend implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Column(name = "sender")
	private String sender;
	public void setSender(String sender){
		this.sender = sender;
	}
	public String getSender(){
		return this.sender;
	}
	
	
	@Column(name = "receiver")
	private String receiver;
	public void setReceiver(String receiver){
		this.receiver = receiver;
	}
	public String getReceiver(){
		return this.receiver;
	}
	
	@Column(name = "friends")
	private boolean friends;
	public void setFriends(boolean friends){
		this.friends = friends;
	}
	public boolean getFriend(){
		return this.friends;
	}
	
}
