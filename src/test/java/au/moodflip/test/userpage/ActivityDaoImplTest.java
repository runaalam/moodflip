package au.moodflip.test.userpage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;
import au.moodflip.userpage.dao.ActivityDao;
import au.moodflip.userpage.model.Activity;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-persistence-context.xml" })
@TransactionConfiguration
@Transactional
public class ActivityDaoImplTest {
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private ActivityDao activityDao;
	
	@Test
	public void testUserActivity(){
		
		User user = userManager.getUserByUsername("user");
		Activity activity = new Activity(user,"Updated status", new Date());
		activityDao.addActivity(activity);
		activity = new Activity(user,"commented on a status", new Date());
		activityDao.addActivity(activity);

		List<Activity> userActivityList = activityDao.getActivityListByUserId(user.getId());
		assertNotNull(userActivityList);
		assertEquals(userActivityList.size(), 2);
	}
}
