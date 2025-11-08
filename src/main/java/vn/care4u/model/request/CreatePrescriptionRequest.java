package vn.care4u.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreatePrescriptionRequest {
    private Long patientId;
    private List<Item> items;

    @Getter
    @Setter
    public static class Item {
        private Long drugId;
        private String name;
        private String dose;
        private Integer quantity;
        private String note;
    }
}
