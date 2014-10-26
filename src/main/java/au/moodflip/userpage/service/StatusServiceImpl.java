package au.moodflip.userpage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.userpage.dao.StatusDao;
import au.moodflip.userpage.model.Status;

@Service
@Transactional
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusDao statusDao;
	
	@Override
	public void saveStatus(Status status) {
		status.setSubmitDate(new Date());
		statusDao.addStatus(status);
	}

	@Override
	public void editStatus(Status status) {
		status.setSubmitDate(new Date());
		statusDao.editStatus(status);
	}

	@Override
	public void removeStatus(Long id) {
		statusDao.removeStatus(id);
	}
	
	@Override
	public List<Status> listStatus() {
		 ArrayList<Status> list = (ArrayList<Status>) statusDao.listStatus();
		 Collections.reverse((List<?>) list);
		 return list;
	}
	
	@Override
	public List<Status> listStatusByUserId(Long userId) {
		 ArrayList<Status> list = (ArrayList<Status>) statusDao.listStatusByUserId(userId);
		 Collections.reverse((List<?>) list);
		 return list;
	}

	@Override
	public List<Status> listStatusOfOtherUser(Long userId) {
		 ArrayList<Status> list = (ArrayList<Status>) statusDao.listStatusOfOtherUser(userId);
		 Collections.reverse((List<?>) list);
		 return list;
	}
	
	@Override
	public Status getStatusById(Long id) {
		return statusDao.getStatusById(id);
	}
}
