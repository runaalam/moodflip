package au.moodflip.userpage.dao;

import java.util.List;

import au.moodflip.userpage.model.Question;

public interface AssessmentDao {
	List<Question> getQuestions();
	public static final String[] answers = {"Always", "Sometimes", "Rarely", "Never", "N/A"};
}
