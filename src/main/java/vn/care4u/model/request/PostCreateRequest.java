package vn.care4u.model.request;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostCreateRequest {
	private Long id;

	private Timestamp created;

	private Timestamp updated;

	private String title;

	private String type;

	private String content;
	
	private String account_email;
}
