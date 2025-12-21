package vn.care4u.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "department")
public class Department implements Serializable {

<<<<<<< Updated upstream
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
	
	@Column(name = "createdAt", columnDefinition = "timestamp")
	private Timestamp createdAt;
=======
    private static final long serialVersionUID = 1L;
>>>>>>> Stashed changes

    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "name", columnDefinition = "nvarchar(100)")
    private String name;

    @Column(name = "description", columnDefinition = "nvarchar(255)")
    private String description;

    @Column(name = "icon", length = 50)
    private String icon;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Doctor> doctors;
}
