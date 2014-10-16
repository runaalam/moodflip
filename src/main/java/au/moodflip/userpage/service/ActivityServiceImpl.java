package au.moodflip.userpage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.userpage.dao.ActivityDao;
import au.moodflip.userpage.model.Activity;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityDao activityDao;
	
	@Override
	public void addActivity(Activity activity) {
		activityDao.addActivity(activity);
		
	}

	@Override
	public List<Activity> getActivityList() {
		return activityDao.getActivityList();
	}

	@Override
	public List<Activity> getActivityListByUserId(Long userId) {
		return activityDao.getActivityListByUserId(userId);
	}

}
