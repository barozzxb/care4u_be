package vn.care4u.model.request;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import vn.care4u.entity.Account;
import vn.care4u.enumeration.ENotiType;

@AllArgsConstructor

@Getter
@Setter
public class NotificationRequest {

	private String title;
	private String content;
	private ENotiType type;
	private Account receiver;
}
