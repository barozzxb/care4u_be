package vn.care4u.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMedicalRecordRequest {
    private Long patientId;
    private String diagnosis;
    private String symptoms;
    private String notes;
}
