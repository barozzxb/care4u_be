package vn.care4u.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "drug")
public class Drug implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "drugname", columnDefinition = "nvarchar(255)")
	private String drugname;
	
	@Column(name = "origin", columnDefinition = "nvarchar(255)")
	private String origin;
	
	@Column(name = "dusage", columnDefinition = "text")
	private String usage;
	
	@Column(name = "dfunction", columnDefinition = "text")
	private String function;
	
	@Column(name = "otherinfo", columnDefinition = "text")
	private String otherinfo;
}
