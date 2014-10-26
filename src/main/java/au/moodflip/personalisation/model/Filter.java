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
@Table(name = "Filter")
public class Filter implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	
	
	@Size(min = 3, message = "Name must be at least 3 characters!")
	@Column(name = "word", unique = true)
	@UniqueUsername(message = "Such username already exists!")
	private String word;
	public void setWord(String word){
		this.word = word;
	}
	public String getWord(){
		return word;
	}
	
	
	
	
	
	
}
