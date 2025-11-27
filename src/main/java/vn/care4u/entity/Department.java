package vn.care4u.entity;

import java.io.Serializable;

<<<<<<< Updated upstream
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
=======
import jakarta.persistence.*;
import lombok.*;
>>>>>>> Stashed changes

@AllArgsConstructor
@NoArgsConstructor
@Data
<<<<<<< Updated upstream

=======
@Builder
>>>>>>> Stashed changes
@Entity
@Table(name = "department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

<<<<<<< Updated upstream
=======
    @Id
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "name", columnDefinition = "nvarchar(100)")
    private String name;

    @Column(name = "description", columnDefinition = "nvarchar(255)")
    private String description;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Doctor> doctors;
>>>>>>> Stashed changes
}
