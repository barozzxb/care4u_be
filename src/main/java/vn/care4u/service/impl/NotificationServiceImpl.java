package vn.care4u.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.care4u.entity.Account;
import vn.care4u.entity.Notification;
import vn.care4u.enumeration.ErrorCode;
import vn.care4u.exception.GeneralException;
import vn.care4u.model.response.NotificationResponse;
import vn.care4u.repository.AccountRepository;
import vn.care4u.repository.NotificationRepository;
import vn.care4u.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService{
	
	@Autowired
	private NotificationRepository notiRepo;
	
	@Autowired
	private AccountRepository accRepo;
	
	@Override
	public NotificationResponse getNotificationById(Long id) {
		Optional<Notification> noti = notiRepo.findById(id);
		if(noti.isPresent()) {
			return convertToResponse(noti.get());
		} else {
			throw new GeneralException(ErrorCode.NOTIFICATION_NOT_FOUND);
		}
	}
	
	@Override
	public List<NotificationResponse> getAllNotificationsOfUser(String email) {
		Account acc = accRepo.findById(email)
				.orElseThrow(() -> new GeneralException(ErrorCode.ACCOUNT_NOT_FOUND));
		List<Notification> notis = notiRepo.findByReceiver(acc);
		return notis.stream().map(this::convertToResponse).collect(Collectors.toList());
	}
	
	private NotificationResponse convertToResponse(Notification n) {
		return NotificationResponse.builder()
				.id(n.getId())
				.receivedAt(n.getReceivedAt())
				.title(n.getTitle())
				.content(n.getContent())
				.type(n.getType().name())
				.isRead(n.getIsRead())
				.sender(n.getSender().getEmail())
				.receiver(n.getReceiver().getEmail())
				.build();
	}
}
