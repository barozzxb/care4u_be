package vn.care4u.service;

import java.util.List;

import vn.care4u.model.response.NotificationResponse;

public interface NotificationService {

	List<NotificationResponse> getAllNotificationsOfUser(String email);

	NotificationResponse getNotificationById(Long id);

}
