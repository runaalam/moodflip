package au.moodflip.comm.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.comm.model.Notification;
import au.moodflip.comm.service.NotificationService;

@Controller
@RequestMapping(value = "/notification")
public class NotificationController {

	private final String FOLDER = "communication";

	@Autowired
	private NotificationService notificationService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getNotifications(
			@RequestParam(value = "userId", required = false) Long userId) {
		ModelAndView mav = new ModelAndView(FOLDER + "/listNotification");
		List<Notification> notifications = (userId == null ? notificationService
				.listNotification() : notificationService
				.listNotificationByUserId(userId));
		mav.addObject("notifications", notifications);
//		for(Notification n : notifications)
//			System.out.println(n.getUrl());
		return mav;
	}
	
	
//	@RequestMapping(method = RequestMethod.GET)
//	public @ResponseBody Notification getNotifications(
//			@RequestParam("userId") Long userId) {
//		List<Notification> notifications = notificationService.listNotificationByUserId(userId);
//		return notifications;
//	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(@RequestParam("message") String message,
			@RequestParam("url") String url,
			@RequestParam("userId") Long userId) {
		// validator.validate(contact, result);
		// if (result.hasErrors()) {
		// return "newContact";
		// }
		Notification notification = new Notification();
		notification.setMessage(message);
		notification.setUrl(url);
		notification.setUserId(userId);
		notification.setCreatedAt(new Date());
		notificationService.createNotification(notification);
		return "redirect:/notification";
	}
	
//	@RequestMapping(value = "/create", method = RequestMethod.POST)
//	public @ResponseBody Notification createNotification(@RequestBody Notification notification) {
//		notification.setCreatedAt(new Date());
//		return notification;
//	}

}
