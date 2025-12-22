package vn.care4u.model.dto;

import lombok.Data;

@Data
public class AppointmentRequestDTO {
    private Long doctorId;
    private String date;
    private String time;
    private String place;
    private String notes;
}
