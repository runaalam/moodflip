package au.moodflip.comm.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.moodflip.comm.model.PrivateMessage;
import au.moodflip.personalisation.model.User;

@Repository
public class PrivateMessageDaoImpl implements PrivateMessageDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public PrivateMessage createPrivateMessage(PrivateMessage pMessage) {
		return (PrivateMessage) sessionFactory.getCurrentSession().get(
				PrivateMessage.class, sessionFactory.getCurrentSession().save(pMessage));
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
			User sender, User receiver) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from PrivateMessage where (sender.id = :userId_1 and receiver.id = :userId_2) or (sender.id = :userId_2 and receiver.id = :userId_1) order by created_at");
		query.setParameter("userId_1", sender.getId());
		query.setParameter("userId_2", receiver.getId());
		return query.list();
	}

	@Override
	public List<PrivateMessage> updatePrivateMessageBySenderAndReceiverId(
			User sender, User receiver, Date datetime) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from PrivateMessage where created_at > :datetime and (sender.id = :userId_2 and receiver.id = :userId_1) order by created_at");
		query.setParameter("datetime", datetime);
		query.setParameter("userId_1", sender.getId());
		query.setParameter("userId_2", receiver.getId());
		return query.list();
	}

}
