package au.moodflip.test.comm;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import au.moodflip.comm.dao.NotificationDao;
import au.moodflip.comm.model.Notification;

public class NotificationDaoTest extends BaseTest {

	@Autowired
	private NotificationDao notificationDao;

	public void setUp() throws Exception {
		if (!setupDone) {
			super.setUp();

			Notification notification1 = new Notification();
			notification1.setMessage("notification1 message");
			notification1.setRead(true);
			notification1.setUrl("/pm/2");
			notification1.setUserId(1);
			notification1.setCreatedAt(new Date());
			notificationDao.createNotification(notification1);

			Notification notification2 = new Notification();
			notification2.setMessage("new reply in topic1");
			notification2.setRead(false);
			notification2.setUrl("/forums/topic/1");
			notification2.setUserId(1);
			notification2.setCreatedAt(new Date());
			notificationDao.createNotification(notification2);

			Notification notification3 = new Notification();
			notification3.setMessage("notification3 message");
			notification3.setRead(false);
			notification3.setUrl("/pm/2");
			notification3.setUserId(1);
			notification3.setCreatedAt(new Date());
			notificationDao.createNotification(notification3);

			Notification notification4 = new Notification();
			notification4.setMessage("for remove test");
			notification4.setRead(false);
			notification4.setUrl("/pm/3");
			notification4.setUserId(1);
			notification4.setCreatedAt(new Date());
			notificationDao.createNotification(notification4);

			Notification notification5 = new Notification();
			notification5.setMessage("for remove test");
			notification5.setRead(false);
			notification5.setUrl("/pm/1");
			notification5.setUserId(2);
			notification5.setCreatedAt(new Date());
			notificationDao.createNotification(notification5);

			super.setSetupDone();
		}
	}

	@Test
	public void testListAll() {
		assertFalse(notificationDao.listNotification().isEmpty());
	}

	@Test
	public void testListByUserId() {
		assertFalse(notificationDao.listNotificationByUserId(1L).isEmpty());
	}

	@Test
	public void testListUnreadByUserId() {
		assertFalse(notificationDao.listUnreadNotificationByUserId(1L)
				.isEmpty());
	}

	@Test
	public void testGetById() {
		assertNotNull(notificationDao.getNotificationById(1L));
		assertNotNull(notificationDao.getNotificationById(2L));
		assertNotNull(notificationDao.getNotificationById(3L));
	}

	@Test
	public void testRemove() {
		Notification notification = notificationDao.getNotificationById(4L);
		notificationDao.removeNotification(notification.getId());

		assertNull(notificationDao.getNotificationById(4L));
	}

	@Test
	public void testSetRead() {
		Notification notification = notificationDao.getNotificationById(3L);
		notificationDao.setNotificationRead(notification.getId());

		assertEquals(true, notificationDao.getNotificationById(3L).isRead());
	}

	@Test
	public void testGetLatestNotReadByUserId() {
		assertNotNull(notificationDao.getLatestNotReadNotificationByUserId(2L));
	}

	@Test
	public void testListUpdateByUserId() {
		assertNotNull(notificationDao.updateNotificationByUserId(1L, 1L));
		assertTrue(notificationDao.updateNotificationByUserId(1L, 5L).isEmpty());
	}

	@Test
	public void testListUpdateUnreadByUserId() {
		assertNotNull(notificationDao.updateUnreadNotificationByUserId(1L, 2L));
		assertTrue(notificationDao.updateUnreadNotificationByUserId(1L, 4L)
				.isEmpty());
	}

}
