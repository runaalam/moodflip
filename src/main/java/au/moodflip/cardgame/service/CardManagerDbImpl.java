package au.moodflip.cardgame.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.cardgame.controller.CardGameController;
import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.Card.Symptom;
import au.moodflip.cardgame.model.Mission;

@Service(value="cardManager")
@Transactional
public class CardManagerDbImpl implements CardManager{
	private static final Logger logger = LoggerFactory.getLogger(CardManagerDbImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void add(Card card){
		sessionFactory.getCurrentSession().save(card);
	}

	@Override
	public List<Card> getCards() {
		return sessionFactory.getCurrentSession().createQuery("from Card").list();
	}
	
	@Override
	public Card getById(long id) {
		Card card = (Card)sessionFactory.getCurrentSession().get(Card.class, id);
		return card;
	}

	@Override
	public void update(Card card) {
		sessionFactory.getCurrentSession().merge(card);
	}

	@Override
	public void delete(long id) {
		Card c = getById(id);
		sessionFactory.getCurrentSession().delete(c);
	}

	@Override
	public List<Card> getCards(Symptom symptom) {
		String hql = "FROM Card AS c WHERE c.symptom = :symptom_grp";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("symptom_grp", symptom);
		return (List<Card>)query.list();
	}
	
}
