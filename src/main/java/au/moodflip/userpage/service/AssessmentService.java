package au.moodflip.userpage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import au.moodflip.userpage.model.Answer;
import au.moodflip.userpage.model.Assessment;
import au.moodflip.userpage.model.Question;

@Service
public interface AssessmentService {
	
	List<Question> getQuestions();
	
	List<Answer> getAnswers();
	
	public void addAssessment(Assessment assessment);
}
