package au.moodflip.moodtrack.model;

import au.moodflip.personalisation.model.User;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class ReportCmd implements Serializable {

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate = new Date();

    private User user;

    public ReportCmd(User user) {
        this.user = user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


