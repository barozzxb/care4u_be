package vn.care4u.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "department")
public class Department implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id", length = 36)
	private String id; //Id is a combination of the first letter of each word in the department name and a random 4-digit number. For example, "Human Resources" could be "HR1234"
	
	@Column(name = "name", columnDefinition = "nvarchar(100)")
	private String name;
	
	@Column(name = "description", columnDefinition = "nvarchar(255)")
	private String description;

}
