package au.moodflip.comm.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.comm.model.TopicComment;
import au.moodflip.comm.service.TopicCommentService;
import au.moodflip.personalisation.service.UserManager;

@Controller
@SessionAttributes(value = {"comment"})
@RequestMapping(value = "/forums/comment")
public class CommentController {
	
	private final String FOLDER = "communication";
	
	@Autowired
	private TopicCommentService topicCommentService;
	
	@Autowired
	private UserManager userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<TopicComment> listComment(@RequestParam("topicId") Long topicId) {
		List<TopicComment> comments = topicCommentService.listCommentByTopicId(topicId);
		return comments;
	}
	
	@RequestMapping(value = "/edit/{commentId}", method = RequestMethod.GET)
	public ModelAndView editComment(@PathVariable("commentId") Long commentId, Principal principal) {
		ModelAndView mav = new ModelAndView(FOLDER + "/editComment");
		TopicComment comment = topicCommentService.getCommentById(commentId);
		
		if(comment.getUser().getId() != userService.getUserByUsername(principal.getName()).getId())
			return new ModelAndView("redirect:/403");
		
		mav.addObject("comment", comment);
		mav.addObject("topicId", comment.getTopic().getId());
		return mav;
	}

	@RequestMapping(value = "/edit/{commentId}", method = RequestMethod.POST)
	public String updateComment(@PathVariable("commentId") Long commentId,
			@ModelAttribute("comment") @Validated TopicComment comment, BindingResult result,
			SessionStatus status, Principal principal) {
		if (result.hasErrors()) {
			//logger

            return FOLDER + "/editComment";
        }
		
		if(comment.getUser().getId() != userService.getUserByUsername(principal.getName()).getId())
			return "redirect:/403";

		comment.setEditedAt(new Date());

		topicCommentService.editComment(comment);
		status.setComplete();
		return "redirect:/forums/topic/" + comment.getTopic().getId();
	}
	
	@RequestMapping(value = "/delete/{commentId}", method = RequestMethod.GET)
	public @ResponseBody Boolean deleteComment(@PathVariable("commentId") Long commentId) {
		topicCommentService.removeComment(commentId);
		return true;
	}
	
	@RequestMapping(value = "/up_vote/{commentId}", method = RequestMethod.GET)
	public @ResponseBody Boolean upVoteComment(@PathVariable("commentId") Long commentId) {
		topicCommentService.upVoteComment(commentId);
		return true;
	}

	@RequestMapping(value = "/down_vote/{commentId}", method = RequestMethod.GET)
	public @ResponseBody Boolean downVoteComment(@PathVariable("commentId") Long commentId) {
		topicCommentService.downVoteComment(commentId);
		return true;
	}

}
