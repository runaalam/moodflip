package au.moodflip.userpage.controller;

import java.util.Date;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.userpage.model.Status;
import au.moodflip.userpage.model.Activity;
import au.moodflip.userpage.service.ActivityService;
import au.moodflip.userpage.service.AssessmentService;
import au.moodflip.userpage.service.StatusService;


@Controller
@RequestMapping(value = "/user-homepage")
public class UserHomepageController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserHomepageController.class);

	private final String FOLDER = "user-homepage";
	
	@Autowired
	private AssessmentService assessmentService;	

	@Autowired
	private StatusService statusService;
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home() {
		logger.info("Welcome to the user home page system!");
		ModelAndView mav = new ModelAndView(FOLDER + "/userHomepage");
		
		//change getActivityList by getActivityListByUserId
		List<Activity> activityList = activityService.getActivityList();
		mav.addObject("activityList", activityList);
		
		Status status = new Status();
		mav.addObject("status", status);
		
		Activity activity = new Activity();
		mav.addObject("userActivitynew",activity);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String statusPost(@ModelAttribute("status") Status status,@ModelAttribute("activity") Activity activity,
			BindingResult bindingResult, SessionStatus sessionStatus) {
		logger.info("Save new status");
		if (bindingResult.hasErrors()){
			logger.info("errors {}: {}", bindingResult.getFieldErrorCount(), bindingResult.getFieldErrors());
			return FOLDER + "/userHomepage";
		}
		statusService.saveStatus(status);
	//	sessionStatus.setComplete();
		
		String activityDesc = "Write Status";
		activity.setDescription(activityDesc);
		activity.setActivityDate(new Date());
		activityService.addActivity(activity);
		sessionStatus.setComplete();
		
		return "redirect:/user-homepage";
	}
	
}