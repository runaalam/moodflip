package au.moodflip.test.comm;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import au.moodflip.comm.dao.ForumDao;
import au.moodflip.comm.model.Forum;

public class ForumDaoTest extends BaseTest {

	@Autowired
	private ForumDao forumDao;

	public void setUp() throws Exception {
		if (!setupDone) {
			super.setUp();
			Forum forum1 = new Forum();
			forum1.setForumName("Test1");
			forumDao.addForum(forum1);

			Forum forum2 = new Forum();
			forum2.setForumName("Test2");
			forumDao.addForum(forum2);

			super.setSetupDone();
		}
	}

	@Test
	public void testListAll() {
		assertFalse(forumDao.listForum().isEmpty());
	}

	@Test
	public void testGetById() {
		assertNotNull(forumDao.getForumById(1L));
	}

	@Test
	public void testSaveForum() {
		Forum forum = new Forum();
		forum.setForumName("Test2");

		forumDao.addForum(forum);

		assertNotNull(forumDao.getForumById(forum.getId()));
		assertNull(forumDao.getForumById(forum.getId()).getTopics());
	}

	@Test
	public void tesEditForum() {
		Forum forum = forumDao.getForumById(1L);
		forum.setForumName("Something else");

		String forumName = forum.getForumName();

		forumDao.editForum(forum);

		assertNotNull(forumDao.getForumById(1L));
		assertEquals(forumName, forumDao.getForumById(1L).getForumName());
	}

	@Test
	public void testDeleteForum() {
		Forum forum = forumDao.getForumById(2L);
		forumDao.removeForum(forum.getId());

		assertNull(forumDao.getForumById(2L));
	}

}
