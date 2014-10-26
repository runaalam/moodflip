package au.moodflip.comm.dao;

import java.util.List;

import au.moodflip.comm.model.Notification;

public interface NotificationDao {
	
	public void createNotification(Notification notification);
	
	public List<Notification> listNotification();
	
	public List<Notification> listNotificationByUserId(Long userId);
	
	public List<Notification> listUnreadNotificationByUserId(Long userId);
	
	public Notification getNotificationById(Long id);
	
	public void editNotification(Notification notification);
	
	public void removeNotification(Long id);
	
	public void setNotificationRead(Long id);
	
	public Notification getLatestNotReadNotificationByUserId(Long userId);
	
	public List<Notification> updateNotificationByUserId(Long userId, Long id);
	
	public List<Notification> updateUnreadNotificationByUserId(Long userId, Long id);

}
