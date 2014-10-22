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
	
	@OneToOne
	@JoinColumn(name = "assessment_id")
	private Assessment assessment;
	
	@Column(name = "dysphoria")
	private float dysphoria;
	
	@Column(name = "anhedonia")
	private float anhedonia;
	
	@Column(name = "appetite")
	private float appetite;
	
	@Column(name = "sleep")
	private float sleep;
	
	@Column(name = "concentration")
	private float concentration;
	
	@Column(name = "guilt")
	private float guilt;
	
	@Column(name = "fatigue")
	private float fatigue;
	
	@Column(name = "agitation")
	private float agitation;
	
	@Column(name = "suicidalIdeation")
	private float suicidalIdeation;

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

	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	public float getDysphoria() {
		return dysphoria;
	}

	public void setDysphoria(float dysphoria) {
		if(dysphoria >= 1)
			dysphoria = dysphoria/3;
		else dysphoria = 0;
	}

	public float getAnhedonia() {
		return anhedonia;
	}

	public void setAnhedonia(float anhedonia) {
		if(anhedonia >= 1)
			anhedonia = anhedonia/2;
		else anhedonia = 0;
	}

	public float getAppetite() {
		return appetite;
	}

	public void setAppetite(float appetite) {
		if(appetite >= 1)
			appetite = appetite/2;
		else appetite = 0;
	}

	public float getSleep() {
		return sleep;
	}

	public void setSleep(float sleep) {
		if(sleep >= 1)
			sleep = sleep/3;
		else sleep = 0;
	}

	public float getConcentration() {
		return concentration;
	}

	public void setConcentration(float concentration) {
		if(concentration >= 1)
			concentration = concentration/2;
		else concentration = 0;
	}

	public float getGuilt() {
		return guilt;
	}

	public void setGuilt(float guilt) {
		if(guilt >= 1)
			guilt = guilt/2;
		else guilt = 0;
	}

	public float getFatigue() {
		return fatigue;
	}

	public void setFatigue(float fatigue) {
		if(fatigue >= 1)
			fatigue = fatigue/2;
		else fatigue = 0;
	}

	public float getAgitation() {
		return agitation;
	}

	public void setAgitation(float agitation) {
		if(agitation >= 1)
			agitation = agitation/2;
		else agitation = 0;
	}

	public float getSuicidalIdeation() {
		return suicidalIdeation;
	}

	public void setSuicidalIdeation(float suicidalIdeation) {
		if(suicidalIdeation >= 1)
			suicidalIdeation = suicidalIdeation/2;
		else suicidalIdeation = 0;
	}
}
