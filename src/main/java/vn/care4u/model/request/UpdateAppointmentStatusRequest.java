package vn.care4u.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAppointmentStatusRequest {
    private String action; // APPROVE | REJECT | DONE
}
