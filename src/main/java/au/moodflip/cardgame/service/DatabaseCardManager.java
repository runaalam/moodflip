package au.moodflip.cardgame.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.HibernateException;
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
public class DatabaseCardManager implements CardManager{
	private static final Logger logger = LoggerFactory.getLogger(DatabaseCardManager.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	BasicDataSource dataSource;
	
	@Override
	public void addCard(Card card){
		sessionFactory.getCurrentSession().save(card);
	}

	@Override
	public List<Card> getCards() {
		System.out.println("DB url is: " + dataSource.getUrl());
		return sessionFactory.getCurrentSession().createQuery("from Card").list();
	}

	@Override
	public Card getCardById(long id) {
		Card card = (Card)sessionFactory.getCurrentSession().get(Card.class, id);
		return card;
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
	
	public void test(){
		System.out.println("in test()");
		Card card = new Card();
		card.setTitle("some title");
		card.setAttempts(1);
		card.setAvgRating(0);
		card.setCompletions(0);
		card.setIntro("intro");
		card.setLevel(1);
		card.setOutro("out");
		card.setSymptom(Symptom.APPETITE);
		List<Mission> missions= new ArrayList<Mission>();
		missions.add(new Mission("mish1"));
		missions.add(new Mission("mish2"));
		missions.add(new Mission("mish3"));
		card.setMissions(missions);
		
		 Session session = sessionFactory.openSession();
	      Transaction tx = null;
		 try{
	         tx = session.beginTransaction();
	         session.save(card);
	         tx.commit();
		 }catch (HibernateException e){
			 e.printStackTrace();
		 }finally {
	         session.close(); 
	      }
	}
	
	
}
