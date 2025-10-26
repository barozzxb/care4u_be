package vn.care4u.service;

import java.util.List;

import vn.care4u.model.dto.PredictionDTO;

public interface PredictionService {

	void deletePrediction(Long id);

	void savePrediction(Long patientId, PredictionDTO prediction);

	List<PredictionDTO> getPredictionByPatientId(Long patientId);

}
