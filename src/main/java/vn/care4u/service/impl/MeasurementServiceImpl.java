package vn.care4u.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.care4u.entity.Measurement;
import vn.care4u.entity.Patient;
import vn.care4u.model.dto.MeasurementUpdateDTO;
import vn.care4u.repository.MeasurementRepository;
import vn.care4u.service.MeasurementService;
import vn.care4u.service.PatientService;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class MeasurementServiceImpl implements MeasurementService {

    @Autowired
    private MeasurementRepository measurementRepo;

    @Autowired
    private PatientService patientService;

    @Override
    public Measurement updateMeasurement(String email, MeasurementUpdateDTO dto) {
        Patient patient = patientService.getPatientById(email);

        double height = dto.getHeight();
        double weight = dto.getWeight();
        double bmi = weight / (height * height);
        String healthStatus = calculateHealthStatus(bmi);

        Measurement measurement = new Measurement();
        measurement.setHeight(height);
        measurement.setWeight(weight);
        measurement.setBmi(bmi);
        measurement.setHealthStatus(healthStatus);
        measurement.setHeartRate(dto.getHeartRate());
        measurement.setBloodPressure(dto.getBloodPressure());
        measurement.setTemperature(dto.getTemperature());
        measurement.setTime(LocalDateTime.now());
        measurement.setPatient(patient);

        return measurementRepo.save(measurement);
    }

    @Override
    public Measurement getLatestMeasurement(String email) {
        Patient patient = patientService.getPatientById(email);
        return measurementRepo.findByPatientIdOrderByTimeDesc(patient.getId()).getFirst();
    }

    private String calculateHealthStatus(double bmi) {
        if (bmi < 18.5) return "Gầy";
        else if (bmi < 24.9) return "Bình thường";
        else if (bmi < 29.9) return "Thừa cân";
        else return "Béo phì";
    }
}