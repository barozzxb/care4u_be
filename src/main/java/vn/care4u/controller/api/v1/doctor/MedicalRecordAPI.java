package vn.care4u.controller.api.v1.doctor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.care4u.entity.MedicalRecord;
import vn.care4u.enumeration.ErrorCode;
import vn.care4u.exception.GeneralException;
import vn.care4u.model.dto.MedicalRecordDetailDTO;
import vn.care4u.model.request.CreateMedicalRecordRequest; // Đảm bảo bạn đã tạo class Request này
import vn.care4u.repository.MeasurementRepository;
import vn.care4u.repository.MedicalRecordRepository;
import vn.care4u.service.CurrentUserService;
import vn.care4u.service.DoctorUsecaseService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor/medical-records")
@RequiredArgsConstructor
@Slf4j
@PreAuthorize("hasAuthority('ROLE_DOCTOR')")
public class MedicalRecordAPI {

    private final DoctorUsecaseService svc;
    private final CurrentUserService currentUser;
    private final MedicalRecordRepository recordRepo;
    private final MeasurementRepository measurementRepo;


    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateMedicalRecordRequest req) {
        log.info("POST /doctor/medical-records body={}", req);

        var createdRecord = svc.createMedicalRecord(req);

        return ResponseEntity.ok(createdRecord);
    }

    @GetMapping
    public ResponseEntity<?> list(@RequestParam(required = false) Long patientId) {

        Long doctorId = currentUser.currentDoctorId();

        List<MedicalRecord> records;

        if (patientId != null) {
            records = recordRepo.findByPatientIdOrderByCreatedAtDesc(patientId);
        } else {
            records = recordRepo.findByDoctorIdOrderByCreatedAtDesc(doctorId);
        }

        var dtoList = records.stream()
                .map(r -> svc.toDTO(r))
                .toList();

        return ResponseEntity.ok(dtoList);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) {

        var r = recordRepo.findById(id)
                .orElseThrow(() -> new GeneralException(ErrorCode.NOT_FOUND));

        var m = measurementRepo.findByMedicalRecordId(r.getId())
                .orElse(null);

        var dto = new MedicalRecordDetailDTO();

        dto.setId(r.getId());
        dto.setCreatedAt(r.getCreatedAt());
        dto.setSymptoms(r.getSymptoms());
        dto.setPhysicalExam(r.getPhysicalExam());
        dto.setDiagnosis(r.getDiagnosis());
        dto.setConclusion(r.getConclusion());
        dto.setTreatment(r.getTreatment());
        dto.setAdvice(r.getAdvice());
        dto.setNotes(r.getNotes());

        // patient
        dto.setPatientId(r.getPatient().getId());
        dto.setPatientFirstname(r.getPatient().getFirstname());
        dto.setPatientLastname(r.getPatient().getLastname());
        dto.setPatientAvatar(r.getPatient().getAvatar());

        // measurement
        if (m != null) {
            dto.setSystolicBP(m.getSystolicBloodPressure());
            dto.setDiastolicBP(m.getDiastolicBloodPressure());
            dto.setTemperature(m.getTemperature());
            dto.setHeartRate(m.getHeartRate());
            dto.setRespiratoryRate(m.getRespiratoryRate());
            dto.setSpo2(m.getSpo2());
            dto.setHeight(m.getHeight());
            dto.setWeight(m.getWeight());
            dto.setBmi(m.getBmi());
        }

        return ResponseEntity.ok(dto);
    }

}