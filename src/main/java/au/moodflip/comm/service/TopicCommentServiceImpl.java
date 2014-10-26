package au.moodflip.comm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.comm.dao.TopicCommentDao;
import au.moodflip.comm.model.TopicComment;

@Service
public class TopicCommentServiceImpl implements TopicCommentService {

	@Autowired
    private TopicCommentDao topicCommentDao;
	
	@Transactional
	public void createComment(TopicComment comment) {
		topicCommentDao.createComment(comment);
	}

	@Transactional
	public List<TopicComment> listComment() {
		return topicCommentDao.listComment();
	}

	@Transactional
	public List<TopicComment> listCommentByTopicId(Long topicId) {
		return topicCommentDao.listCommentByTopicId(topicId);
	}

	@Transactional
	public TopicComment getCommentById(Long id) {
		return topicCommentDao.getCommentById(id);
	}

	@Transactional
	public void editComment(TopicComment comment) {
		topicCommentDao.editComment(comment);
	}

	@Transactional
	public void removeComment(Long id) {
		topicCommentDao.removeComment(id);
	}

	@Transactional
	public void upVoteComment(Long id) {
		topicCommentDao.upVoteComment(id);
	}

	@Transactional
	public void downVoteComment(Long id) {
		topicCommentDao.downVoteComment(id);
	}

}
