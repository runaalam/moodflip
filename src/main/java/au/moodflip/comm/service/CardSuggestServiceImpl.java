package au.moodflip.comm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.comm.dao.CardSuggestDao;
import au.moodflip.comm.model.CardSuggest;

@Service
@Transactional
public class CardSuggestServiceImpl implements CardSuggestService {
	
	@Autowired
	private CardSuggestDao cardSuggestDao;

	@Override
	public void addCardSuggest(CardSuggest card) {
		cardSuggestDao.addCardSuggest(card);
	}

	@Override
	public List<CardSuggest> listSuggestedCards() {
		return cardSuggestDao.listSuggestedCards();
	}

	@Override
	public List<CardSuggest> listSuggestedCardsByTopicId(Long topicId) {
		return cardSuggestDao.listSuggestedCardsByTopicId(topicId);
	}

	@Override
	public CardSuggest getCardSuggestById(Long id) {
		return cardSuggestDao.getCardSuggestById(id);
	}

	@Override
	public void editCardSuggest(CardSuggest card) {
		cardSuggestDao.editCardSuggest(card);
	}

	@Override
	public void removeCardSuggest(Long id) {
		cardSuggestDao.removeCardSuggest(id);
	}

}
