package au.moodflip.cardgame.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Transient;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.cardgame.model.Card;
import au.moodflip.cardgame.model.Playlist;
import au.moodflip.cardgame.model.PlaylistItem;
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
//		logger.info("Got card: " + card);
		return card;
	}

	@Override
	public Card update(Card card) {
//		logger.info("in update");
		linkCardsAndTasks(card);
//		logger.info("applying update");
		sessionFactory.getCurrentSession().merge(card);
		return card;
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

	/** recommend cards based on top 3 symptoms that aren't in playlist ordered by avg-rating desc
	 * @param symptoms: 3 symptoms array. size must be 3 elements 
	 * @return 3 cards. otherwise, less than 3 cards if playlist already contains recommended card.  
	 * null if input symptoms is less than 3 or no recommendations available
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Card> recommendCards(Map<Symptom, Double> symptoms, long userId) {
		if (symptoms.size() < 3){
			return null;
		}
		Map<Symptom, Double> sortedAssessment = sortByValue(symptoms);
		Symptom[] topSymptoms = new Symptom[3];
		int i = 0;
		for (Map.Entry<Symptom, Double> e : sortedAssessment.entrySet()){
			if (e.getValue() == 0){
				return null;
			}
			topSymptoms[i] = e.getKey();
			if (i++ == 2){
				break;
			}
		}
		String hql = "select c.cardId FROM Card as c WHERE c.cardId NOT IN (SELECT cardId FROM PlaylistItem as p WHERE p.playlist.userId = :userId)  AND c.symptom = :symptom ORDER BY c.avgRating DESC LIMIT 1";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("symptom", topSymptoms[0]);
		query.setParameter("userId", userId);
		List<Long> res = query.list();
		query.setParameter("symptom", topSymptoms[1]);
		List<Long> res2 = query.list();
		query.setParameter("symptom", topSymptoms[2]);
		List<Long> res3 = query.list();
		List<Card> cards = new ArrayList<Card>();
		if (!res.isEmpty()){
			cards.add(getById(res.get(0)));
		}
		if (!res2.isEmpty()){
			cards.add(getById(res2.get(0)));
		}
		if (!res3.isEmpty()){
			cards.add(getById(res3.get(0)));
		}
		return cards;
	}

	
	
	@Override
	public List<Card> getCards(Playlist playlist) {
		List<Card> cards = new ArrayList<Card>();
		if (playlist != null){
			for (PlaylistItem i : playlist.getPlaylistItems()){
				cards.add(getById(i.getCardId()));
			}
		}
		return cards;
	}
	
	// Credit goes to Carter Page
	// http://stackoverflow.com/questions/109383/how-to-sort-a-mapkey-value-on-the-values-in-java
	@Transient
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map )
	{
	    List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>( map.entrySet() );
	    Collections.sort( list, new Comparator<Map.Entry<K, V>>()
	    {
	        public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
	        {
	            return (o1.getValue()).compareTo( o2.getValue() ) * -1; // sort in desc order
	        }
	    } );
	
	    Map<K, V> result = new LinkedHashMap<K, V>();
	    for (Map.Entry<K, V> entry : list)
	    {
	        result.put( entry.getKey(), entry.getValue() );
	    }
	    return result;
	}
}
