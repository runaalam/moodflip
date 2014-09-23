package au.moodflip.personalisation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;

@Controller
@RequestMapping(value = "/personalisation")
public class PersonalisationController {
	private static final Logger logger = LoggerFactory
			.getLogger(PersonalisationController.class);


	@Autowired
	private UserManager userManager;
	
	private final String FOLDER = "personalisation";

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		logger.info("Welcome to the personalisation system!");
		ModelAndView mav = new ModelAndView(FOLDER + "/personalisation");
		
		
		String now = (new java.util.Date()).toString();
		logger.info("Returning hello view with "+ now);
		
		Map<String,Object> myModel = new HashMap<String, Object>();
		myModel.put("now", now);
		List<User> user = userManager.getUsers();
		List<String> status = new ArrayList<String>();
		status.add("Ban");
		status.add("UnBan");
		status.add("Delete");
		
		mav.getModelMap().put("users", user);
		mav.getModelMap().put("status", status);
		
		return mav;
	}

}