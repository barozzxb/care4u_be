package vn.care4u.model.request;

import lombok.Data;

@Data
public class UpdateAppointmentRequest {
    private String date;
    private String time;
    private String place;
    private String notes;
}
