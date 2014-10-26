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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.comm.model.PrivateMessage;
import au.moodflip.comm.service.NotificationService;
import au.moodflip.comm.service.PrivateMessageService;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;

@Controller
@RequestMapping(value = "/pm")
public class PrivateMessageController {

	private static final String FOLDER = "communication";
	
	private static final Long TIMEOUT_SEC = 10L;

	@Autowired
	private PrivateMessageService pMessageService;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private NotificationService notificationService;
	
	private Map<DeferredResult<List<PrivateMessage>>, Long> messageRequests = new ConcurrentHashMap<DeferredResult<List<PrivateMessage>>, Long>();
	
	@RequestMapping(value = "/{receiverId}", method = RequestMethod.GET)
	public ModelAndView pageMessages(
			@PathVariable("receiverId") Long receiverId,
			Principal principal) {
		ModelAndView mav = new ModelAndView(FOLDER + "/listPM");
//		List<User> users = userManager.getUsers();
//		mav.addObject("users", users);
//		mav.addObject("currentUser", principal.getName());
		mav.addObject("receiver", userManager.getUserById(receiverId));
		return mav;
	}
	
	@RequestMapping(value = "/{receiverId}/list", method = RequestMethod.GET)
	public @ResponseBody List<PrivateMessage> getMessages(
			@PathVariable("receiverId") Long receiverId, Principal principal) {
		User sender = userManager.getUserByUsername(principal.getName());
		User receiver = userManager.getUserById(receiverId);
		List<PrivateMessage> privateMessages = pMessageService
				.listPrivateMessageBySenderAndReceiverId(sender, receiver);
		return privateMessages;
	}
	
	@RequestMapping(value = "/{receiverId}/update", method = RequestMethod.GET)
	public @ResponseBody DeferredResult<List<PrivateMessage>> updateMessages(
			@PathVariable("receiverId") Long receiverId,
			@RequestParam("datetime") Long datetime, Principal principal) {

		final DeferredResult<List<PrivateMessage>> result = new DeferredResult<List<PrivateMessage>>(
				TIMEOUT_SEC * 1000, Collections.emptyList());
		this.messageRequests.put(result, datetime);

		result.onCompletion(new Runnable() {
			public void run() {
				messageRequests.remove(result);
			}
		});

		User sender = userManager.getUserByUsername(principal.getName());
		User receiver = userManager.getUserById(receiverId);
		List<PrivateMessage> list = pMessageService
				.updatePrivateMessageBySenderAndReceiverId(sender, receiver,
						new Date(datetime));

		if (!list.isEmpty()) {
			result.setResult(list);
		}

		return result;
	}
	
	@RequestMapping(value = "/{receiverId}", method = RequestMethod.POST)
	public @ResponseBody PrivateMessage postMessage(
			@PathVariable("receiverId") Long receiverId, @RequestBody String message, Principal principal) {
		User sender = userManager.getUserByUsername(principal.getName());
		User receiver = userManager.getUserById(receiverId);
		
		notificationService.createNotification(sender.getUsername() + " messaged you!", "pm/" + sender.getId(), receiverId);
		
		return pMessageService.createPrivateMessage(message, sender, receiver);
	}

}