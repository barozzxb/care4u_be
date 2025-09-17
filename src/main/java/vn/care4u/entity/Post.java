package vn.care4u.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.care4u.enumeration.EPostType;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "posts")
public class Post implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "created", columnDefinition = "timestamp")
	private String created;
	
	@Column(name = "updated", columnDefinition = "timestamp")
	private String updated;
	
	@Column(name = "title", columnDefinition = "nvarchar(255)")
	private String title;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type", columnDefinition = "nvarchar(255)")
	private EPostType type;
	
	@Column(name = "content", columnDefinition = "text")
	private String content;
	
	@Column(name = "image", columnDefinition = "nvarchar(255)")
	private String image;
}
