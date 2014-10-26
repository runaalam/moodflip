package au.moodflip.userpage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import au.moodflip.userpage.model.Answer;
import au.moodflip.userpage.model.Assessment;
import au.moodflip.userpage.model.Question;
import au.moodflip.userpage.model.ResultDetails;

@Service
public interface AssessmentService {
	
	List<Question> getQuestions();
	
	List<Answer> getAnswers();
	
	public void save(Assessment assessment);
	
	public  List<Assessment> listAssessmentByUserId(Long userId);
	
	public Assessment getAssessmentsById(Long id);
	
	public Assessment calculateAssessmentScore(Assessment assessment);
}
