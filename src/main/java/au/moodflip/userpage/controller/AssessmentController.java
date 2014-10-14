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
import au.moodflip.userpage.service.AssessmentService;
import au.moodflip.userpage.service.StatusService;

@Controller
public class AssessmentController {
		
	private final String FOLDER = "user-homepage";
	
	@Autowired
	private AssessmentService assessmentService;
	
	@Autowired
	private StatusService statusService;
	
	@RequestMapping("/depression-assessment")
	public ModelAndView showQuestionList(Map<String, Object> model) {
		
		ModelAndView mav = new ModelAndView(FOLDER + "questions");
		List<Question> quesList = assessmentService.getQuestions();
		model.put("quesList", quesList);
		return mav;
	}
}
	
	

