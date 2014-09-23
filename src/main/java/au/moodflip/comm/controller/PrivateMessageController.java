package au.moodflip.comm.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.comm.model.PrivateMessage;
import au.moodflip.comm.service.PrivateMessageService;

@Controller
@RequestMapping(value = "/pm")
public class PrivateMessageController {

	private final String FOLDER = "communication";

	@Autowired
	private PrivateMessageService pMessageService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getNotifications(
			@RequestParam(value = "userId", required = false) Long userId) {
		ModelAndView mav = new ModelAndView(FOLDER + "/listPM");
		List<PrivateMessage> privateMessages = (userId == null ? pMessageService
				.listPrivateMessage() : pMessageService
				.listPrivateMessageByUserId(userId));
		mav.addObject("privateMessages", privateMessages);
		
		mav.getModelMap().put("privateMessage", new PrivateMessage());
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String createComment(@ModelAttribute("privateMessage") @Validated PrivateMessage pMessage, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			//logger

            return FOLDER + "/listPM";
        }

		pMessage.setCreatedAt(new Date());

		pMessageService.createPrivateMessage(pMessage);
		status.setComplete();
		return "redirect:/pm";
	}

}
