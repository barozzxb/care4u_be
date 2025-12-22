package vn.care4u.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CreateAppointmentRequest {

    @NotNull
    private Long patientId;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    @JsonFormat(pattern = "HH:mm")
    private LocalTime time;

    @NotBlank
    private String place;

    private String notes;
}
