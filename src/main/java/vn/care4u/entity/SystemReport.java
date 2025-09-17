package vn.care4u.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = "systemreport")
public class SystemReport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "createdat", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp createdAt;
	
	@Column(name="data",columnDefinition = "TEXT")
	private String data;
	
	@Column(name="chart",columnDefinition = "TEXT")
	private String chart;
	
	@Column(name="summary",columnDefinition = "TEXT")
	private String summary;
	
	@Column(name="totaluserdes",columnDefinition = "VARCHAR(255)")
	private String totalUserDes;
}
