package au.moodflip.test.comm;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import au.moodflip.comm.dao.TopicCommentDao;
import au.moodflip.comm.dao.TopicDao;
import au.moodflip.comm.model.Topic;
import au.moodflip.comm.model.TopicComment;

public class TopicCommentDaoTest extends BaseTest {

	@Autowired
	private TopicCommentDao topicCommentDao;
	
	@Autowired
	private TopicDao topicDao;
	
	private Topic topic;
	private long topicId;
	
	public void setUp() throws Exception {
		super.setUp();
		
		topic = new Topic();
		topic.setName("Topic name");
		topic.setContent("topic content");
		topicDao.createTopic(topic);
		topicId = topic.getId();
		
		TopicComment comment1 = new TopicComment();
		comment1.setContent("some content.");
		comment1.setCreatedAt(new Date());
		comment1.setTopic(topic);
		topicCommentDao.createComment(comment1);
		
		TopicComment comment2 = new TopicComment();
		comment2.setContent("some content.");
		comment2.setCreatedAt(new Date());
		comment2.setTopic(topic);
		topicCommentDao.createComment(comment2);
	}

	@Test
	public void testListAll() {
		assertFalse(topicCommentDao.listComment().isEmpty());
	}
	
	@Test
	public void testListByTopicId() {
		assertFalse(topicCommentDao.listCommentByTopicId(topicId).isEmpty());
	}

	@Test
	public void testGetById() {
		assertNotNull(topicCommentDao.getCommentById(1L));
	}

	@Test
	public void testSaveTopicComment() {
		TopicComment comment = new TopicComment();
		comment.setContent("reply content");
		comment.setCreatedAt(new Date());
		comment.setTopic(topic);
		
		String content = comment.getContent();
		Date createdAt = comment.getCreatedAt();

		topicCommentDao.createComment(comment);
		
		assertNotNull(topicCommentDao.getCommentById(comment.getId()));
		assertEquals(content, topicCommentDao.getCommentById(comment.getId()).getContent());
		assertEquals(createdAt, topicCommentDao.getCommentById(comment.getId()).getCreatedAt());
		assertEquals(topicId, topicCommentDao.getCommentById(comment.getId()).getTopic().getId());
	}
	
	@Test
	public void testEditTopicComment() {
		TopicComment comment = topicCommentDao.getCommentById(1L);
		comment.setContent("changed content.");
		comment.setCreatedAt(new Date());
		
		String content = comment.getContent();
		Date createdAt = comment.getCreatedAt();
		
		topicCommentDao.editComment(comment);
		
		assertNotNull(topicCommentDao.getCommentById(1L));
		assertEquals(content, topicCommentDao.getCommentById(1L).getContent());
		assertEquals(createdAt, topicCommentDao.getCommentById(1L).getCreatedAt());
	}

	@Test
	public void testDeleteTopicComment() {
		TopicComment comment = topicCommentDao.getCommentById(2L);
		topicCommentDao.removeComment(comment.getId());
		
		assertNull(topicCommentDao.getCommentById(2L));
	}
	
	@Test
	public void testUpVoteTopicComment() {
		TopicComment comment = topicCommentDao.getCommentById(1L);
		int oldUpVote = comment.getUpVote();
		topicCommentDao.upVoteComment(1L);
		
		assertEquals(oldUpVote + 1, topicCommentDao.getCommentById(1L).getUpVote());
	}
	
	@Test
	public void testDownVoteTopicComment() {
		TopicComment comment = topicCommentDao.getCommentById(1L);
		int oldDownVote = comment.getDownVote();
		topicCommentDao.downVoteComment(1L);
		
		assertEquals(oldDownVote + 1, topicCommentDao.getCommentById(1L).getDownVote());
	}

}
