package au.moodflip.comm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.comm.dao.NotificationDao;
import au.moodflip.comm.model.Notification;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
    private NotificationDao notificationDao;
	
	@Transactional
	public void createNotification(Notification notification) {
		notificationDao.createNotification(notification);
	}

	@Transactional
	public List<Notification> listNotification() {
		return notificationDao.listNotification();
	}

	@Transactional
	public List<Notification> listNotificationByUserId(Long userId) {
		return notificationDao.listNotificationByUserId(userId);
	}

	@Transactional
	public Notification getNotificationById(Long id) {
		return notificationDao.getNotificationById(id);
	}

	@Transactional
	public void editNotification(Notification notification) {
		notificationDao.editNotification(notification);
	}

	@Transactional
	public void removeNotification(Long id) {
		notificationDao.removeNotification(id);
	}

	@Transactional
	public void setNotificationRead(Long id) {
		notificationDao.setNotificationRead(id);
	}

}
