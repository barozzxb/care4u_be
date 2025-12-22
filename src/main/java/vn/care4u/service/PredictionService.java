package vn.care4u.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import vn.care4u.model.dto.PredictionDTO;

public interface PredictionService {

	void deletePrediction(Long id);

	void savePrediction(String email, PredictionDTO prediction);

	Page<PredictionDTO> getPredictionByPatientId(Long patientId, Pageable pageable);

	Page<PredictionDTO> getPredictionByEmail(String email, Pageable pageable);

}
