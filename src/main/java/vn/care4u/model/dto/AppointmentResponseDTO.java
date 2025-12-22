package vn.care4u.model.dto;

import lombok.Data;
import vn.care4u.enumeration.EStatus;

@Data
public class AppointmentResponseDTO {
    private Long id;
    private Long doctorId;
    private Long patientId;
    private String doctorName;
    private String date;
    private String time;
    private String place;
    private String notes;
    private EStatus status;
}
