package au.moodflip.userpage.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.userpage.model.Status;
import au.moodflip.userpage.service.StatusService;




@Controller
@RequestMapping(value = "/user-homepage")
public class UserHomepageController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserHomepageController.class);

	private final String FOLDER = "user-homepage";

	@Autowired
	StatusService statusService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(Model model) {
		logger.info("Welcome to the user home page system!");
		model.addAttribute(new Status());
		ModelAndView mav = new ModelAndView(FOLDER + "/userHomepage");
		return mav;
	}
	
	@RequestMapping(value = "/user-homepage", method = RequestMethod.POST)
	public String statusPost(@ModelAttribute Status status, BindingResult bindingResult, SessionStatus sessionStatus) {
		logger.info("Save new status");
		if (bindingResult.hasErrors()){
			logger.info("errors {}: {}", bindingResult.getFieldErrorCount(), bindingResult.getFieldErrors());
			return FOLDER + "/userHomepage";
		}
		statusService.addStatus(status);
		sessionStatus.setComplete();
		return FOLDER + "/userHomepage";
	}
	
	@RequestMapping(value = "/depression-assessment", method = RequestMethod.GET)
	public ModelAndView assessment() {
		ModelAndView mav = new ModelAndView(FOLDER + "/depressionAssessment");
		return mav;
	}
	
	@RequestMapping(value = "/assessment-result", method = RequestMethod.GET)
	public ModelAndView assessmentResult() {
		ModelAndView mav = new ModelAndView(FOLDER + "/assessmentResult");
		return mav;
	}
	
	@RequestMapping(value = "/other-post", method = RequestMethod.GET)
	public ModelAndView otherPost() {
		ModelAndView mav = new ModelAndView(FOLDER + "/statusPost");
		return mav;
	}
	 
}