package au.moodflip.comm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.moodflip.comm.model.CardSuggest;

@Repository
public class CardSuggestDaoImpl implements CardSuggestDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addCardSuggest(CardSuggest card) {
		sessionFactory.getCurrentSession().save(card);
	}

	@Override
	public List<CardSuggest> listSuggestedCards() {
		return sessionFactory.getCurrentSession()
				.createCriteria(CardSuggest.class).list();
	}

	@Override
	public List<CardSuggest> listSuggestedCardsByTopicId(Long topicId) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from CardSuggest where topic.id = :topicId order by id");
		query.setParameter("topicId", topicId);
		return query.list();
	}

	@Override
	public CardSuggest getCardSuggestById(Long id) {
		return (CardSuggest) sessionFactory.getCurrentSession().get(
				CardSuggest.class, id);
	}

	@Override
	public void editCardSuggest(CardSuggest card) {
		sessionFactory.getCurrentSession().merge(card);
	}

	@Override
	public void removeCardSuggest(Long id) {
		CardSuggest card = (CardSuggest) sessionFactory
				.getCurrentSession().load(CardSuggest.class, id);
		if (card != null) {
			sessionFactory.getCurrentSession().delete(card);
		}
	}

	@Override
	public void upVoteComment(Long id) {
		CardSuggest card = (CardSuggest) sessionFactory
				.getCurrentSession().get(CardSuggest.class, id);
		card.setUpVote(card.getUpVote() + 1);
	}

	@Override
	public void downVoteComment(Long id) {
		CardSuggest card = (CardSuggest) sessionFactory
				.getCurrentSession().get(CardSuggest.class, id);
		card.setUpVote(card.getUpVote() - 1);
	}

}
