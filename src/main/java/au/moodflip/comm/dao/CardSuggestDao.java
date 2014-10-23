package au.moodflip.comm.dao;

import java.util.List;

import au.moodflip.comm.model.CardSuggest;
import au.moodflip.comm.model.Topic;

public interface CardSuggestDao {

	public void addCardSuggest(CardSuggest card);

	public List<CardSuggest> listSuggestedCards();
	
	public List<CardSuggest> listSuggestedCardsByTopicId(Long topicId);
	
	public CardSuggest getCardSuggestById(Long id);
	
	public void editCardSuggest(CardSuggest card);
	
	public void removeCardSuggest(Long id);

}
