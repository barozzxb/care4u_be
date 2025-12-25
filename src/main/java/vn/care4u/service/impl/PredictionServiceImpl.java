package vn.care4u.service.impl;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.care4u.entity.Patient;
import vn.care4u.entity.Prediction;
import vn.care4u.enumeration.ErrorCode;
import vn.care4u.exception.GeneralException;
import vn.care4u.model.dto.PredictionDTO;
import vn.care4u.repository.PatientRepository;
import vn.care4u.repository.PredictionRepository;
import vn.care4u.service.PredictionService;

@Service
public class PredictionServiceImpl implements PredictionService {

	@Autowired
	PredictionRepository predRepo;
	
	@Autowired
	PatientRepository patientRepo;

	@Override
	public Page<PredictionDTO> getPredictionByPatientId(Long patientId, Pageable pageable) {
		return predRepo.findByPatientId(patientId, pageable).map(this::convertToResponse);
	}
	
	@Override
	public Page<PredictionDTO> getPredictionByEmail(String email, Pageable pageable) {
		Patient p = patientRepo.findByAccount_Email(email).orElseThrow(() -> new GeneralException(ErrorCode.INVALID_CREDENTIALS));
		return predRepo.findByPatientId(p.getId(), pageable).map(this::convertToResponse);
	}

	@Override
	public void savePrediction(String patientId, PredictionDTO prediction) {
		try {
			System.out.println(prediction.getPrediction());
			System.out.println(prediction.getSymptoms());
			
			Prediction pred = new Prediction();
			Patient patient = patientRepo.findByAccount_Email(patientId)
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
		return PredictionDTO.builder().id(p.getId()).datetime(p.getDatetime()).prediction(p.getPrediction()).symptoms(p.getSymptoms())
				.build();
	}

}
