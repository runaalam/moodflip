package au.moodflip.personalisation.service;

import java.io.Serializable;
import java.util.List;

import au.moodflip.personalisation.model.Filter;
import au.moodflip.comm.model.TopicComment;

public interface SearchFilter extends Serializable{
 
    
    public void filterComments(String username);
   
    public List<TopicComment> getFilteredComments();
    public void deleteWord(String word);
    public void addWord(String word);
    
}