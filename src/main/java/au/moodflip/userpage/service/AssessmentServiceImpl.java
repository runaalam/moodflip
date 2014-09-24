package au.moodflip.userpage.service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import au.moodflip.userpage.dao.AssessmentDao;
import au.moodflip.userpage.model.Answer;
import au.moodflip.userpage.model.Question;


@Service
public class AssessmentServiceImpl implements AssessmentService {

	@Autowired
	private AssessmentDao assesmentDao;
	
	@Override
	public List<Question> getQuestions() {
		return assesmentDao.getQuestions();
	}

	@Override
	public List<Answer> getAnswers() {
		List<String> texts = Arrays.asList(AssessmentDao.answers);
		
		List<Answer> list = new LinkedList<Answer>();
		int i = 0;
		for (String str: texts) {
			Answer ans = new Answer();
			ans.setId(i);
			i++;
			ans.setText(str);
			list.add(ans);
		}
		return list;
	}	
}
