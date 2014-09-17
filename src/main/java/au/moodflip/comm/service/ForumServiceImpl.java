package au.moodflip.comm.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.comm.dao.ForumDao;
import au.moodflip.comm.model.Forum;

@Service
public class ForumServiceImpl implements ForumService {

	@Autowired
    private ForumDao forumDao;
	
	@Transactional
	public void addForum(Forum forum) {
		forumDao.addForum(forum);
	}

	@Transactional
	public List<Forum> listForum() {
		return forumDao.listForum();
	}
	
	@Transactional
	public Forum getForumById(Long id) {
		return forumDao.getForumById(id);
	}

	@Transactional
	public void editForum(Forum forum){
		forumDao.editForum(forum);
	}
	
	@Transactional
	public void removeForum(Long id) {
		forumDao.removeForum(id);
	}

}
