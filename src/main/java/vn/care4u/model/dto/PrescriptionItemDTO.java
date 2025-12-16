package vn.care4u.model.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PrescriptionItemDTO {
    private Long id;
    private Long drugId;
    private String name;
    private String dose;
    private Integer quantity;
    private String note;
}
