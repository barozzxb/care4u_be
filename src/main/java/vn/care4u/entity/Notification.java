package vn.care4u.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.care4u.enumeration.ENotiType;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "notifications")
public class Notification implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "title", columnDefinition = "nvarchar(255)")
	private String title;
	
	@Column(name = "received_at", columnDefinition = "timestamp")
	private Timestamp receivedAt;
	
	@Column(name = "content", columnDefinition = "nvarchar(1000)")
	private String content;
	
	@Enumerated
	@Column(name = "type", columnDefinition = "nvarchar(50)")
	private ENotiType type;
	
	@Column(name = "is_read", columnDefinition = "bit")
	private Boolean isRead;

}
