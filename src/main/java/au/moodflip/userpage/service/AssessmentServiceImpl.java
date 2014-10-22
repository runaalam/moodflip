package au.moodflip.userpage.service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.userpage.controller.UserHomepageController;
import au.moodflip.userpage.dao.AssessmentDao;
import au.moodflip.userpage.model.Answer;
import au.moodflip.userpage.model.Assessment;
import au.moodflip.userpage.model.Question;
import au.moodflip.userpage.model.Response;
import au.moodflip.userpage.model.ResultDetails;


@Service
@Transactional
public class AssessmentServiceImpl implements AssessmentService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserHomepageController.class);
	
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
	
	@Override
	public void save(Assessment assessment){
		assesmentDao.save(calculateAssessmentScore(assessment));
	}

	@Override
	public List<Assessment> listAssessmentByUserId(Long userId) {
		return assesmentDao.listAssessmentByUserId(userId);
	}

	@Override
	public Assessment getAssessmentsById(Long id) {
		return assesmentDao.getAssessmentsById(id);
	}

/* ---- Determine Depression Scale -----
Sadness(Dysphoria): Question numbers 2,4, 6
Loss of Interest(Anhedonia): Question numbers 8, 10
Appetite: Question numbers 1, 18
Sleep: Question numbers 5, 11, 19
Thinking / concentration: Question numbers 3, 20
Guilt(Worthlessness): Question numbers 9, 17
Tired(fatigue): Question numbers 7, 16
Movement(Agitation): Question numbers 12, 13
Suicidal ideation: Question numbers 14, 15
---------------------------------------------- */
	
	@Override
	public Assessment calculateAssessmentScore(Assessment assessment) {
		 
		ResultDetails resultDetails = new ResultDetails();
		int score = 0;
		int[] dsmSymptom = new int[9];
		int mCriteria = 0;
		int pCriteria = 0;
		
		for(Response r : assessment.getResponseList()) {
			
			if(r.getAnswer().getValue()>3)
				score = score + (r.getAnswer().getValue() - 1);
			else score = score + r.getAnswer().getValue();
			
			if(r.getQuestion().getId() == 2 || r.getQuestion().getId() == 4 || r.getQuestion().getId() == 6){
				if(r.getAnswer().getValue() > 3)
					dsmSymptom[0] = r.getAnswer().getValue();
			} else if(r.getQuestion().getId() == 8 || r.getQuestion().getId() == 10) {
				if(r.getAnswer().getValue() > 3)
					dsmSymptom[1] = r.getAnswer().getValue();
			} else if(r.getQuestion().getId() == 1  || r.getQuestion().getId() == 18) {
				if(r.getAnswer().getValue() > 2 && r.getAnswer().getValue() > dsmSymptom[2])
					dsmSymptom[2] = r.getAnswer().getValue();
			} else if(r.getQuestion().getId() == 5 || r.getQuestion().getId() == 11 || r.getQuestion().getId() == 19) {
				if(r.getAnswer().getValue() > 2 && r.getAnswer().getValue() > dsmSymptom[3])
					dsmSymptom[3] = r.getAnswer().getValue();
			} else if(r.getQuestion().getId() == 3 || r.getQuestion().getId() == 20) {
				if(r.getAnswer().getValue() > 2 && r.getAnswer().getValue() > dsmSymptom[4])
					dsmSymptom[4] = r.getAnswer().getValue();
			} else if(r.getQuestion().getId() == 9 || r.getQuestion().getId() == 17) {
				if(r.getAnswer().getValue() > 2 && r.getAnswer().getValue() > dsmSymptom[5])
					dsmSymptom[5] = r.getAnswer().getValue();
			} else if(r.getQuestion().getId() == 7  || r.getQuestion().getId() == 16) {
				if(r.getAnswer().getValue() > 2 && r.getAnswer().getValue() > dsmSymptom[6])
					dsmSymptom[6] = r.getAnswer().getValue();
			} else if(r.getQuestion().getId() == 12 ||r.getQuestion().getId() == 13) {
				if(r.getAnswer().getValue() > 2 && r.getAnswer().getValue() > dsmSymptom[7])
					dsmSymptom[7] = r.getAnswer().getValue();
			} else if(r.getQuestion().getId() == 14 || r.getQuestion().getId() == 15) {
				if(r.getAnswer().getValue() > 2 && r.getAnswer().getValue() > dsmSymptom[8])
					dsmSymptom[8] = r.getAnswer().getValue();
			} 
			
			if (r.getQuestion().getId() == 6)
				resultDetails.setDysphoria(r.getAnswer().getValue());
			else if (r.getQuestion().getId() == 10)
				resultDetails.setAnhedonia(r.getAnswer().getValue());
			else if(r.getQuestion().getId() == 18) 
				resultDetails.setAppetite(r.getAnswer().getValue());
			else if(r.getQuestion().getId() == 19) 
				resultDetails.setSleep(r.getAnswer().getValue());
			else if(r.getQuestion().getId() == 20) 
				resultDetails.setConcentration(r.getAnswer().getValue());
			else if(r.getQuestion().getId() == 17) 
				resultDetails.setGuilt(r.getAnswer().getValue());
			else if(r.getQuestion().getId() == 16) 
				resultDetails.setFatigue(r.getAnswer().getValue());
			else if(r.getQuestion().getId() == 13) 
				resultDetails.setAgitation(r.getAnswer().getValue());
			else if(r.getQuestion().getId() == 15) 
				resultDetails.setSuicidalIdeation(r.getAnswer().getValue());
		}
		
		assessment.setScore(score);
		
		for (int i = 2; i < 9; i++){
			if(dsmSymptom[i] == 3)
				pCriteria ++;
			if(dsmSymptom[i] == 4)
				mCriteria ++;
		}
		
		if(dsmSymptom[0] == 4 || dsmSymptom[1] == 4) {
			if(mCriteria >= 4 )
				assessment.setDepressionScale(4);
			else if(mCriteria == 3 || pCriteria >= 3)
				assessment.setDepressionScale(3);
			else if(mCriteria == 2 || pCriteria == 2)
				assessment.setDepressionScale(2);
			else if(assessment.getScore() >= 16)
					assessment.setDepressionScale(1);
			else assessment.setDepressionScale(0);
			
		} else if(assessment.getScore() >= 16)
				assessment.setDepressionScale(1);
		else assessment.setDepressionScale(0);
		
		assessment.setResultDetails(resultDetails);
		return assessment;
	}
}
