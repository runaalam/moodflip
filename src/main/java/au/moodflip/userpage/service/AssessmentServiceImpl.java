package au.moodflip.userpage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.moodflip.userpage.dao.AssessmentDao;
import au.moodflip.userpage.model.Question;


@Service
public class AssessmentServiceImpl implements AssessmentService {

	@Autowired
	private AssessmentDao assesmentDao;
	
	@Override
	public List<Question> getQuestions() {
		return assesmentDao.getQuestions();
	}	
}
