package au.moodflip.test.comm;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import au.moodflip.comm.dao.PrivateMessageDao;
import au.moodflip.comm.model.PrivateMessage;
import au.moodflip.personalisation.service.UserManager;

public class PrivateMessageDaoTest extends BaseTest {

	@Autowired
	private PrivateMessageDao pmDao;

	@Autowired
	private UserManager userManager;

	private Date datetime;

	public void setUp() throws Exception {
		if (!setupDone) {
			super.setUp();

			PrivateMessage pm1 = new PrivateMessage();
			pm1.setContent("test message 1");
			pm1.setCreatedAt(new Date());
			pm1.setReceiver(userManager.getUserByUsername("admin"));
			pm1.setSender(userManager.getUserByUsername("user"));
			pmDao.createPrivateMessage(pm1);

			PrivateMessage pm2 = new PrivateMessage();
			pm2.setContent("test message 2");
			pm2.setCreatedAt(new Date());
			pm2.setReceiver(userManager.getUserByUsername("user"));
			pm2.setSender(userManager.getUserByUsername("admin"));
			pmDao.createPrivateMessage(pm2);

			datetime = pm2.getCreatedAt();

			PrivateMessage pm3 = new PrivateMessage();
			pm3.setContent("test message 3");
			pm3.setCreatedAt(new Date());
			pm3.setReceiver(userManager.getUserByUsername("admin"));
			pm3.setSender(userManager.getUserByUsername("user"));
			pmDao.createPrivateMessage(pm3);
			
			super.setSetupDone();
		}
	}

	@Test
	public void testGetById() {
		assertNotNull(pmDao.getPrivateMessageById(1L));
		assertNotNull(pmDao.getPrivateMessageById(2L));
		assertNotNull(pmDao.getPrivateMessageById(3L));
	}

	@Test
	public void testSavePM() {
		PrivateMessage pm = new PrivateMessage();
		pm.setContent("test create pm");
		pm.setCreatedAt(new Date());
		pm.setReceiver(userManager.getUserByUsername("admin"));
		pm.setSender(userManager.getUserByUsername("user"));
		pmDao.createPrivateMessage(pm);

		assertNotNull(pmDao.getPrivateMessageById(pm.getId()));
	}

	@Test
	public void testListPrivateMessageBySenderAndReceiverId() {
		assertNotNull(pmDao.listPrivateMessageBySenderAndReceiverId(
				userManager.getUserByUsername("admin"),
				userManager.getUserByUsername("user")));
		assertNotNull(pmDao.listPrivateMessageBySenderAndReceiverId(
				userManager.getUserByUsername("user"),
				userManager.getUserByUsername("admin")));
	}

	@Test
	public void testUpdatePrivateMessageBySenderAndReceiverId() {
		assertNotNull(pmDao.updatePrivateMessageBySenderAndReceiverId(
				userManager.getUserByUsername("admin"),
				userManager.getUserByUsername("user"), datetime));
		assertNotNull(pmDao.updatePrivateMessageBySenderAndReceiverId(
				userManager.getUserByUsername("user"),
				userManager.getUserByUsername("admin"), datetime));
	}

}
