package au.moodflip.comm.dao;

import java.util.List;

import au.moodflip.comm.model.Forum;

public interface ForumDao {

	public void addForum(Forum forum);

	public List<Forum> listForum();
	
	public Forum getForumById(Long id);
	
	public void editForum(Forum forum);
	
	public void removeForum(Long id);

}
