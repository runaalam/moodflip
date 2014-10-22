package au.moodflip.userpage.model;

import java.util.ArrayList;
import java.util.List;

public class ChartData {

	List<String> dates = new ArrayList<String>();
	List<Integer> scores = new ArrayList<Integer>();

	public List<String> getDates() {
		return dates;
	}
	public void setDates(List<String> dates) {
		this.dates = dates;
	}
	public List<Integer> getScores() {
		return scores;
	}
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}	
}
