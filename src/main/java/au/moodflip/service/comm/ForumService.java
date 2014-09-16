package au.moodflip.service.comm;

import java.util.List;

import au.moodflip.model.comm.Forum;

public interface ForumService {

	public void addForum(Forum forum);

	public List<Forum> listForum();
	
	public Forum getForumById(Long id);

	public void editForum(Forum forum);

	public void removeForum(Long id);

}
