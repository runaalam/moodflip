package au.moodflip.userpage.dao;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import au.moodflip.userpage.model.Question;

@Repository
public class AssessmentDaoImpl implements AssessmentDao {

	@Override
	public List<Question> getQuestions() {
		
		List<Question> quesList = new LinkedList<Question>();
		
		Question qus = new Question();
		qus.setId(1);
		qus.setText("My appetite was poor.");
		quesList.add(qus);
		
		qus = new Question();
		qus.setId(2);
		qus.setText("I could not shake off the blues.");
		quesList.add(qus);
		return quesList;
	}



}
