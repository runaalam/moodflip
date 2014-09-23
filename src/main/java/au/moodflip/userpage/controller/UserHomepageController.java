package au.moodflip.userpage.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AutoPopulatingList;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.comm.model.Forum;
import au.moodflip.userpage.model.Answer;
import au.moodflip.userpage.model.Assessment;
import au.moodflip.userpage.model.Question;
import au.moodflip.userpage.model.Response;
import au.moodflip.userpage.model.Status;
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
	StatusService statusService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home(Locale locale) {
            logger.info("Welcome home!, " + "Locale: " + locale);
            ModelAndView mav = new ModelAndView("userHomepage");
            return mav;
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(Model model) {
		logger.info("Welcome to the user home page system!");
		model.addAttribute(new Status());
		List<Status> statusList = statusService.listStatus();
		model.addAttribute("statusList", statusList);
		ModelAndView mav = new ModelAndView(FOLDER + "/userHomepage");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String statusPost(@ModelAttribute Status status, BindingResult bindingResult, SessionStatus sessionStatus) {
		logger.info("Save new status");
		if (bindingResult.hasErrors()){
			logger.info("errors {}: {}", bindingResult.getFieldErrorCount(), bindingResult.getFieldErrors());
			return FOLDER + "/userHomepage";
		}
		statusService.saveStatus(status);
		sessionStatus.setComplete();
		return "redirect:/user-homepage";
	}
	
	@RequestMapping(value = "/depression-assessment", method = RequestMethod.GET)
	public ModelAndView assessmentSurvey() {


		ModelAndView mav = new ModelAndView(FOLDER + "/questions");
		List<Question> quesList = assessmentService.getQuestions();
		mav.addObject("quesList", quesList);
		List<Answer> answerList = assessmentService.getAnswers();
		mav.addObject("ansList", answerList);

		Assessment assessment = new Assessment();
		mav.addObject("assessment", assessment);
		return mav;
	}

	@RequestMapping(value = "/depression-assessment", method = RequestMethod.POST)
	public String create(@ModelAttribute("assessment") Assessment assessment,
			BindingResult result, SessionStatus status) {
		if (result.hasErrors()) {
			//logger
			
            return FOLDER + "/questions";
        }
//		forumService.addForum(forum);
		status.setComplete();
		
		String testStatus = "I have just completed my assesment test with individual score of (" 
							+ assessment.getResponseList().get(0).getAnswerId() + "+"
							+ assessment.getResponseList().get(0).getAnswerId() + "+"
							+ assessment.getResponseList().get(0).getAnswerId() + "+"
							+ assessment.getResponseList().get(0).getAnswerId() + "+"
							+ assessment.getResponseList().get(0).getAnswerId() + ")";
		Status newStatus = new Status();
		newStatus.setContent(testStatus);
		newStatus.setSubmitDate(new Date());
		statusService.saveStatus(newStatus);
		return "redirect:/user-homepage";
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