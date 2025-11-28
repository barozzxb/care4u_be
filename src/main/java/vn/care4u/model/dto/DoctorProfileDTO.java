package vn.care4u.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorProfileDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String phonenum;
    private String address;
    private String avatar;
    private String speciality;
    private String gender;
    private String dob;
}
