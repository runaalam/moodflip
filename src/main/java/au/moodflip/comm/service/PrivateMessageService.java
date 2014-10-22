package au.moodflip.comm.service;

import java.util.List;

import au.moodflip.comm.model.PrivateMessage;
//
public interface PrivateMessageService {
	
	public void createPrivateMessage(PrivateMessage pMessage);
	
	public PrivateMessage createPrivateMessage(String message, Long senderId, Long receiverId);

	public List<PrivateMessage> listPrivateMessage();
	
	public List<PrivateMessage> listPrivateMessageBySenderId(Long senderId);
	
	public List<PrivateMessage> listPrivateMessageByReceiverId(Long receiverId);
	
	public List<PrivateMessage> listPrivateMessageByUserId(Long userId);
	
	public List<PrivateMessage> listPrivateMessageBySenderAndReceiverId(Long userId_1, Long userId_2);
	
	public List<PrivateMessage> updatePrivateMessageBySenderAndReceiverId(Long userId_1, Long userId_2, Long msgId);
	
	public PrivateMessage getPrivateMessageById(Long id);
	
	public void editPrivateMessage(PrivateMessage pMessage);
	
	public void removePrivateMessage(Long id);

}
