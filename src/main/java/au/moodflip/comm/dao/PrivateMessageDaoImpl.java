package au.moodflip.comm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.moodflip.comm.model.PrivateMessage;

@Repository
public class PrivateMessageDaoImpl implements PrivateMessageDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createPrivateMessage(PrivateMessage pMessage) {
		sessionFactory.getCurrentSession().save(pMessage);
	}

	@Override
	public List<PrivateMessage> listPrivateMessage() {
		return sessionFactory.getCurrentSession()
				.createQuery("from PrivateMessage").list();
	}

	@Override
	public List<PrivateMessage> listPrivateMessageBySenderId(Long senderId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from PrivateMessage where senderId = :senderId");
		query.setParameter("senderId", senderId);
		return query.list();
	}

	@Override
	public List<PrivateMessage> listPrivateMessageByReceiverId(Long receiverId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from PrivateMessage where receiverId = :receiverId");
		query.setParameter("receiverId", receiverId);
		return query.list();
	}

	@Override
	public List<PrivateMessage> listPrivateMessageByUserId(Long userId) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from PrivateMessage where senderId = :userId or receiverId = :userId");
		query.setParameter("userId", userId);
		return query.list();
	}

	@Override
	public PrivateMessage getPrivateMessageById(Long id) {
		return (PrivateMessage) sessionFactory.getCurrentSession().get(
				PrivateMessage.class, id);
	}

	@Override
	public void editPrivateMessage(PrivateMessage pMessage) {
		sessionFactory.getCurrentSession().merge(pMessage);
	}

	@Override
	public void removePrivateMessage(Long id) {
		PrivateMessage pMessage = (PrivateMessage) sessionFactory
				.getCurrentSession().load(PrivateMessage.class, id);
		if (null != pMessage) {
			sessionFactory.getCurrentSession().delete(pMessage);
		}
	}

	@Override
	public List<PrivateMessage> listPrivateMessageBySenderAndReceiverId(
			Long userId_1, Long userId_2) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from PrivateMessage where (senderId = :userId_1 and receiverId = :userId_2) or (senderId = :userId_2 and receiverId = :userId_1)");
		query.setParameter("userId_1", userId_1);
		query.setParameter("userId_2", userId_2);
		return query.list();
	}

}
