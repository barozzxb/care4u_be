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
public class DepartmentDTO {

	private String id;
	private String name;
	private String description;
	private List<DoctorDTO> doctors;
	
}
