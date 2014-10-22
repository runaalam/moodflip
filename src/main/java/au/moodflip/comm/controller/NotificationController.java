package au.moodflip.comm.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.comm.model.Notification;
import au.moodflip.comm.service.NotificationService;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;
//
@Controller
@RequestMapping(value = "/notification")
public class NotificationController {

	private final String FOLDER = "communication";

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private UserManager userManager;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pageNotifications() {
		ModelAndView mav = new ModelAndView(FOLDER + "/listNotification");
		return mav;
	}
	
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public @ResponseBody List<Notification> getNotifications(Principal principal) {
		User user = userManager.getUserByUsername(principal.getName());
		List<Notification> notifications = notificationService.listNotificationByUserId(user.getId());
		return notifications;
	}

//	@RequestMapping(value = "/create", method = RequestMethod.GET)
//	public String create(@RequestParam("message") String message,
//			@RequestParam("url") String url,
//			@RequestParam("userId") Long userId) {
//		Notification notification = new Notification();
//		notification.setMessage(message);
//		notification.setUrl(url);
//		notification.setUserId(userId);
//		notification.setCreatedAt(new Date());
//		notificationService.createNotification(notification);
//		return "redirect:/notification";
//	}
	
//	@RequestMapping(value = "/create", method = RequestMethod.POST)
//	public @ResponseBody Notification createNotification(@RequestBody Notification notification) {
//		notification.setCreatedAt(new Date());
//		return notification;
//	}

}
