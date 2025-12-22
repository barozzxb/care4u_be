package vn.care4u.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TodayAppointmentDTO {
    private Long id;
    private String time;
    private String patientName;
    private String status;
}
