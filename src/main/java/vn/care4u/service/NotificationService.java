package vn.care4u.service;

import java.util.List;

import org.springframework.data.domain.Page;

import vn.care4u.model.request.NotificationRequest;
import vn.care4u.model.response.NotificationResponse;

public interface NotificationService {

//	List<NotificationResponse> getAllNotificationsOfUser(String email);

	NotificationResponse getNotificationById(Long id);

	Page<NotificationResponse> getAllNotifications(String email, int page, int size);

	List<NotificationResponse> getRecentNotifications(String email);

	void markAsRead(Long id, String email);

	void sendNotification(NotificationRequest req, String senderEmail);

}
