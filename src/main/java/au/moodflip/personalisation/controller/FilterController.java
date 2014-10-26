package au.moodflip.personalisation.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.comm.model.TopicComment;
import au.moodflip.comm.service.TopicCommentService;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.SearchFilterImp;
import au.moodflip.personalisation.service.UserManager;

@Controller
@RequestMapping(value = "/filter")
public class FilterController {
	private static final Logger logger = LoggerFactory
			.getLogger(PersonalisationController.class);


	@Autowired
	private SearchFilterImp searchFilter;
	@Autowired
	private TopicCommentService topicCommentService;
	
	@Autowired
	private UserManager userService;
	
	
	private final String FOLDER = "filter";

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		logger.info("Welcome to the personalisation system!");
		ModelAndView mav = new ModelAndView(FOLDER + "/contentfilter");
		
		
		String now = (new java.util.Date()).toString();
		logger.info("Returning hello view with "+ now);
		
		Map<String,Object> myModel = new HashMap<String, Object>();
		myModel.put("now", now);
		List<TopicComment> comment = searchFilter.getFilteredComments();
		mav.getModelMap().put("comment", comment);
		
		return mav;
	}
	
	


}