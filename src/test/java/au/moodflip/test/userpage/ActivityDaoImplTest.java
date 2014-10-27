package au.moodflip.test.userpage;

import java.util.Date;
import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;
import au.moodflip.userpage.model.Activity;
import au.moodflip.userpage.service.ActivityService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-persistence-context.xml" })
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class ActivityDaoImplTest {
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private ActivityService activityService;
	
	public void testGetActivityByUser(){
		long userId = 0;
		User user = userManager.getUserById(userId);
		Activity activity = new Activity(user,"Write a status", new Date());
		activityService.addActivity(activity);
		activity = new Activity(user,"Write a comment on a status", new Date());
		
		List<Activity> userActivityList = activityService.getActivityListByUserId(userId);
		
		for(Activity a : userActivityList){
			
		}
		
	}
}
