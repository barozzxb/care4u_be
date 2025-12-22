package vn.care4u.model.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UpdateDoctorProfileRequest {
    private String firstname;
    private String lastname;
    private String phonenum;
    private String gender;
    private LocalDate dob;

    private String address;
    private String bio;
    private String education;
    private String experience;
    private String certification;
    private String workinghour;
}
