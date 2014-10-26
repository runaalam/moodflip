
package au.moodflip.moodtrack.model;


import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;


import au.moodflip.personalisation.model.User;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Entity
public class Data implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqData")
    @SequenceGenerator(name = "seqData", sequenceName = "data_seq", allocationSize = 3)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @Transient
    private List<Integer> moodRatingValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int moodRating;

    @Transient
    private List<Integer> copedWithTaskValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int copedWithTask;

    @Transient
    private List<Integer> hoursOfSleepingValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int hoursOfSleeping;

    @Transient
    private List<Integer> exerciseHoursValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int exerciseHours;

    @Transient
    private List<Integer> interestedValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int interested;

    @Transient
    private List<Integer> irritableValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int irritable;

    @Transient
    private List<Integer> distressedValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int distressed;

    @Transient
    private List<Integer> alertValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int alert;

    @Transient
    private List<Integer> excitedValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int excited;

    @Transient
    private List<Integer> ashamedValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int ashamed;

    @Transient
    private List<Integer> upsetValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int upset;

    @Transient
    private List<Integer> inspiredValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int inspired;

    @Transient
    private List<Integer> strongValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int strong;

    @Transient
    private List<Integer> nervousValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int nervous;

    @Transient
    private List<Integer> guiltyValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int guilty;

    @Transient
    private List<Integer> determinedValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int determined;

    @Transient
    private List<Integer> scaredValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int scared;

    @Transient
    private List<Integer> attentiveValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int attentive;

    @Transient
    private List<Integer> hostileValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int hostile;

    @Transient
    private List<Integer> jitteryValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int jittery;

    @Transient
    private List<Integer> enthusiasticValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int enthusiastic;

    @Transient
    private List<Integer> activeValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int active;

    @Transient
    private List<Integer> proudValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int proud;

    @Transient
    private List<Integer> afraidValues = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    private int afraid;

    public Data() {
    }

    public Data(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMoodRating() {
        return moodRating;
    }

    public void setMoodRating(int moodRating) {
        this.moodRating = moodRating;
    }

    public int getCopedWithTask() {
        return copedWithTask;
    }

    public void setCopedWithTask(int copedWithTask) {
        this.copedWithTask = copedWithTask;
    }

    public int getHoursOfSleeping() {
        return hoursOfSleeping;
    }

    public void setHoursOfSleeping(int hoursOfSleeping) {
        this.hoursOfSleeping = hoursOfSleeping;
    }

    public int getExerciseHours() {
        return exerciseHours;
    }

    public void setExerciseHours(int exerciseHours) {
        this.exerciseHours = exerciseHours;
    }

    public int getInterested() {
        return interested;
    }

    public void setInterested(int interested) {
        this.interested = interested;
    }

    public int getDistressed() {
        return distressed;
    }

    public void setDistressed(int distressed) {
        this.distressed = distressed;
    }

    public int getExcited() {
        return excited;
    }

    public void setExcited(int excited) {
        this.excited = excited;
    }

    public int getUpset() {
        return upset;
    }

    public void setUpset(int upset) {
        this.upset = upset;
    }

    public int getStrong() {
        return strong;
    }

    public void setStrong(int strong) {
        this.strong = strong;
    }

    public int getGuilty() {
        return guilty;
    }

    public void setGuilty(int guilty) {
        this.guilty = guilty;
    }

    public int getScared() {
        return scared;
    }

    public void setScared(int scared) {
        this.scared = scared;
    }

    public int getHostile() {
        return hostile;
    }

    public void setHostile(int hostile) {
        this.hostile = hostile;
    }

    public int getEnthusiastic() {
        return enthusiastic;
    }

    public void setEnthusiastic(int enthusiastic) {
        this.enthusiastic = enthusiastic;
    }

    public int getProud() {
        return proud;
    }

    public void setProud(int proud) {
        this.proud = proud;
    }

    public int getIrritable() {
        return irritable;
    }

    public void setIrritable(int irritable) {
        this.irritable = irritable;
    }

    public int getAlert() {
        return alert;
    }

    public void setAlert(int alert) {
        this.alert = alert;
    }

    public int getAshamed() {
        return ashamed;
    }

    public void setAshamed(int ashamed) {
        this.ashamed = ashamed;
    }

    public int getInspired() {
        return inspired;
    }

    public void setInspired(int inspired) {
        this.inspired = inspired;
    }

    public int getNervous() {
        return nervous;
    }

    public void setNervous(int nervous) {
        this.nervous = nervous;
    }

    public int getDetermined() {
        return determined;
    }

    public void setDetermined(int determined) {
        this.determined = determined;
    }

    public int getAttentive() {
        return attentive;
    }

    public void setAttentive(int attentive) {
        this.attentive = attentive;
    }

    public int getJittery() {
        return jittery;
    }

    public void setJittery(int jittery) {
        this.jittery = jittery;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getAfraid() {
        return afraid;
    }

    public void setAfraid(int afraid) {
        this.afraid = afraid;
    }

    public List<Integer> getMoodRatingValues() {
        return moodRatingValues;
    }

    public List<Integer> getCopedWithTaskValues() {
        return copedWithTaskValues;
    }

    public List<Integer> getHoursOfSleepingValues() {
        return hoursOfSleepingValues;
    }

    public List<Integer> getExerciseHoursValues() {
        return exerciseHoursValues;
    }

    public List<Integer> getInterestedValues() {
        return interestedValues;
    }

    public List<Integer> getIrritableValues() {
        return irritableValues;
    }

    public List<Integer> getDistressedValues() {
        return distressedValues;
    }

    public List<Integer> getAlertValues() {
        return alertValues;
    }

    public List<Integer> getExcitedValues() {
        return excitedValues;
    }

    public List<Integer> getAshamedValues() {
        return ashamedValues;
    }

    public List<Integer> getUpsetValues() {
        return upsetValues;
    }

    public List<Integer> getInspiredValues() {
        return inspiredValues;
    }

    public List<Integer> getStrongValues() {
        return strongValues;
    }

    public List<Integer> getNervousValues() {
        return nervousValues;
    }

    public List<Integer> getGuiltyValues() {
        return guiltyValues;
    }

    public List<Integer> getDeterminedValues() {
        return determinedValues;
    }

    public List<Integer> getScaredValues() {
        return scaredValues;
    }

    public List<Integer> getAttentiveValues() {
        return attentiveValues;
    }

    public List<Integer> getHostileValues() {
        return hostileValues;
    }

    public List<Integer> getJitteryValues() {
        return jitteryValues;
    }

    public List<Integer> getEnthusiasticValues() {
        return enthusiasticValues;
    }

    public List<Integer> getActiveValues() {
        return activeValues;
    }

    public List<Integer> getProudValues() {
        return proudValues;
    }

    public List<Integer> getAfraidValues() {
        return afraidValues;
    }
}

