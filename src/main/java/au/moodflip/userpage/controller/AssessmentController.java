package au.moodflip.userpage.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;
import au.moodflip.userpage.model.Answer;
import au.moodflip.userpage.model.Assessment;
import au.moodflip.userpage.model.ChartData;
import au.moodflip.userpage.model.LevelDetails;
import au.moodflip.userpage.model.Question;
import au.moodflip.userpage.model.Activity;
import au.moodflip.userpage.service.ActivityService;
import au.moodflip.userpage.service.AssessmentService;
import au.moodflip.userpage.service.StatusService;
import au.moodflip.userpage.utils.UserPageUtils;

@Controller
@RequestMapping(value = "/user-homepage")
@PreAuthorize("hasRole('ROLE_USER')")
public class AssessmentController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserHomepageController.class);
		
	private final String FOLDER = "user-homepage";
	
	@Autowired
	private AssessmentService assessmentService;
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private UserManager userManager;	
	
	@RequestMapping(value = "/depression-assessment", method = RequestMethod.GET)
	public ModelAndView depressionAssessment(Principal principal) {

		ModelAndView mav = new ModelAndView(FOLDER + "/questions");
		List<Question> quesList = assessmentService.getQuestions();
		mav.addObject("quesList", quesList);
		List<Answer> answerList = assessmentService.getAnswers();
		mav.addObject("ansList", answerList);

		Assessment assessment = new Assessment();
		User user = userManager.getUserByUsername(principal.getName());
		assessment.setUser(user);
		mav.addObject("assessment", assessment);
		
		Activity activity = new Activity();
		mav.addObject("userActivitynew",activity);
		
		return mav;
	}

	@RequestMapping(value = "/depression-assessment", method = RequestMethod.POST)
	public String submitAssessment(@ModelAttribute("assessment") Assessment assessment, 
			@ModelAttribute("activity") Activity activity,
			BindingResult result, SessionStatus sessionStatus, Principal principal) {
		if (result.hasErrors()) {
			//logger
			
            return FOLDER + "/questions";
        }
		
		User user = userManager.getUserByUsername(principal.getName());
		assessment.setDate(new Date());
		assessment.setUser(user);
		assessmentService.save(assessment);
		
		sessionStatus.setComplete();
		
		String activityDesc = "Completed assesment test";
		activity.setUser(user);
		activity.setDescription(activityDesc);
		activity.setActivityDate(new Date());
		activityService.addActivity(activity);
		sessionStatus.setComplete();
		
		return "redirect:/user-homepage";
	}	
	
	@RequestMapping(value = "/assessment-result", method = RequestMethod.GET)
	public ModelAndView assessmentResult(Principal principal) {
		ModelAndView mav = new ModelAndView(FOLDER + "/assessmentResult");
		
		User user = userManager.getUserByUsername(principal.getName());
		List<Assessment> assList = assessmentService.listAssessmentByUserId(user.getId());
		//mav.addObject("assList", assList);
		
		if(assList.size() > 0){
			Assessment assessment = assList.get(assList.size() - 1);
			mav.addObject("assessment", assessment);
			
			LevelDetails levelDetails = new LevelDetails(assessment.getDepressionScale());
			mav.addObject("levelDetails", levelDetails);
		}
//		logger.info("\n ***** Assessment result page ****" + assList.size()); 
		return mav;
	}
	
	@RequestMapping(value = "/drawLineChart", method = RequestMethod.GET )
	@ResponseBody
	public ChartData drawLineChart( ModelAndView model, Principal principal) {

		User user = userManager.getUserByUsername(principal.getName());
		List<Assessment> assList = assessmentService.listAssessmentByUserId(user.getId());
		
		return UserPageUtils.prepareChartData(assList);
//		return UserPageUtils.prepareDummyChartData(); 
	}	
}
	
	

