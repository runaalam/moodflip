package au.moodflip.userpage.service;
//
import java.util.List;

import au.moodflip.userpage.model.Activity;

public interface ActivityService {
	
	public void addActivity(Activity activity);
	
	public List<Activity> getActivityList();
	
	public List<Activity> getActivityListByUserId(Long userId);

}
