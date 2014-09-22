package au.moodflip.cardgame.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.cardgame.model.Card;

@Service(value="cardManager")
@Transactional
public class DatabaseCardManager implements CardManager{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addCard(Card card){
		sessionFactory.getCurrentSession().save(card);
	}

	@Override
	public List<Card> getCards() {
		return sessionFactory.getCurrentSession().createQuery("from Card").list();
	}

	@Override
	public Card getCardById(long id) {
		return (Card)sessionFactory.getCurrentSession().get(Card.class, id);
	}

	@Override
	public void updateCard(Card card) {
		sessionFactory.getCurrentSession().merge(card);
	}

	@Override
	public void deleteCard(long id) {
		Card c = getCardById(id);
		sessionFactory.getCurrentSession().delete(c);
	}
	
	
}
