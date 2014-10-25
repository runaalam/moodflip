package au.moodflip.userpage.dao;

import java.util.List;

import au.moodflip.userpage.model.Status;

public interface StatusDao {
	
	public void addStatus(Status status);
	
	public void editStatus(Status status);
	
	public void removeStatus(Long id);

	public List<Status> listStatus();
	
	public List<Status> listStatusByUserId(Long userId);
	
	public List<Status> listStatusOfOtherUser(Long userId);
	
	public Status getStatusById(Long id);

}
