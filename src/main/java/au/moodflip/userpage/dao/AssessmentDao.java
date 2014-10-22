package au.moodflip.userpage.dao;
//
import java.util.List;

import au.moodflip.userpage.model.Assessment;
import au.moodflip.userpage.model.Question;

public interface AssessmentDao {
	List<Question> getQuestions();
	public static final String[] answers = {"Not at all or less than 1 day last week", 
											"One or two days last week", 
											"Three to four days last week", 
											"Five to seven days last week", 
											"Nearly every day for two weeks"};
	
	public void save(Assessment assessment);
}
