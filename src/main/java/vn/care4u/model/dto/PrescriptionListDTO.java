package vn.care4u.model.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PrescriptionListDTO {
    private Long id;
    private LocalDateTime createdAt;

    private Long patientId;
    private String patientName;

    private Integer itemsCount;
    private List<PrescriptionItemDTO> itemsPreview;
}
