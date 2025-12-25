package vn.care4u.model.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class DoctorProfileDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenum;
    private String gender;
    private LocalDate dob;

    private String address;
    private String avatar;
    private String bio;
    private String education;
    private String experience;
    private String certification;
    private String workinghour;

    private Long departmentId;
    private String departmentName;
}
