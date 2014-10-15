package au.moodflip.userpage.service;

import java.util.List;

import au.moodflip.userpage.model.StatusComment;

public interface StatusCommentService {
	
	public void addComment(StatusComment statusComment);

	public List<StatusComment> listStatusComment();
	
	public List<StatusComment> listStatusComment(Long statusId);
	
	public StatusComment getCommentById(Long id);

}
