package vn.care4u.model.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PrescriptionDetailDTO {
    private Long id;
    private LocalDateTime createdAt;

    private Long patientId;
    private String patientName;

    private List<PrescriptionItemDTO> items;
}
