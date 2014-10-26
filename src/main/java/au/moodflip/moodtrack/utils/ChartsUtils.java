package au.moodflip.moodtrack.utils;



import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import au.moodflip.moodtrack.model.Data;
import au.moodflip.moodtrack.model.Charts;

public class ChartsUtils {
	
		public static Charts prepareChartData(List<Data> datas) {
			
			Charts charts = new Charts();
			List<String> dates = new ArrayList<String>();
			List<Integer> moodRating= new ArrayList<Integer>();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			List<Integer> copedWithTask= new ArrayList<Integer>();
			
			for(Data data: datas) {
				dates.add(formatter.format(data.getDate()));
				moodRating.add(data.getMoodRating());
				copedWithTask.add(data.getCopedWithTask());
			}
			
			charts.setDates(dates);
			charts.setMoodRating(moodRating);
			charts.setCopedWithTask(copedWithTask);
			
			return charts;		
		}
		
		public static Charts prepareColumnChartData(List<Data> datas) {
			
			Charts bCharts = new Charts();
			List<String> dates = new ArrayList<String>();
			List<Integer> hoursOfSleeping= new ArrayList<Integer>();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");		
			
			for(Data data: datas) {
				dates.add(formatter.format(data.getDate()));
				hoursOfSleeping.add(data.getHoursOfSleeping());
			}
			
			bCharts.setDates(dates);
			bCharts.setHoursOfSleeping(hoursOfSleeping);
			
			return bCharts;		
		}
		
		public static Charts prepareDummyChartData() {
			
			Charts charts = new Charts();
			List<String> dates = new ArrayList<String>();
			List<Integer> moodRating = new ArrayList<Integer>();
			
			dates.add("2014-Jan-10");
			moodRating.add(5);

			dates.add("2014-Mar-3");
			moodRating.add(0);
			
			dates.add("2014-Jul-22");
			moodRating.add(9);

			dates.add("2014-Oct-10");
			moodRating.add(6);

			charts.setDates(dates);
			charts.setMoodRating(moodRating);
			
			return charts;		
		}


	}



