package au.moodflip.service.comm;

import java.util.List;

import au.moodflip.model.comm.Topic;

public interface TopicService {
	
	public void createTopic(Topic topic);

	public List<Topic> listTopic();
	
	public List<Topic> listTopicByForumId(Long forumId);
	
	public Topic getTopicById(Long id);
	
	public void editTopic(Topic topic);
	
	public void removeTopic(Long id);
	
	public void upVoteTopic(Long id);
	
	public void downVoteTopic(Long id);

}
