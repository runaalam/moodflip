package au.moodflip.cardgame.service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.Task;
import au.moodflip.cardgame.model.Card.Symptom;
import au.moodflip.cardgame.model.UsersCard;

@Service(value="cardManager")
@Transactional
public class CardManagerDbImpl implements CardManager{
	private static final Logger logger = LoggerFactory.getLogger(CardManagerDbImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public long add(Card card){
		linkCardsAndTasks(card);
		sessionFactory.getCurrentSession().save(card);
		return card.getCardId();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Card> getCards() {
		return new TreeSet<Card>(sessionFactory.getCurrentSession().createQuery("from Card").list());
	}
	
	@Override
	public Card getById(long id) {
		Card card = (Card)sessionFactory.getCurrentSession().get(Card.class, id);
		logger.info("Got card: " + card);
		return card;
	}

	@Override
	public void update(Card card) {
		System.out.println("in update");
		linkCardsAndTasks(card);
		Card card2 = (Card)sessionFactory.getCurrentSession().get(Card.class, card.getCardId());
		System.out.println("applying update");
		sessionFactory.getCurrentSession().merge(card); 
	}

	@Override
	public void delete(long id) {
		Card c = getById(id);
		sessionFactory.getCurrentSession().delete(c);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<Card> getCards(Symptom symptom) {
		String hql = "FROM Card AS c WHERE c.symptom = :symptom_grp";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("symptom_grp", symptom);
		return new TreeSet<Card>(query.list());
	}

	@Override
	public Set<Card> getCards(Set<UsersCard> ids) {
		if (ids.isEmpty()) { return new TreeSet<Card>(); }
		Set<Card> cards = new TreeSet<Card>();
		for (UsersCard cardId : ids){
			cards.add(getById(cardId.getId().getCardId()));
		}
		return cards;
	}
	
	// link tasks to card and each task to its adjacent tasks
	private void linkCardsAndTasks(Card card){
		List<Task> tasks = card.getTasks();
		for (int i=0; i < tasks.size(); i++){
			if (i < tasks.size()-1){
				tasks.get(i).setNext(tasks.get(i+1));
			}
			tasks.get(i).setCard(card);
			logger.info("simple name: " + tasks.get(i).getClass().getSimpleName());
		}
	}
}
