package au.moodflip.userpage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.userpage.dao.StatusDao;
import au.moodflip.userpage.model.Status;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusDao statusDao;
	
	@Transactional
	public void saveStatus(Status status) {
		status.setSubmitDate(new Date());
		statusDao.addStatus(status);
	}

	@Transactional
	public List<Status> listStatus() {
		 ArrayList<Status> list = (ArrayList<Status>) statusDao.listStatus();
		 Collections.reverse((List<?>) list);
		 return list;
	}

	@Transactional
	public Status getStatusById(Long id) {
		return statusDao.getStatusById(id);
	}
}
