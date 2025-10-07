package vn.care4u.controller.api.v1.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import vn.care4u.model.response.ApiResponse;
import vn.care4u.model.response.NotificationResponse;
import vn.care4u.service.NotificationService;

@RestController
@RequestMapping("/api/v1/notification")
@Tag(name = "Notification", description = "Notification API")
public class NotificationAPI {

	@Autowired
	private NotificationService notiServ;
	
	@GetMapping("/")
	public ApiResponse<NotificationResponse> getNotificationById(@RequestBody Long id) {
		NotificationResponse noti = notiServ.getNotificationById(id);
		return ApiResponse.<NotificationResponse>builder()
				.status(200)
				.message("Lấy thông báo thành công")
				.body(noti)
				.build();
	}
	
	@GetMapping("/receiver")
	public ApiResponse<Iterable<NotificationResponse>> getAllNotificationsOfUser(@RequestParam String email) {
		Iterable<NotificationResponse> notis = notiServ.getAllNotificationsOfUser(email);
		return ApiResponse.<Iterable<NotificationResponse>>builder()
				.status(200)
				.message("Lấy danh sách thông báo thành công")
				.body(notis)
				.build();
	}
}
