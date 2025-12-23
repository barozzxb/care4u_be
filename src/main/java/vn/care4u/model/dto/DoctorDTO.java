package vn.care4u.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DoctorDTO {

    private Long id;

    private String firstname;

    private String lastname;
    
    private String address;
    
    private String phonenum;
    private String avatar;

    private String bio;

    private String education;       
    
    private String certification;   

    private String experience;

    private String workinghour;     

    private Integer patients; 
    
    private String gender;

    private Double rating;        

}
