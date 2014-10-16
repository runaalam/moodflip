package au.moodflip.userpage.dao;

import java.util.List;

import au.moodflip.userpage.model.Status;
import au.moodflip.userpage.model.StatusComment;

public interface StatusCommentDao {
	
	public void addComment(StatusComment statusComment);

	public List<StatusComment> listStatusComment();
	
	public List<StatusComment> listStatusComment(Long statusId);
	
	public StatusComment getCommentById(Long id);

}
