package vn.care4u.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import vn.care4u.enumeration.EStatus;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    
    @Column(columnDefinition = "date")
    private LocalDate date;
    
    @Column(columnDefinition = "time")
    private LocalTime time;
    
    @Column(columnDefinition = "nvarchar(255)")
    private String place;
    
    @Enumerated(EnumType.STRING)
    private EStatus status;
    
    @Column(columnDefinition = "nvarchar(255)")
    private String notes;
}