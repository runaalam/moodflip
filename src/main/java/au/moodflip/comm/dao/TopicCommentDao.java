package au.moodflip.comm.dao;

import java.util.List;

import au.moodflip.comm.model.TopicComment;
//
public interface TopicCommentDao {
	
	public void createComment(TopicComment comment);

	public List<TopicComment> listComment();
	
	public List<TopicComment> listCommentByTopicId(Long topicId);
	
	public TopicComment getCommentById(Long id);
	
	public void editComment(TopicComment comment);
	
	public void removeComment(Long id);
	
	public void upVoteComment(Long id);
	
	public void downVoteComment(Long id);

}
