package au.moodflip.userpage.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.userpage.model.Question;
import au.moodflip.userpage.service.AssessmentService;

@Controller
public class AssessmentController {
		
	private final String FOLDER = "user-homepage";
	
	@Autowired
	private AssessmentService assessmentService;
	
	@RequestMapping("/depression-assessment")
	public ModelAndView showQuestionList(Map<String, Object> model) {

		List<Question> quesList = assessmentService.getQuestions();
		model.put("quesList", quesList);
		ModelAndView mav = new ModelAndView(FOLDER + "questions");
		return mav;
	}
	
}
	
	

