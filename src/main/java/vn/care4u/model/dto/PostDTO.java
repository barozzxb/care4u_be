package vn.care4u.model.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostDTO {
	private Long id;

	private Timestamp created;

	private Timestamp updated;

	private String title;

	private String type;

	private String content;

	private String image;
	
	private String account_email;
}
