package vn.care4u.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.care4u.entity.Account;
import vn.care4u.entity.Notification;
import vn.care4u.enumeration.ErrorCode;
import vn.care4u.exception.GeneralException;
import vn.care4u.model.request.NotificationRequest;
import vn.care4u.model.response.NotificationResponse;
import vn.care4u.repository.AccountRepository;
import vn.care4u.repository.NotificationRepository;
import vn.care4u.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationRepository notiRepo;

	@Autowired
	private AccountRepository accRepo;

	@Override
	public NotificationResponse getNotificationById(Long id) {
		Optional<Notification> noti = notiRepo.findById(id);
		if (noti.isPresent()) {
			return convertToResponse(noti.get());
		} else {
			throw new GeneralException(ErrorCode.NOTIFICATION_NOT_FOUND);
		}
	}

	@Override
	public List<NotificationResponse> getRecentNotifications(String email) {
		Account acc = accRepo.findById(email).orElseThrow(() -> new GeneralException(ErrorCode.ACCOUNT_NOT_FOUND));
		Pageable pageable = PageRequest.of(0, 5, Sort.by("receivedAt").descending());
		List<Notification> notis = notiRepo.findByReceiver(acc, pageable);
		return notis.stream().map(this::convertToResponse).collect(Collectors.toList());
	}

	@Override
	public Page<NotificationResponse> getAllNotifications(String email, int page, int size) {
		Account acc = accRepo.findById(email).orElseThrow(() -> new GeneralException(ErrorCode.ACCOUNT_NOT_FOUND));
		Pageable pageable = PageRequest.of(page, size, Sort.by("receivedAt").descending());
		Page<Notification> notiPage = notiRepo.findAllByReceiver(acc, pageable);
		return notiPage.map(this::convertToResponse);
	}

	@Override
	public void markAsRead(Long id, String email) {
		Account acc = accRepo.findById(email).orElseThrow(() -> new GeneralException(ErrorCode.ACCOUNT_NOT_FOUND));
		int updated = notiRepo.markAsRead(id, acc);
		if (updated == 0) {
			throw new GeneralException(ErrorCode.NOTIFICATION_NOT_FOUND);
		}
	}

	private NotificationResponse convertToResponse(Notification n) {
		return NotificationResponse.builder().id(n.getId()).receivedAt(n.getReceivedAt()).title(n.getTitle())
				.content(n.getContent()).type(n.getType().name()).isRead(n.getIsRead()).sender(n.getSender().getEmail())
				.receiver(n.getReceiver().getEmail()).build();
	}

	/* For sending notis */

	@Override
	public void sendNotification(NotificationRequest req, String senderEmail) {
		Notification noti = new Notification();
		Account sender = accRepo.findById(senderEmail)
				.orElseThrow(() -> new GeneralException(ErrorCode.ACCOUNT_NOT_FOUND));
		Account receiver = accRepo.findById(req.getReceiver().getEmail())
				.orElseThrow(() -> new GeneralException(ErrorCode.ACCOUNT_NOT_FOUND));
		try {
			noti.setTitle(req.getTitle());
			noti.setContent(req.getContent());
			noti.setType(req.getType());
			noti.setSender(sender);
			noti.setReceiver(receiver);
			notiRepo.save(noti);
		} catch (Exception e) {
			throw new GeneralException(ErrorCode.UNKNOWN_ERROR);
		}
	}
}
