package au.moodflip.comm.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	private static final String FOLDER = "communication";
	
	private static final Long TIMEOUT_SEC = 10L;

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private UserManager userManager;
	
	private Map<DeferredResult<List<Notification>>, Long> updateUnreadRequests = new ConcurrentHashMap<DeferredResult<List<Notification>>, Long>();
	private Map<DeferredResult<List<Notification>>, Long> updateNotificationRequests = new ConcurrentHashMap<DeferredResult<List<Notification>>, Long>();

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
	public @ResponseBody DeferredResult<List<Notification>> updateNotifications(@RequestParam("id") Long id,
			Principal principal) {

		final DeferredResult<List<Notification>> result = new DeferredResult<List<Notification>>(
				TIMEOUT_SEC * 1000, Collections.emptyList());
		this.updateNotificationRequests.put(result, id);

		result.onCompletion(new Runnable() {
			public void run() {
				updateNotificationRequests.remove(result);
			}
		});

		User user = userManager.getUserByUsername(principal.getName());
		List<Notification> list = notificationService.updateNotificationByUserId(user.getId(), id);

		if (!list.isEmpty()) {
			result.setResult(list);
		}

		return result;
	}
	
	@RequestMapping(value = "/listUnread", method = RequestMethod.GET)
	public @ResponseBody List<Notification> getUnreadNotifications(
			Principal principal) {
		User user = userManager.getUserByUsername(principal.getName());
		List<Notification> unread = notificationService
				.listUnreadNotificationByUserId(user.getId());
		return unread;
	}
	
	@RequestMapping(value = "/updateUnread", method = RequestMethod.GET)
	public @ResponseBody DeferredResult<List<Notification>> updateUnreadNotifications(@RequestParam("id") Long id,
			Principal principal) {

		final DeferredResult<List<Notification>> result = new DeferredResult<List<Notification>>(
				TIMEOUT_SEC * 1000, Collections.emptyList());
		this.updateUnreadRequests.put(result, id);

		result.onCompletion(new Runnable() {
			public void run() {
				updateUnreadRequests.remove(result);
			}
		});

		User user = userManager.getUserByUsername(principal.getName());
		List<Notification> list = notificationService
				.updateUnreadNotificationByUserId(user.getId(), id);

		if (!list.isEmpty()) {
			result.setResult(list);
		}

		return result;
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public @ResponseBody Boolean upVoteComment(@RequestBody Long[] ids) {
		if (ids != null) {
			for (int i = 0; i < ids.length; i++) {
				notificationService.setNotificationRead(ids[i]);
			}
		}
		return true;
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public @ResponseBody Boolean remove(@PathVariable Long id) {
		notificationService.removeNotification(id);;
		return true;
	}

}
