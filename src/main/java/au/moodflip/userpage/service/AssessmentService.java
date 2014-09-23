package au.moodflip.userpage.service;

import java.util.List;

import au.moodflip.userpage.model.Question;


public interface AssessmentService {
	
	List<Question> getQuestions();
	
	List<String> getAnswers();
}
