package au.moodflip.userpage.service;

import java.util.List;

import au.moodflip.userpage.model.Status;

public interface StatusService {
	
	public void saveStatus(Status status);
	
	public void editStatus(Status status);
	
	public void removeStatus(Long id);

	public List<Status> listStatus();
	
	public List<Status> listStatusByUserId(Long userId);
	
	public List<Status> listStatusOfOtherUser(Long userId);
	
	public Status getStatusById(Long id);
}
