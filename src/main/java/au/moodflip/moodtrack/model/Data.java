package au.moodflip.moodtrack.model;



import javax.persistence.*;

import java.io.Serializable;


@Entity
public class Data implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqData")
    @SequenceGenerator(name = "seqData", sequenceName = "data_seq", allocationSize = 1)
    private int id;

    private int moodRating;
    private int copedWithTask;
    private int hoursOfSleeping;
    private int exerciseHours;

    private int interested;
    private int irritable;

    private int distressed;
    private int alert;

    private int excited;
    private int ashamed;

    private int upset;
    private int inspired;

    private int strong;
    private int nervous;

    private int guilty;
    private int determined;

    private int scared;
    private int attentive;

    private int hostile;
    private int jittery;

    private int enthusiastic;
    private int active;

    private int proud;
    private int afraid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}

