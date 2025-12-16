package vn.care4u.controller.api.v1.notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import vn.care4u.model.AccountDetail;
import vn.care4u.model.request.NotificationRequest;
import vn.care4u.model.response.ApiResponse;
import vn.care4u.model.response.NotificationResponse;
import vn.care4u.service.NotificationService;

@RestController
@RequestMapping("/api/v1/notification")
@Tag(name = "Notification", description = "Notification API")
public class NotificationAPI {

	@Autowired
	private NotificationService notiServ;
	
	@Operation(summary = "Get Recent Notis", description = "Lấy danh sách thông báo gần đây")
	@GetMapping("/user-get-recent")
	public ApiResponse<List<NotificationResponse>> getRecentNotifications(
			@AuthenticationPrincipal AccountDetail detail) {
		List<NotificationResponse> notis = notiServ.getRecentNotifications(detail.getUsername());
		return ApiResponse.<List<NotificationResponse>>builder()
				.status(200)
				.message("Lấy danh sách thông báo gần đây thành công")
				.body(notis)
				.build();
	}
	
	@Operation(summary = "Get All Notis", description = "Lấy danh sách tất cả thông báo")
	@GetMapping("/user-get-all")
	public ApiResponse<Page<NotificationResponse>> getAllNotifications(
			@AuthenticationPrincipal AccountDetail detail,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "10") int size) {
		Page<NotificationResponse> notis = notiServ.getAllNotifications(detail.getUsername(), page, size);
		return ApiResponse.<Page<NotificationResponse>>builder()
				.status(200)
				.message("Lấy danh sách tất cả thông báo thành công")
				.body(notis)
				.build();
	}
	
	@Operation(summary = "Mark Noti As Read", description = "Đánh dấu thông báo đã đọc")
	@PatchMapping("/user-mark-as-read")
	public ApiResponse<Void> markAsRead(
			@AuthenticationPrincipal AccountDetail detail,
			@RequestBody Long id) {
		notiServ.markAsRead(id, detail.getUsername());
		return ApiResponse.<Void>builder()
				.status(200)
				.message("Đánh dấu thông báo đã đọc thành công")
				.build();
	}

	@Operation(summary = "Send Noti", description = "Gửi thông báo (dành cho admin/doctor)")
	@PostMapping("/send")
	public ApiResponse<Void> sendNotification(
			@AuthenticationPrincipal AccountDetail detail,
			@RequestBody NotificationRequest request) {
		notiServ.sendNotification(request, detail.getUsername());
		return ApiResponse.<Void>builder()
				.status(200)
				.message("Gửi thông báo thành công")
				.build();
	}
}
