package au.moodflip.comm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.comm.dao.NotificationDao;
import au.moodflip.comm.model.Notification;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

	@Autowired
    private NotificationDao notificationDao;
	
	@Override
	public void createNotification(Notification notification) {
		Notification latest = notificationDao
				.getLatestNotReadNotificationByUserId(notification.getUserId());
		if (latest != null) {
			if (latest.getMessage().equals(notification.getMessage())) {
				latest.setCreatedAt(new Date());
				editNotification(latest);
				return;
			}
		}
		notificationDao.createNotification(notification);
	}
	
	@Override
	public void createNotification(String message, String url, Long userId) {
		Notification notification = new Notification();
		notification.setMessage(message);
		notification.setUrl(url);
		notification.setUserId(userId);
		notification.setCreatedAt(new Date());
		createNotification(notification);
	}

	@Override
	public List<Notification> listNotification() {
		return notificationDao.listNotification();
	}

	@Override
	public List<Notification> listNotificationByUserId(Long userId) {
		return notificationDao.listNotificationByUserId(userId);
	}

	@Override
	public Notification getNotificationById(Long id) {
		return notificationDao.getNotificationById(id);
	}

	@Override
	public void editNotification(Notification notification) {
		notificationDao.editNotification(notification);
	}

	@Override
	public void removeNotification(Long id) {
		notificationDao.removeNotification(id);
	}

	@Override
	public void setNotificationRead(Long id) {
		notificationDao.setNotificationRead(id);
	}

}
