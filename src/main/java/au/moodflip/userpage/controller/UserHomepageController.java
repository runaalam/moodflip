package au.moodflip.userpage.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;
import au.moodflip.userpage.model.Status;
import au.moodflip.userpage.model.Activity;
import au.moodflip.userpage.model.StatusComment;
import au.moodflip.userpage.service.ActivityService;
import au.moodflip.userpage.service.AssessmentService;
import au.moodflip.userpage.service.StatusCommentService;
import au.moodflip.userpage.service.StatusService;


@Controller
@RequestMapping(value = "/user-homepage")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserHomepageController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserHomepageController.class);

	private final String FOLDER = "user-homepage";
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private AssessmentService assessmentService;	

	@Autowired
	private StatusService statusService;
	
	@Autowired
	private StatusCommentService statusCommentService;
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(Principal principal) {
		logger.info("Welcome to the user home page system!");
		ModelAndView mav = new ModelAndView(FOLDER + "/userHomepage");
		
		User user = userManager.getUserByUsername(principal.getName());
		List<Activity> activityList = activityService.getActivityListByUserId(user.getId());
		mav.addObject("activityList", activityList);
		
		List<Status> statusList = statusService.listStatusByUserId(user.getId());
		mav.addObject("statusList", statusList);
		
		mav.addObject("myStatusNew", new Boolean(true));
		
		Status status = new Status();
		mav.addObject("status", status);
		
		StatusComment statusComment = new StatusComment();
		mav.addObject("statusComment", statusComment);
		
		Activity activity = new Activity();
		mav.addObject("userActivitynew",activity);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String saveStatusPost(@ModelAttribute("status") @Validated Status status, BindingResult bindingResult,@ModelAttribute("activity") Activity activity,
			Model model, Principal principal,  SessionStatus sessionStatus, HttpSession session) {
		logger.info("Save new status");
		if (bindingResult.hasErrors()){
			logger.info("errors {}: {}", bindingResult.getFieldErrorCount(), bindingResult.getFieldErrors());
			User user = userManager.getUserByUsername(principal.getName());
			model.addAttribute("statusList", statusService.listStatusByUserId(user.getId()));
			model.addAttribute("myStatusNew", true);
			return FOLDER + "/userHomepage";
		}
		
		User user = userManager.getUserByUsername(principal.getName());
		status.setUser(user);
		statusService.saveStatus(status);
		sessionStatus.setComplete();
		
		String activityDesc = "Wrote a new Status";
		activity.setUser(user);
		activity.setDescription(activityDesc);
		activity.setActivityDate(new Date());
		activityService.addActivity(activity);
		sessionStatus.setComplete();
		
		return "redirect:/user-homepage";
	}
	
	@RequestMapping(value = "/my-status/statusId/{statusId}", method = RequestMethod.GET)
	public ModelAndView editMyStatus(@PathVariable("statusId") Long statusId){
		ModelAndView mav = new ModelAndView(FOLDER + "/status");
		
		Status status = statusService.getStatusById(statusId);
		mav.addObject("status", status);
		
		List<StatusComment> statusCommentList = statusCommentService.listStatusComment(statusId);
		/*Set<StatusComment> set = status.getStatusComments();
		LinkedList<StatusComment> statusCommentList = new LinkedList<StatusComment>(status.getStatusComments());*/
		mav.addObject("statusCommentList", statusCommentList);
		
		LinkedList<User> commentUserList = new LinkedList<User>();
		for(StatusComment sc : statusCommentList)
			commentUserList.add(sc.getUser());
		mav.addObject("commentUserList", commentUserList);
		
		StatusComment statusComment = new StatusComment();
		mav.addObject("statusComment", statusComment);
		
		return mav;
	
	}
	
	@RequestMapping(value = "/my-status/statusId/{statusId}", method = RequestMethod.POST)
	public String commentPost(@PathVariable("statusId") Long statusId, Model model, Principal principal,
			@ModelAttribute("statusComment") @Validated StatusComment statusComment, BindingResult bindingResult, Activity activity,
			 SessionStatus sessionStatus) {
		logger.info("Save new Comment");
		if (bindingResult.hasErrors()){
			logger.info("errors {}: {}", bindingResult.getFieldErrorCount(), bindingResult.getFieldErrors());
			return FOLDER + "/userHomepage";
		}
		
		User user = userManager.getUserByUsername(principal.getName());
		model.addAttribute("statusId", statusId);
		Status status = statusService.getStatusById(statusId);
		statusComment.setStatus(status);
		statusComment.setUser(user);
		statusComment.setCommentDate(new Date());
		status.getStatusComments().add(statusComment);
//		statusCommentService.addComment(statusComment);
		statusService.saveStatus(status);
		sessionStatus.setComplete();
		
		String activityDesc = "Commented on a status";
		activity.setUser(user);
		activity.setDescription(activityDesc);
		activity.setActivityDate(new Date());
		activityService.addActivity(activity);
		sessionStatus.setComplete();
		
		String redirUrl = "redirect:/user-homepage/other-post/statusId/{statusId}";
		logger.info("redirUrl:" + redirUrl);
		return redirUrl;
	}
	
	@RequestMapping(value = "/edit/{statusId}", method = RequestMethod.GET)
	public ModelAndView showMyStatus(@PathVariable("statusId") Long statusId, Principal principal){
		ModelAndView mav = new ModelAndView(FOLDER + "/userHomepage");
		
		User user = userManager.getUserByUsername(principal.getName());
		Status status = statusService.getStatusById(statusId);
		mav.addObject("status", status);
		logger.info("*****Edit status GET*******" + status.getId() + "  " +status.getUser());
		
		List<Activity> activityList = activityService.getActivityListByUserId(user.getId());
		mav.addObject("activityList", activityList);
		
		mav.addObject("myStatusNew", new Boolean(false));
	
		List<Status> statusList = statusService.listStatusByUserId(user.getId());
		mav.addObject("statusList", statusList);
		
		return mav;
	}
	
	@RequestMapping(value = "/edit/{statusId}", method = RequestMethod.POST)
	public String editMyStatus(@PathVariable("statusId") Long statusId, @ModelAttribute("status") @Validated Status status, BindingResult bindingResult,
			Principal principal, SessionStatus sessionStatus){
		if (bindingResult.hasErrors()){
			logger.info("errors {}: {}", bindingResult.getFieldErrorCount(), bindingResult.getFieldErrors());
			return FOLDER + "/userHomepage";
		}
		
		User user = userManager.getUserByUsername(principal.getName());
		status.setId(statusId);
		status.setUser(user);
		statusService.editStatus(status);
		sessionStatus.setComplete();
		logger.info("*****Edit status POST*******" + status.getId());
		
		Activity activity = new Activity();
		String activityDesc = "Updated Status";
		activity.setUser(status.getUser());
		activity.setDescription(activityDesc);
		activity.setActivityDate(new Date());
		activityService.addActivity(activity);
		sessionStatus.setComplete();
		
		return "redirect:/user-homepage";
	}
	
	@RequestMapping(method = RequestMethod.GET, params="delete")
	public String deleteMyStatus(@RequestParam(value="delete", required=false) long statusId, 
			 Principal principal,  SessionStatus sessionStatus){
		
		statusService.removeStatus(statusId);
		sessionStatus.setComplete();
		
		User user = userManager.getUserByUsername(principal.getName());
		String activityDesc = "Deleted Status";
		Activity activity = new Activity();
		activity.setUser(user);
		activity.setDescription(activityDesc);
		activity.setActivityDate(new Date());
		activityService.addActivity(activity);
		sessionStatus.setComplete();
		return "redirect:/user-homepage";
	}
	
}