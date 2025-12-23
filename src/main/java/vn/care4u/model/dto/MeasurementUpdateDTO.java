package vn.care4u.model.dto;

import lombok.Data;

@Data
public class MeasurementUpdateDTO {
    private double height;
    private double weight;
    private int heartRate;
    private String bloodPressure;
    private Double temperature;
}
