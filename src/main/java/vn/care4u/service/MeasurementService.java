package vn.care4u.service;

import vn.care4u.entity.Measurement;
import vn.care4u.model.dto.MeasurementUpdateDTO;

public interface MeasurementService {

    Measurement updateMeasurement(String email, MeasurementUpdateDTO dto);

    Measurement getLatestMeasurement(String email);
}
