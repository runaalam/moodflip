package au.moodflip.userpage.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(value = "/user-homepage")
public class UserHomepageController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserHomepageController.class);

	private final String FOLDER = "user-homepage";

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		logger.info("Welcome to the user home page system!");
		ModelAndView mav = new ModelAndView(FOLDER + "/userHomepage");
		return mav;
	}
	
	@RequestMapping(value = "/depression-assessment", method = RequestMethod.GET)
	public ModelAndView assessmentSurvey() {
		ModelAndView mav = new ModelAndView(FOLDER + "/depressionAssessment");
		return mav;
	}
	
	@RequestMapping(value = "/assessment-result", method = RequestMethod.GET)
	public ModelAndView assessmentResult() {
		ModelAndView mav = new ModelAndView(FOLDER + "/assessmentResult");
		return mav;
	}
	
	@RequestMapping(value = "/audio-video", method = RequestMethod.GET)
	public ModelAndView audioAndVideo() {
		ModelAndView mav = new ModelAndView(FOLDER + "/audioVideo");
		return mav;
	}
	
	@RequestMapping(value = "/other-post", method = RequestMethod.GET)
	public ModelAndView otherPost() {
		ModelAndView mav = new ModelAndView(FOLDER + "/statusPost");
		return mav;
	}
	
}