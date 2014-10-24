package au.moodflip.moodtrack.model;

import au.moodflip.personalisation.model.User;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Charts implements Serializable {

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate = new Date();

    private User user;

    public Charts(User user) {
        this.user = user;
    }

   

	

	public Charts() {
		// TODO Auto-generated constructor stub
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
    List<String> dates = new ArrayList<String>();
	List<Integer> moodRating = new ArrayList<Integer>();

	public List<String> getDates() {
		return dates;
	}
	public void setDates(List<String> dates) {
		this.dates = dates;
	}
	public List<Integer> getMoodRating() {
		return moodRating;
	}
	public void setMoodRating(List<Integer> moodRating) {
		this.moodRating = moodRating;
	}	
}


