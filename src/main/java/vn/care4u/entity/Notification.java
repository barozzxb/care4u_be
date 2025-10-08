package vn.care4u.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type", columnDefinition = "nvarchar(50)")
	private ENotiType type;
	
	@Column(name = "is_read", columnDefinition = "bit")
	private Boolean isRead;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender", referencedColumnName = "email")	
	private Account sender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver", referencedColumnName = "email")	
	private Account receiver;
	
	@PrePersist
	protected void onCreate() {
		receivedAt = new Timestamp(System.currentTimeMillis());
		if(isRead == null) {
			isRead = false;
		}
	}
}
