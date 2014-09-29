package au.moodflip.comm.service;

import java.util.Date;
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
	public PrivateMessage createPrivateMessage(String message, Long senderId, Long receiverId) {
		PrivateMessage pm = new PrivateMessage();
		pm.setContent(message);
		pm.setSenderId(senderId);
		pm.setReceiverId(receiverId);
		pm.setCreatedAt(new Date());
		createPrivateMessage(pm);
		return pm;
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
	public List<PrivateMessage> listPrivateMessageBySenderAndReceiverId(Long userId_1, Long userId_2) {
		return privateMessageDao.listPrivateMessageBySenderAndReceiverId(userId_1, userId_2);
	}
	
	@Transactional
	public List<PrivateMessage> updatePrivateMessageBySenderAndReceiverId(Long userId_1, Long userId_2, Long msgId) {
		return privateMessageDao.updatePrivateMessageBySenderAndReceiverId(userId_1, userId_2, msgId);
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
