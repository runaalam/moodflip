package au.moodflip.comm.service;

import java.util.List;

import au.moodflip.comm.model.Notification;

public interface NotificationService {

	public void createNotification(Notification notification);
	
	public void createNotification(String message, String url, Long userId);
	
	public List<Notification> listNotification();
	
	public List<Notification> listNotificationByUserId(Long userId);
	
	public List<Notification> listNewNotificationByUserId(Long userId);
	
	public Notification getNotificationById(Long id);
	
	public void editNotification(Notification notification);
	
	public void removeNotification(Long id);
	
	public void setNotificationRead(Long id);
	
}
