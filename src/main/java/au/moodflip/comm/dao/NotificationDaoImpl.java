package au.moodflip.comm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.moodflip.comm.model.Notification;

@Repository
public class NotificationDaoImpl implements NotificationDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createNotification(Notification notification) {
		sessionFactory.getCurrentSession().save(notification);
	}

	@Override
	public List<Notification> listNotification() {
		return sessionFactory.getCurrentSession()
				.createQuery("from Notification").list();
	}

	@Override
	public List<Notification> listNotificationByUserId(Long userId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Notification where userId = :userId order by id");
		query.setParameter("userId", userId);
		return query.list();
	}
	
	public List<Notification> listNewNotificationByUserId(Long userId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Notification where userId = :userId and read = false order by id");
		query.setParameter("userId", userId);
		return query.list();
	}

	@Override
	public Notification getNotificationById(Long id) {
		return (Notification) sessionFactory.getCurrentSession().get(
				Notification.class, id);
	}

	@Override
	public void editNotification(Notification notification) {
		sessionFactory.getCurrentSession().merge(notification);
	}

	@Override
	public void removeNotification(Long id) {
		Notification notification = (Notification) sessionFactory
				.getCurrentSession().load(Notification.class, id);
		if (null != notification) {
			sessionFactory.getCurrentSession().delete(notification);
		}
	}

	@Override
	public void setNotificationRead(Long id) {
		Notification notification = (Notification) sessionFactory.getCurrentSession().get(
				Notification.class, id);
		notification.setRead(true);
	}

	@Override
	public Notification getLatestNotReadNotificationByUserId(Long userId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Notification where userId = :userId and read = 0 order by created_at desc");
		query.setParameter("userId", userId);
		return (Notification) query.setMaxResults(1).uniqueResult();
	}

}