package au.moodflip.comm.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.comm.model.Notification;
import au.moodflip.comm.service.NotificationService;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;

@Controller
@RequestMapping(value = "/notification")
public class NotificationController {

	private final String FOLDER = "communication";
	
	private static final Long TIMEOUT_SEC = 2*60L;

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private UserManager userManager;
	
	private Map<DeferredResult<List<Notification>>, Date> notificationRequests = new ConcurrentHashMap<DeferredResult<List<Notification>>, Date>();

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
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public @ResponseBody DeferredResult<List<Notification>> getNewNotifications(
			Principal principal) {

		final DeferredResult<List<Notification>> result = new DeferredResult<List<Notification>>(
				TIMEOUT_SEC * 1000, Collections.emptyList());
		this.notificationRequests.put(result, new Date());

		result.onCompletion(new Runnable() {
			public void run() {
				notificationRequests.remove(result);
			}
		});

		User user = userManager.getUserByUsername(principal.getName());
		List<Notification> list = notificationService
				.listNewNotificationByUserId(user.getId());

		if (!list.isEmpty()) {
			result.setResult(list);
		}

		return result;
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public @ResponseBody Boolean upVoteComment(@RequestBody Long[] ids) {
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				notificationService.setNotificationRead(ids[i]);
			}
		}
		return true;
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
