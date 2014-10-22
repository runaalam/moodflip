package au.moodflip.userpage.dao;
//
import java.util.List;

import au.moodflip.userpage.model.Activity;

public interface ActivityDao {
	
	public void addActivity(Activity activity);
	
	public List<Activity> getActivityList();
	
	public List<Activity> getActivityListByUserId(Long userId);
}
