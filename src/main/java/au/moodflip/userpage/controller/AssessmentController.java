package au.moodflip.userpage.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.userpage.model.Answer;
import au.moodflip.userpage.model.Assessment;
import au.moodflip.userpage.model.Question;
import au.moodflip.userpage.model.Status;
import au.moodflip.userpage.model.Activity;
import au.moodflip.userpage.service.ActivityService;
import au.moodflip.userpage.service.AssessmentService;
import au.moodflip.userpage.service.StatusService;

@Controller
@RequestMapping(value = "/user-homepage")
public class AssessmentController {
		
	private final String FOLDER = "user-homepage";
	
	@Autowired
	private AssessmentService assessmentService;
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(value = "/depression-assessment", method = RequestMethod.GET)
	public ModelAndView depressionAssessment() {

		ModelAndView mav = new ModelAndView(FOLDER + "/questions");
		List<Question> quesList = assessmentService.getQuestions();
		mav.addObject("quesList", quesList);
		List<Answer> answerList = assessmentService.getAnswers();
		mav.addObject("ansList", answerList);

		Assessment assessment = new Assessment();
		mav.addObject("assessment", assessment);
		
		Activity activity = new Activity();
		mav.addObject("userActivitynew",activity);
		
		return mav;
	}

	@RequestMapping(value = "/depression-assessment", method = RequestMethod.POST)
	public String submitAssessment(@ModelAttribute("assessment") Assessment assessment, @ModelAttribute("activity") Activity activity,
			BindingResult result, SessionStatus sessionStatus) {
		if (result.hasErrors()) {
			//logger
			
            return FOLDER + "/questions";
        }

		//sessionStatus.setComplete();
		
		/*String testStatus = "Completed assesment test with individual score of (" 
							+ assessment.getResponseList().get(0).getAnswerId() + "+"
							+ assessment.getResponseList().get(1).getAnswerId() + "+"
							+ assessment.getResponseList().get(2).getAnswerId() + "+"
							+ assessment.getResponseList().get(3).getAnswerId() + "+"
							+ assessment.getResponseList().get(4).getAnswerId() + ")";
		Status newStatus = new Status();
		newStatus.setContent(testStatus);
		newStatus.setSubmitDate(new Date());
		statusService.saveStatus(newStatus);*/
		
		String activityDesc = "Completed assesment test";
		activity.setDescription(activityDesc);
		activity.setActivityDate(new Date());
		activityService.addActivity(activity);
		sessionStatus.setComplete();
		
		return "redirect:/user-homepage";
	}	
	
	@RequestMapping(value = "/assessment-result", method = RequestMethod.GET)
	public ModelAndView assessmentResult() {
		ModelAndView mav = new ModelAndView(FOLDER + "/assessmentResult");
		return mav;
	} 
}
	
	

