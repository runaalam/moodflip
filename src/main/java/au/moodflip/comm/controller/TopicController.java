package au.moodflip.comm.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import au.moodflip.comm.model.Topic;
import au.moodflip.comm.model.TopicComment;
import au.moodflip.comm.service.ForumService;
import au.moodflip.comm.service.TopicCommentService;
import au.moodflip.comm.service.TopicService;
import au.moodflip.personalisation.service.UserManager;

@Controller
@SessionAttributes(value = {"forum", "topic", "comments", "comment"})
@RequestMapping(value = "/forum/{forumId}")
public class TopicController {

	private final String FOLDER = "communication";

	@Autowired
	private TopicService topicService;
	
	@Autowired
	private TopicCommentService topicCommentService;

	@Autowired
	private ForumService forumService;
	
	@Autowired
	private UserManager userService;
	
//	@ModelAttribute("forum")
//	public Forum getForum(@PathVariable("forumId") Long forumId) {
//	    return forumService.getForumById(forumId);
//	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAllTopics(@PathVariable("forumId") Long forumId, @RequestParam(value="p", required = false) Integer p) {
		ModelAndView mav = new ModelAndView(FOLDER + "/listTopics");
		List<Topic> topics = topicService.listTopicByForumId(forumId);
		
		// initialize PagedListHolder with our list, set current page defaulted to 0, and pass it to the view
		PagedListHolder pagedListHolder = new PagedListHolder(topics);
		int page = p == null ? 0 : p;
		pagedListHolder.setPage(page);
		int pageSize = 10;
		pagedListHolder.setPageSize(pageSize);
		mav.addObject("pagedListHolder", pagedListHolder);
		
//		mav.addObject("topics", topics);
		mav.addObject("forum", forumService.getForumById(forumId));
		return mav;
	}

	@RequestMapping(value = "/topic/{id}", method = RequestMethod.GET)
	public ModelAndView show(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView(FOLDER + "/showTopic");
		Topic topic = topicService.getTopicById(id);
		mav.addObject("topic", topic);
		mav.addObject("comments", topicCommentService.listCommentByTopicId(id));
		
		TopicComment comment = new TopicComment();
		mav.getModelMap().put("comment", comment);
		return mav;
	}

	@RequestMapping(value = "/topic/create", method = RequestMethod.GET)
	public ModelAndView newForumForm() {
		ModelAndView mav = new ModelAndView(FOLDER + "/newTopic");
		Topic topic = new Topic();
		mav.getModelMap().put("topic", topic);
		return mav;
	}

	@RequestMapping(value = "/topic/create", method = RequestMethod.POST)
	public String create(@PathVariable("forumId") Long forumId,
			@ModelAttribute("topic") @Validated Topic topic, BindingResult result,
			SessionStatus status, Principal principal) {
		if (result.hasErrors()) {
			//logger

            return FOLDER + "/newTopic";
        }
		topic.setUserId(userService.getUserByUsername(principal.getName()).getId());
		topic.setForum(forumService.getForumById(forumId));
		topic.setCreatedAt(new Date());

		topicService.createTopic(topic);
		
		status.setComplete();
		return "redirect:/forum/{forumId}";
	}

	@RequestMapping(value = "/topic/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView(FOLDER + "/editTopic");
		Topic topic = topicService.getTopicById(id);
		mav.addObject("topic", topic);
		return mav;
	}

	@RequestMapping(value = "/topic/edit/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute("topic") @Validated Topic topic, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			//logger

            return FOLDER + "/editTopic";
        }

		topic.setEditedAt(new Date());

		topicService.editTopic(topic);
		status.setComplete();
		return "redirect:/forum/{forumId}/topic/{id}";
	}

	@RequestMapping(value = "/topic/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("redirect:/forum/{forumId}");
		topicService.removeTopic(id);
		return mav;
	}

	@RequestMapping(value = "/topic/up_vote/{id}", method = RequestMethod.GET)
	public ModelAndView upVote(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("redirect:/forum/{forumId}/topic/{id}");
		topicService.upVoteTopic(id);
		return mav;
	}

	@RequestMapping(value = "/topic/down_vote/{id}", method = RequestMethod.GET)
	public ModelAndView downVote(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("redirect:/forum/{forumId}/topic/{id}");
		topicService.downVoteTopic(id);
		return mav;
	}
	
	
	/**
	 * TopicComment
	 */
	
	@RequestMapping(value = "/topic/{id}/comment/create", method = RequestMethod.GET)
	public ModelAndView newComment() {
		ModelAndView mav = new ModelAndView(FOLDER + "/newTopicComment");
		TopicComment comment = new TopicComment();
		mav.getModelMap().put("comment", comment);
		return mav;
	}
	
	@RequestMapping(value = "/topic/{id}/comment/create", method = RequestMethod.POST)
	public String createComment(@PathVariable("id") Long id,
			@ModelAttribute("comment") @Validated TopicComment comment, BindingResult result,
			SessionStatus status, Principal principal) {
		if (result.hasErrors()) {
			//logger

            return FOLDER + "/showTopic";
        }
		comment.setUserId(userService.getUserByUsername(principal.getName()).getId());
		comment.setTopic(topicService.getTopicById(id));
		comment.setCreatedAt(new Date());

		topicCommentService.createComment(comment);
		status.setComplete();
		return "redirect:/forum/{forumId}/topic/{id}";
	}
	
	@RequestMapping(value = "/topic/{id}/comment/edit/{commentId}", method = RequestMethod.GET)
	public ModelAndView editComment(@PathVariable("commentId") Long commentId) {
		ModelAndView mav = new ModelAndView(FOLDER + "/editComment");
		TopicComment comment = topicCommentService.getCommentById(commentId);
		mav.addObject("comment", comment);
		return mav;
	}

	@RequestMapping(value = "/topic/{id}/comment/edit/{commentId}", method = RequestMethod.POST)
	public String updateComment(@PathVariable("commentId") Long commentId,
			@ModelAttribute("comment") @Validated TopicComment comment, BindingResult result,
			SessionStatus status) {
		if (result.hasErrors()) {
			//logger

            return FOLDER + "/editComment";
        }

		comment.setEditedAt(new Date());

		topicCommentService.editComment(comment);
		status.setComplete();
		return "redirect:/forum/{forumId}/topic/{id}";
	}
	
	@RequestMapping(value = "/topic/{id}/comment/delete/{commentId}", method = RequestMethod.GET)
	public ModelAndView deleteComment(@PathVariable("commentId") Long commentId) {
		ModelAndView mav = new ModelAndView("redirect:/forum/{forumId}/topic/{id}");
		topicCommentService.removeComment(commentId);
		return mav;
	}
	
	@RequestMapping(value = "/topic/{id}/comment/up_vote/{commentId}", method = RequestMethod.GET)
	public ModelAndView upVoteComment(@PathVariable("commentId") Long commentId) {
		ModelAndView mav = new ModelAndView("redirect:/forum/{forumId}/topic/{id}");
		topicCommentService.upVoteComment(commentId);
		return mav;
	}

	@RequestMapping(value = "/topic/{id}/comment/down_vote/{commentId}", method = RequestMethod.GET)
	public ModelAndView downVoteComment(@PathVariable("commentId") Long commentId) {
		ModelAndView mav = new ModelAndView("redirect:/forum/{forumId}/topic/{id}");
		topicCommentService.downVoteComment(commentId);
		return mav;
	}

}
