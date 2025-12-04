package vn.care4u.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientUpdateDTO {
    
    private String firstname;
    private String lastname;
    private String phonenum;
    private String dob;
    private String idNumber;
    private String gender;
    
    private String insurance;
    private String relativePhone;
    private String province;
    private String district;
    private String ward;
    private String ethnic;
    private String referralCode;
    
    private String avatar;

}
