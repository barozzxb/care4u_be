package vn.care4u.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "measurements")
public class Measurement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "double")
    private double height;

    @Column(columnDefinition = "double")
    private double weight;

    @Column(columnDefinition = "double")
    private double bmi;

    @Column(columnDefinition = "nvarchar(255)")
    private String healthStatus;

    @Column
    private Double heartRate;

    @Column(columnDefinition = "nvarchar(255)")
    private String bloodPressure;

    @Column
    private Double temperature;

    @Column
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
