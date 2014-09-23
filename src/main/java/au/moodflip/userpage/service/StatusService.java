package au.moodflip.userpage.service;

import java.util.List;

import au.moodflip.userpage.model.Status;

public interface StatusService {
	
	public void addStatus(Status status);

	public List<Status> listStatus();
	
	public Status getStatusById(Long id);
}
