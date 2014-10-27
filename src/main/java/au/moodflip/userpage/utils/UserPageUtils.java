package au.moodflip.userpage.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import au.moodflip.userpage.model.Assessment;
import au.moodflip.userpage.model.ChartData;

public class UserPageUtils {

	public static ChartData prepareChartData(List<Assessment> assessments) {
		
		ChartData chartData = new ChartData();
		List<String> dates = new ArrayList<String>();
		List<Integer> scores = new ArrayList<Integer>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");		
		
		for(Assessment assessment: assessments) {
			dates.add(formatter.format(assessment.getDate()));
			scores.add(assessment.getScore());
		}
		
		chartData.setDates(dates);
		chartData.setScores(scores);
		
		return chartData;		
	}
	
	public static ChartData prepareDummyChartData() {
		
		ChartData chartData = new ChartData();
		List<String> dates = new ArrayList<String>();
		List<Integer> scores = new ArrayList<Integer>();
		
		dates.add("2014-Jan-10");
		scores.add(5);

		dates.add("2014-Mar-3");
		scores.add(0);
		
		dates.add("2014-Jul-22");
		scores.add(9);

		dates.add("2014-Oct-10");
		scores.add(6);

		chartData.setDates(dates);
		chartData.setScores(scores);
		
		return chartData;		
	}	
}
