package vn.care4u.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.care4u.entity.Patient;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDTO {
    
    private Long id;
    private String firstname;
    private String lastname;
    private String phonenum;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    
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
    private String email;
    private String accountEmail;
    

    public static PatientResponseDTO fromEntity(Patient patient) {
        if (patient == null) {
            return null;
        }
        
        return PatientResponseDTO.builder()
                .id(patient.getId())
                .firstname(patient.getFirstname())
                .lastname(patient.getLastname())
                .phonenum(patient.getPhonenum())
                .dob(patient.getDob())
                .idNumber(patient.getIdNumber())
                .gender(patient.getGender())
                .insurance(patient.getInsurance())
                .relativePhone(patient.getRelativePhone())
                .province(patient.getProvince())
                .district(patient.getDistrict())
                .ward(patient.getWard())
                .ethnic(patient.getEthnic())
                .referralCode(patient.getReferralCode())
                .avatar(patient.getAvatar())
                .email(patient.getEmail())
                .accountEmail(patient.getAccount() != null ? patient.getAccount().getEmail() : null)
                .build();
    }
}