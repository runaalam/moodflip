package au.moodflip.dao.comm;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.moodflip.model.comm.Topic;

@Repository
public class TopicDaoImpl implements TopicDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void createTopic(Topic topic) {
		sessionFactory.getCurrentSession().save(topic);
	}

	@Override
	public List<Topic> listTopic() {
		return sessionFactory.getCurrentSession().createQuery("from Topic")
				.list();
	}

	@Override
	public List<Topic> listTopicByForumId(Long forumId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Topic where forum.id = :forumId");
		query.setParameter("forumId", forumId);
		return query.list();
	}

	@Override
	public Topic getTopicById(Long id) {
		return (Topic) sessionFactory.getCurrentSession().get(Topic.class, id);
	}

	@Override
	public void editTopic(Topic topic) {
		sessionFactory.getCurrentSession().merge(topic);
	}

	@Override
	public void removeTopic(Long id) {
		Topic topic = (Topic) sessionFactory.getCurrentSession().load(
				Topic.class, id);
		if (null != topic) {
			sessionFactory.getCurrentSession().delete(topic);
		}
	}

	@Override
	public void upVoteTopic(Long id) {
		Topic topic = (Topic) sessionFactory.getCurrentSession().get(
				Topic.class, id);
		topic.setUpVote(topic.getUpVote() + 1);
	}

	@Override
	public void downVoteTopic(Long id) {
		Topic topic = (Topic) sessionFactory.getCurrentSession().get(
				Topic.class, id);
		topic.setDownVote(topic.getDownVote() + 1);
	}

}
