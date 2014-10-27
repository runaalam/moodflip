package au.moodflip.test.comm;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import au.moodflip.comm.dao.TopicDao;
import au.moodflip.comm.model.Topic;

public class TopicDaoTest extends BaseTest {

	@Autowired
	private TopicDao topicDao;
	
	public void setUp() throws Exception {
		super.setUp();
		
		Topic topic1 = new Topic();
		topic1.setName("Test Topic 1");
		topic1.setContent("some content.");
		topic1.setCreatedAt(new Date());
		topicDao.createTopic(topic1);
		
		Topic topic2 = new Topic();
		topic2.setName("Test Topic 2");
		topic2.setContent("some content 2.");
		topic2.setCreatedAt(new Date());
		topicDao.createTopic(topic2);
	}

	@Test
	public void testListAll() {
		assertFalse(topicDao.listTopic().isEmpty());
	}

	@Test
	public void testGetById() {
		assertNotNull(topicDao.getTopicById(1L));
	}

	@Test
	public void testSaveTopic() {
		Topic topic = new Topic();
		topic.setName("Test Topic 2");
		topic.setContent("some content.");
		topic.setCreatedAt(new Date());
		
		String name = topic.getName();
		String content = topic.getContent();
		Date createdAt = topic.getCreatedAt();

		topicDao.createTopic(topic);
		
		assertNotNull(topicDao.getTopicById(topic.getId()));
		assertEquals(name, topicDao.getTopicById(topic.getId()).getName());
		assertEquals(content, topicDao.getTopicById(topic.getId()).getContent());
		assertEquals(createdAt, topicDao.getTopicById(topic.getId()).getCreatedAt());
	}
	
	@Test
	public void testEditTopic() {
		Topic topic = topicDao.getTopicById(1L);
		topic.setName("Some name");
		topic.setContent("changed content.");
		topic.setCreatedAt(new Date());
		
		String name = topic.getName();
		String content = topic.getContent();
		Date createdAt = topic.getCreatedAt();
		
		topicDao.editTopic(topic);
		
		assertNotNull(topicDao.getTopicById(1L));
		assertEquals(name, topicDao.getTopicById(1L).getName());
		assertEquals(content, topicDao.getTopicById(1L).getContent());
		assertEquals(createdAt, topicDao.getTopicById(1L).getCreatedAt());
	}

	@Test
	public void testDeleteTopic() {
		Topic topic = topicDao.getTopicById(2L);
		topicDao.removeTopic(topic.getId());
		
		assertNull(topicDao.getTopicById(2L));
	}
	
	@Test
	public void testUpVoteTopic() {
		Topic topic = topicDao.getTopicById(1L);
		int oldUpVote = topic.getUpVote();
		topicDao.upVoteTopic(1L);
		
		assertEquals(oldUpVote + 1, topicDao.getTopicById(1L).getUpVote());
	}
	
	@Test
	public void testDownVoteTopic() {
		Topic topic = topicDao.getTopicById(1L);
		int oldDownVote = topic.getDownVote();
		topicDao.downVoteTopic(1L);
		
		assertEquals(oldDownVote + 1, topicDao.getTopicById(1L).getDownVote());
	}

}
