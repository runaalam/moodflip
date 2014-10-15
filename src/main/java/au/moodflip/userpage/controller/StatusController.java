package au.moodflip.userpage.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.userpage.model.Status;
import au.moodflip.userpage.service.StatusService;

@Controller
@RequestMapping(value = "/user-homepage")
public class StatusController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserHomepageController.class);
	
	private final String FOLDER = "user-homepage";
	
	@Autowired
	private StatusService statusService;
	
	@RequestMapping(value = "/other-post", method = RequestMethod.GET)
	public ModelAndView otherPost() {
		logger.info("Welcome to the user status system!");
		ModelAndView mav = new ModelAndView(FOLDER + "/statusList");
		List<Status> statusList = statusService.listStatus();
		mav.addObject("statusList", statusList);
		
		return mav;
	}

}
