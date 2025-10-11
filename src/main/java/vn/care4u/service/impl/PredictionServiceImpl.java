package vn.care4u.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.care4u.entity.Patient;
import vn.care4u.entity.Prediction;
import vn.care4u.enumeration.ErrorCode;
import vn.care4u.exception.GeneralException;
import vn.care4u.model.dto.PredictionDTO;
import vn.care4u.repository.PatientRepository;
import vn.care4u.repository.PredictionRepository;
import vn.care4u.service.PatientService;
import vn.care4u.service.PredictionService;

@Service
public class PredictionServiceImpl implements PredictionService {

	@Autowired
	PredictionRepository predRepo;
	
	@Autowired
	PatientRepository patientRepo;

	@Override
	public List<PredictionDTO> getPredictionByPatientId(Long patientId) {
		List<Prediction> predictions = predRepo.findByPatientId(patientId);
		return predictions.stream().map(this::convertToResponse).toList();
	}

	@Override
	public void savePrediction(Long patientId, PredictionDTO prediction) {
		try {
			Prediction pred = new Prediction();
			Patient patient = patientRepo.findById(patientId)
					.orElseThrow(() -> new GeneralException(ErrorCode.PATIENT_NOT_FOUND));
			pred.setDatetime(prediction.getDatetime());
			pred.setPrediction(prediction.getPrediction());
			pred.setSymptoms(prediction.getSymptoms());
			pred.setPatient(patient);
			predRepo.save(pred);
		} catch (Exception e) {
			throw new GeneralException(ErrorCode.UNEXPECTED_ERROR);
		}
	}
	
	@Override
	public void deletePrediction(Long id) {
		try {
			predRepo.deleteById(id);
		} catch (Exception e) {
			throw new GeneralException(ErrorCode.UNEXPECTED_ERROR);
		}
	}

	private PredictionDTO convertToResponse(Prediction p) {
		return PredictionDTO.builder().datetime(p.getDatetime()).prediction(p.getPrediction()).symptoms(p.getSymptoms())
				.build();
	}

}
