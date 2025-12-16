package vn.care4u.model.response;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.care4u.enumeration.ENotiType;

@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
@Builder
public class NotificationResponse {

	private Long id;
	private Timestamp receivedAt;
	private String title;
	private String content;
	private String type;
	private Boolean isRead;
	private String sender;
	private String receiver;
}
