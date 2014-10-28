package au.moodflip.userpage.model;

public class LevelDetails {
	
	/*MEET_CRITERIA(4, "Meets criteria for Major depressive episode", "Your answers suggest that depression is an important problem for you. We recommend that you make an appointment with a health care professional to discuss how you are feeling as soon as you can. Depression affects many people. There are good treatments for it."),
	PROBABLE_MAJOR_EPISODE(3, "Probable major depressive episode", "Your answers suggest that depression may be a problem for you. The next time you visit a health care professional, we recommend that you talk with him or her about how you have been feeling."),
	POSSIBLE_MAJOR_EPISODE(2, "Possible major depressive episode", "Your answers suggest that depression might be a problem for you. The next time you visit a health care professional, we recommend that you talk with him or her about how you have been feeling."),
	SUBTHERSHHOLD_DEPRESSION(1, "Subthershhold depression episode", "Your answers suggest that depression might be a problem for you. The next time you visit a health care professional, we recommend that you talk with him or her about how you have been feeling."),
	NO_CLINICAL_SIGNIFICANCE(0, "No clinical significance", "Your answers suggest that depression is not a problem for you right now. Talk to your health care professional if you ever have symptoms like those listed above for more than a few days.");
	*/
	
	private  int level;
	private String diagnosis;
	private String assessmentFeedback;
	
	public LevelDetails(int level) {
		
		this.level = level;
		
		if(this.level == 0){
			setDiagnosis("No clinical significance");
			setAssessmentFeedback("Your answers suggest that depression is not a problem for you right now. "
									+ "Talk to your health care professional if you ever have symptoms like "
									+ "those listed above for more than a few days.");
		}else if(this.level == 1){
			setDiagnosis("Subthershhold depression episode");
			setAssessmentFeedback("Your answers suggest that depression might be a problem for you. " 
								+"The next time you visit a health care professional, we recommend "
								+"that you talk with him or her about how you have been feeling.");
		}else if(this.level == 2){
			setDiagnosis("Possible major depressive episode");
			setAssessmentFeedback("Your answers suggest that depression might be a problem for you. " 
					+"The next time you visit a health care professional, "
					+"we recommend that you talk with him or her about how you have been feeling.");
		}else if(this.level == 3){
			setDiagnosis("Probable major depressive episode");
			setAssessmentFeedback("Your answers suggest that depression may be a problem for you. "
									+"The next time you visit a health care professional, "
									+"we recommend that you talk with him or her about how you have been feeling.");
		}else if(this.level == 4){
			setDiagnosis("Meets criteria for Major depressive episode");
			setAssessmentFeedback("Your answers suggest that depression is an important problem for you. "
								+"We recommend that you make an appointment with a health care professional to "
								+"discuss how you are feeling as soon as you can. Depression affects many people. "
								+"There are good treatments for it.");
			
		}
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getAssessmentFeedback() {
		return assessmentFeedback;
	}

	public void setAssessmentFeedback(String assessmentFeedback) {
		this.assessmentFeedback = assessmentFeedback;
	}
	
}
