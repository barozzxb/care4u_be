package vn.care4u.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Long id;
    private Long patientId;
    private String patientName;
    private LocalDateTime time;
    private String place;
    private String status;
    private String reason;
}
