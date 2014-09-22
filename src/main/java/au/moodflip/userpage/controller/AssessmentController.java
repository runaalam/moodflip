package au.moodflip.userpage.controller;


import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import au.moodflip.userpage.model.Question;

@Controller
//@SessionAttributes("assessment")
public class AssessmentController {
	

	/*@RequestMapping("/depression-assessment")
	public String showQuestionList(Map<String, Object> model) {
		ArrayList<Question> quesList = new ArrayList<Question>();
		
		Question qus = new Question();
		qus.setId(1);
		qus.setText("￼My appetite was poor.");
		quesList.add(qus);
		
		qus = new Question();
		qus.setId(2);
		qus.setText("I could not shake off the blues.");
		quesList.add(qus);
		
		model.put("quesList", quesList);
		return "depression-assessment/quesList";
	}*/
	
}
	
	

