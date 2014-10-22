package au.moodflip.comm.service;

import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.comm.dao.TopicDao;
import au.moodflip.comm.model.Topic;
//
@Service
public class TopicServiceImpl implements TopicService {
	
	@Autowired
    private TopicDao topicDao;

	@Transactional
	public void createTopic(Topic topic) {
		topicDao.createTopic(topic);
	}

	@Transactional
	public List<Topic> listTopic() {
		return topicDao.listTopic();
	}

	@Transactional
	public List<Topic> listTopicByForumId(Long forumId) {
		return topicDao.listTopicByForumId(forumId);
	}

	@Transactional
	public Topic getTopicById(Long id) {
		return topicDao.getTopicById(id);
	}

	@Transactional
	public void editTopic(Topic topic) {
		topicDao.editTopic(topic);
	}

	@Transactional
	public void removeTopic(Long id) {
		topicDao.removeTopic(id);
	}

	@Transactional
	public void upVoteTopic(Long id) {
		topicDao.upVoteTopic(id);
	}

	@Transactional
	public void downVoteTopic(Long id) {
		topicDao.downVoteTopic(id);
	}

}
