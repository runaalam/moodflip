package au.moodflip.comm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.comm.dao.PrivateMessageDao;
import au.moodflip.comm.model.PrivateMessage;

@Service
public class PrivateMessageServiceImpl implements PrivateMessageService {

	@Autowired
    private PrivateMessageDao privateMessageDao;
	
	@Transactional
	public void createPrivateMessage(PrivateMessage pMessage) {
		privateMessageDao.createPrivateMessage(pMessage);
	}

	@Transactional
	public List<PrivateMessage> listPrivateMessage() {
		return privateMessageDao.listPrivateMessage();
	}

	@Transactional
	public List<PrivateMessage> listPrivateMessageBySenderId(Long senderId) {
		return privateMessageDao.listPrivateMessageBySenderId(senderId);
	}

	@Transactional
	public List<PrivateMessage> listPrivateMessageByReceiverId(Long receiverId) {
		return privateMessageDao.listPrivateMessageByReceiverId(receiverId);
	}

	@Transactional
	public List<PrivateMessage> listPrivateMessageByUserId(Long userId) {
		return privateMessageDao.listPrivateMessageByUserId(userId);
	}

	@Transactional
	public PrivateMessage getPrivateMessageById(Long id) {
		return privateMessageDao.getPrivateMessageById(id);
	}

	@Transactional
	public void editPrivateMessage(PrivateMessage pMessage) {
		privateMessageDao.editPrivateMessage(pMessage);
	}

	@Transactional
	public void removePrivateMessage(Long id) {
		privateMessageDao.removePrivateMessage(id);
	}

}
