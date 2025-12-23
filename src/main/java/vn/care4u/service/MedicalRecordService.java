package vn.care4u.service;

import java.util.List;
import vn.care4u.model.dto.MedicalHistoryDTO;

public interface MedicalRecordService {

    List<MedicalHistoryDTO> getAllMedicalRecords();

    MedicalHistoryDTO getMedicalRecordById(Long id);

    MedicalHistoryDTO createMedicalRecord(MedicalHistoryDTO record);
}
