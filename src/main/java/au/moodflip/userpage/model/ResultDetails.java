package au.moodflip.userpage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import au.moodflip.personalisation.model.User;

@Entity
@Table(name = "ResultDetails")
public class ResultDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name = "dysphoria")
	private double dysphoria;
	
	@Column(name = "anhedonia")
	private double anhedonia;
	
	@Column(name = "appetite")
	private double appetite;
	
	@Column(name = "sleep")
	private double sleep;
	
	@Column(name = "concentration")
	private double concentration;
	
	@Column(name = "guilt")
	private double guilt;
	
	@Column(name = "fatigue")
	private double fatigue;
	
	@Column(name = "agitation")
	private double agitation;
	
	@Column(name = "suicidalIdeation")
	private double suicidalIdeation;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public double getDysphoria() {
		return dysphoria;
	}
	public void setDysphoria(double dysphoria) {
		this.dysphoria = dysphoria;
	}

	public double getAnhedonia() {
		return anhedonia;
	}

	public void setAnhedonia(double anhedonia) {
		this.anhedonia = anhedonia;
	}

	public double getAppetite() {
		return appetite;
	}

	public void setAppetite(double appetite) {
		this.appetite = appetite;
	}

	public double getSleep() {
		return sleep;
	}

	public void setSleep(double sleep) {
		this.sleep = sleep;
	}

	public double getConcentration() {
		return concentration;
	}

	public void setConcentration(double concentration) {
		this.concentration = concentration;
	}

	public double getGuilt() {
		return guilt;
	}

	public void setGuilt(double guilt) {
		this.guilt = guilt;
	}

	public double getFatigue() {
		return fatigue;
	}

	public void setFatigue(double fatigue) {
		this.fatigue = fatigue;
	}

	public double getAgitation() {
		return agitation;
	}

	public void setAgitation(double agitation) {
		this.agitation = agitation;
	}

	public double getSuicidalIdeation() {
		return suicidalIdeation;
	}

	public void setSuicidalIdeation(double suicidalIdeation) {
		this.suicidalIdeation = suicidalIdeation;
	}
	
}
