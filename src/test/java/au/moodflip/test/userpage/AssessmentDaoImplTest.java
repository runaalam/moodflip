package au.moodflip.test.userpage;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;
import au.moodflip.userpage.dao.AssessmentDao;
import au.moodflip.userpage.model.Answer;
import au.moodflip.userpage.model.Assessment;
import au.moodflip.userpage.model.Question;
import au.moodflip.userpage.model.Response;
import au.moodflip.userpage.service.AssessmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-persistence-context.xml" })
@TransactionConfiguration
@Transactional
public class AssessmentDaoImplTest {
	
	@Autowired
	private UserManager userService;

	@Autowired
	private AssessmentDao assessmentDao;
	
	@Autowired
	private AssessmentService assessmentService;
	
	@Ignore
//	@Before
	public void setup() {
		User user = userService.getUserByUsername("user");
		List<Question> quesList = assessmentDao.getQuestions();
		Random rand = new Random();
		List<Response> rList;
		Response r = null;
		Assessment assessment;
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		
		// create 12 survey assessments with random data
		for (int i=0; i < 12; i++){
			quesList = assessmentDao.getQuestions();
			assessment = new Assessment();
			rList = new ArrayList<Response>();
			for (Question q : quesList){
				r = new Response();
				Answer a = new Answer();
				a.setValue(rand.nextInt(5));	
				r.setAnswer(a);
				r.setQuestion(q);
				r.setUser(user);
				r.setAssessment(assessment);
				rList.add(r);
			}
			assessment.setResponseList(rList);
			assessment.setUser(user);
			try {
				Date t = ft.parse("2014-10-" + String.format("%02d", i+1)); // start at day 1
				assessment.setDate(t);
				assessmentDao.save(assessment);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}		
	}

	@Test
	public void testSaveAndListAssessment() {
		List<Assessment> assessments = assessmentDao.listAssessmentByUserId(userService.getUserByUsername("user").getId());
		assertNotNull(assessments);
		assertEquals(assessments.size(), 12);
	}
	
	@Test
	public void testAssessmentScoreCalculation() {
		for (Assessment assessment: assessmentDao.listAssessmentByUserId(userService.getUserByUsername("user").getId())) {
			assessment = assessmentService.calculateAssessmentScore(assessment);
			assertNotNull(assessment.getResultDetails());
		}
	}
}
