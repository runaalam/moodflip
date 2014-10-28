package au.moodflip.personalisation.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.model.Friend;
import au.moodflip.personalisation.service.FriendManager;
import au.moodflip.personalisation.service.UserManager;

@Controller
@SessionAttributes(value = {"user"})
@RequestMapping(value = "/friend/**")
public class FriendController {
	private static final Logger logger = LoggerFactory
			.getLogger(PersonalisationController.class);


	@Autowired
	private UserManager userManager;
	@Autowired
	private FriendManager friendManager;
	
	private final String FOLDER = "personalisation";

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		logger.info("Welcome to the personalisation system!");
		ModelAndView mav = new ModelAndView(FOLDER + "/friend");
		
		
		String now = (new java.util.Date()).toString();
		logger.info("Returning hello view with "+ now);
		
		Map<String,Object> myModel = new HashMap<String, Object>();
		myModel.put("now", now);
		List<User> user = userManager.getUsers();
		List<String> status = new ArrayList<String>();
		status.add("Ban");
		status.add("UnBan");
		status.add("Delete");
		
		mav.getModelMap().put("users", user);
		mav.getModelMap().put("status", status);
		
		return mav;
	}
	
@RequestMapping(value = "/requestbyid/{id}",method = RequestMethod.POST)
	public ModelAndView request(@PathVariable("id") Long id,Principal principal) {
		ModelAndView mav = new ModelAndView("redirect:/user/profile");
		User receiver = userManager.getUserById(id);
		User owner = userManager.getUserByUsername(principal.getName());
		Friend friend = new Friend();
		friend.setSender(owner);
		friend.setReceiver(receiver);
		friend.setFriends(false);
		friendManager.addFriendRequest(friend);
		
		return mav;
	}
@RequestMapping(value = "/request",method = RequestMethod.POST)
public ModelAndView usernameRequest(Principal principal,HttpServletRequest request) {
	ModelAndView mav = new ModelAndView("redirect:/user/profile");
	User receiver = userManager.getUserByUsername("user");
	User owner = userManager.getUserByUsername(principal.getName());
	Friend friend = new Friend();
	friend.setSender(owner);
	friend.setReceiver(receiver);
	friend.setFriends(false);
	friendManager.addFriendRequest(friend);
	return mav;
}


@RequestMapping(value = "/accept/{id}",method = RequestMethod.GET)
public ModelAndView accept(@PathVariable("id") Long id,Principal principal) {
	User sender = userManager.getUserById(id);
	User owner = userManager.getUserByUsername(principal.getName());
	friendManager.acceptFriendRequest(sender, owner);
	ModelAndView mav = new ModelAndView("redirect:/user/profile");

	logger.info("user"+id +sender.getUsername()+"and" +owner.getId()+owner.getUsername()+"are friends");
	return mav;
}

@RequestMapping(value = "/remove/{id}",method = RequestMethod.GET)
public ModelAndView delete(@PathVariable("id") Long id,Principal principal) {
	User sender = userManager.getUserById(id);
	User owner = userManager.getUserByUsername(principal.getName());
	friendManager.deleteFriend(sender, owner);
	ModelAndView mav = new ModelAndView("redirect:/user/profile");

	logger.info("user"+id +sender.getUsername()+"and" +owner.getId()+owner.getUsername()+"delet friends");
	return mav;
}
@RequestMapping(value = "/add/{id}",method = RequestMethod.GET)
public ModelAndView add(@PathVariable("id") Long id,Principal principal) {
	User sender = userManager.getUserById(id);
	User owner = userManager.getUserByUsername(principal.getName());
	friendManager.deleteFriend(sender, owner);
	ModelAndView mav = new ModelAndView("redirect:/user/profile");

	logger.info("user"+id +sender.getUsername()+"and" +owner.getId()+owner.getUsername()+"delet friends");
	return mav;
}

}