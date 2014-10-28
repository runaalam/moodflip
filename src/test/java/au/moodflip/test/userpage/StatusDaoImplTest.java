package au.moodflip.test.userpage;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;
import au.moodflip.userpage.dao.AssessmentDao;
import au.moodflip.userpage.dao.StatusCommentDao;
import au.moodflip.userpage.dao.StatusDao;
import au.moodflip.userpage.model.Status;
import au.moodflip.userpage.model.StatusComment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-persistence-context.xml" })
@TransactionConfiguration
@Transactional
public class StatusDaoImplTest {
	
	@Autowired
	private UserManager userService;

	@Autowired
	private StatusDao statusDao;
	
	@Autowired
	private StatusCommentDao statusCommentDao;
	
	@Ignore	
//	@Before
	public void setup() {
		User user = userService.getUserByUsername("user");
		User commentUser;
		Status status;
		StatusComment comment;
		int i, j;
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");

		for(i = 0; i < 5; i++) {
			
			status = new Status();
			try {
				Date date = ft.parse("2014-10-" + String.format("%02d", i+1)); // start at day 1
				status.setSubmitDate(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			status.setContent((i+1) + ": I am " + user.getName() + " and this is my nice status #" + (i+1));
			status.setUser(user);
			statusDao.addStatus(status);
			Set<StatusComment> comments = new LinkedHashSet<StatusComment>();
			status.setStatusComments(comments);
			
			for(j = 1; j < 5; j++) {
				comment = new StatusComment();
				commentUser = userService.getUserByUsername("user"+j);
				comment.setUser(commentUser);
				comment.setStatus(status);
				comment.setContent((j+1) + ": I am " + user.getName() + " and this is my nice comment #" + (j+1));
				
				try {
					Date date = ft.parse("2014-11-" + String.format("%02d", i+1)); // start at day 1
					comment.setCommentDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				status.getStatusComments().add(comment);
			}
			statusDao.addStatus(status);
		}		
	}

	@Test
	public void testSaveAndListStatus() {
		User user = userService.getUserByUsername("user");		
		assertNotNull(statusDao.listStatusByUserId(user.getId()));
		
		List<Status> statuses = statusDao.listStatusByUserId(user.getId());
		assertEquals(statuses.size(), 5);
		
		for(Status status: statuses) {
			assertNotNull(statusCommentDao.listStatusComment(status.getId()));
			List<StatusComment> comments = statusCommentDao.listStatusComment(status.getId());
			assertEquals(comments.size(), 4);
		}
	}
}
