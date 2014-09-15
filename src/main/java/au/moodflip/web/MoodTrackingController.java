package au.moodflip.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class MoodTrackingController implements Controller{
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("Welcome to the mood tracking system!");
		
		return new ModelAndView("moodTracking");
	}


}
