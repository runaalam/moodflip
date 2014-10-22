package au.moodflip.comm.controller;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.comm.model.PrivateMessage;
import au.moodflip.comm.service.NotificationService;
import au.moodflip.comm.service.PrivateMessageService;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;
//
@Controller
@RequestMapping(value = "/pm")
public class PrivateMessageController {

	private final String FOLDER = "communication";

	@Autowired
	private PrivateMessageService pMessageService;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private NotificationService notificationService;
	
	 private Map<DeferredResult<List<PrivateMessage>>, Long> messageRequests = new ConcurrentHashMap<DeferredResult<List<PrivateMessage>>, Long>();

//	@RequestMapping(method = RequestMethod.GET)
//	public ModelAndView pageMessages(Principal principal) {
//		ModelAndView mav = new ModelAndView(FOLDER + "/listPM");
//		User user = userManager.getUserByUsername(principal.getName());
//		List<PrivateMessage> privateMessages = pMessageService.listPrivateMessageByUserId(user.getId());
//		mav.addObject("privateMessages", privateMessages);
//		mav.addObject("user", user);
//
//		mav.getModelMap().put("privateMessage", new PrivateMessage());
//
//		return mav;
//	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pageMessages(
			@RequestParam(value = "userId", required = false) Long receiverId,
			Principal principal) {
		ModelAndView mav = new ModelAndView(FOLDER + "/listPM");
		List<User> users = userManager.getUsers();
		mav.addObject("users", users);
		mav.addObject("currentUser", principal.getName());
		mav.addObject("receiverId", receiverId);
		return mav;
	}
	
	@RequestMapping(value = "/{receiverId}", method = RequestMethod.GET)
	public @ResponseBody List<PrivateMessage> getMessages(
			@PathVariable("receiverId") Long receiverId, Principal principal) {
		User user = userManager.getUserByUsername(principal.getName());
		List<PrivateMessage> privateMessages = pMessageService
				.listPrivateMessageBySenderAndReceiverId(user.getId(),
						receiverId);
		return privateMessages;
	}
	
	@RequestMapping(value = "/{receiverId}/update", method = RequestMethod.GET)
	public @ResponseBody DeferredResult<List<PrivateMessage>> updateMessages(
			@PathVariable("receiverId") Long receiverId, @RequestParam("lastId") Long msgId, Principal principal) {
		
		  final DeferredResult<List<PrivateMessage>> result = new DeferredResult<List<PrivateMessage>>(null, Collections.emptyList());
		  this.messageRequests.put(result, msgId);

		  result.onCompletion(new Runnable() {
		    public void run() {
		    	messageRequests.remove(result);
		    }
		  });

		  User user = userManager.getUserByUsername(principal.getName());
			List<PrivateMessage> list = pMessageService
					.updatePrivateMessageBySenderAndReceiverId(user.getId(),
							receiverId, msgId);
			
		  if (!list.isEmpty()) {
		    result.setResult(list);
		  }

		  return result;
	}
	
	@RequestMapping(value = "/{receiverId}", method = RequestMethod.POST)
	public @ResponseBody PrivateMessage postMessage(
			@PathVariable("receiverId") Long receiverId, @RequestBody String message, Principal principal) {
		User user = userManager.getUserByUsername(principal.getName());
		
		notificationService.createNotification(user.getUsername() + " messaged you!", "pm?userId=" + user.getId(), receiverId);
		
		return pMessageService.createPrivateMessage(message, user.getId(), receiverId);
	}

//	@RequestMapping(method = RequestMethod.POST)
//	public String createMessage(
//			@ModelAttribute("privateMessage") @Validated PrivateMessage pMessage,
//			BindingResult result, SessionStatus status) {
//		if (result.hasErrors()) {
//			// logger
//
//			return FOLDER + "/listPM";
//		}
//
//		pMessage.setCreatedAt(new Date());
//
//		pMessageService.createPrivateMessage(pMessage);
//		status.setComplete();
//		return "redirect:/pm";
//	}

}
