package au.moodflip.userpage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.userpage.model.Status;
import au.moodflip.userpage.model.StatusComment;
import au.moodflip.userpage.service.StatusCommentService;
import au.moodflip.userpage.service.StatusService;

@Controller
@RequestMapping(value = "/user-homepage")
public class StatusController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserHomepageController.class);
	
	private final String FOLDER = "user-homepage";
	
	@Autowired
	private StatusService statusService;
	
	@Autowired
	private StatusCommentService statusCommentService;
	
	@RequestMapping(value = "/other-post", method = RequestMethod.GET)
	public ModelAndView otherPost() {
		logger.info("Welcome to the user status system!");
		ModelAndView mav = new ModelAndView(FOLDER + "/statusList");
		List<Status> statusList = statusService.listStatus();
		mav.addObject("statusList", statusList);
		
		return mav;
	}

	@RequestMapping(value = "/other-post/statusId/{statusId}", method = RequestMethod.GET)
	public ModelAndView showSingleStatus(@PathVariable("statusId") Long statusId){
		ModelAndView mav = new ModelAndView(FOLDER + "/status");
		
		Status status = statusService.getStatusById(statusId);
		mav.addObject("status", status);
		
//		List<StatusComment> statusCommentList = statusCommentService.listStatusComment(statusId);
		List<StatusComment> statusCommentList = new ArrayList<StatusComment>(status.getStatusComments());

		mav.addObject("statusCommentList", statusCommentList);
		
		StatusComment statusComment = new StatusComment();
		mav.addObject("statusComment", statusComment);
		
		return mav;
	}
	
	@RequestMapping(value = "/other-post/statusId/{statusId}", method = RequestMethod.POST)
	public String commentPost(@PathVariable("statusId") Long statusId, Model model,
			@ModelAttribute("statusComment") StatusComment statusComment,
			BindingResult bindingResult, SessionStatus sessionStatus) {
		logger.info("Save new Comment");
		if (bindingResult.hasErrors()){
			logger.info("errors {}: {}", bindingResult.getFieldErrorCount(), bindingResult.getFieldErrors());
			return FOLDER + "/userHomepage";
		}
		model.addAttribute("statusId", statusId);
		Status status = statusService.getStatusById(statusId);
		statusComment.setStatus(status);
		status.getStatusComments().add(statusComment);
//		statusCommentService.addComment(statusComment);
		statusService.saveStatus(status);
		sessionStatus.setComplete();
		
		String redirUrl = "redirect:/user-homepage/other-post/statusId/{statusId}";
		logger.info("redirUrl:" + redirUrl);
		return redirUrl;
	}
}
