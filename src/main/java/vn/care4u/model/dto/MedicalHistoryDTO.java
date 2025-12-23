package vn.care4u.model.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalHistoryDTO {

    private Long id;
    private LocalDateTime createdAt;
    private String diagnosis;
    private String treatment;
    private String notes;
    private String doctorName;

    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
}
