package vn.care4u.controller.api.v1.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.care4u.entity.Patient;
import vn.care4u.model.dto.PatientUpdateDTO;
import vn.care4u.service.PatientService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getPatientInfo(@RequestParam String email) {
        Map<String, Object> response = new HashMap<>();

        try {
            Patient patient = patientService.getPatientById(email);

            Map<String, Object> patientData = new HashMap<>();
            patientData.put("id", patient.getId());
            patientData.put("firstname", patient.getFirstname());
            patientData.put("lastname", patient.getLastname());
            patientData.put("phonenum", patient.getPhonenum());
            patientData.put("dob", patient.getDob());
            patientData.put("idNumber", patient.getIdNumber());
            patientData.put("gender", patient.getGender());
            patientData.put("email", patient.getEmail());
            patientData.put("insurance", patient.getInsurance());
            patientData.put("relativePhone", patient.getRelativePhone());
            patientData.put("province", patient.getProvince());
            patientData.put("district", patient.getDistrict());
            patientData.put("ward", patient.getWard());
            patientData.put("ethnic", patient.getEthnic());
            patientData.put("referralCode", patient.getReferralCode());
            patientData.put("avatar", patient.getAvatar());

            response.put("success", true);
            response.put("data", patientData);
            response.put("message", "Lấy thông tin thành công");

            return ResponseEntity.ok()
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .body(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Không tìm thấy bệnh nhân: " + e.getMessage());
            response.put("data", null);

            return ResponseEntity.status(404)
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .body(response);
        }
    }

    @PutMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<Map<String, Object>> updatePatientInfoJson(
            @RequestParam String email,
            @RequestBody PatientUpdateDTO dto
    ) {
        Map<String, Object> response = new HashMap<>();

        try {
            patientService.updatePatientInfo(email, dto);

            response.put("success", true);
            response.put("message", "Cập nhật thông tin thành công!");
            response.put("avatarUrl", null);

            return ResponseEntity.ok()
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .body(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Cập nhật thất bại: " + e.getMessage());

            return ResponseEntity.status(400)
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .body(response);
        }
    }

    @PutMapping(value = "/update", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, Object>> updatePatientInfoWithAvatar(
            @RequestParam String email,
            @RequestPart(required = false) MultipartFile avatar,
            @RequestPart(required = false) String firstname,
            @RequestPart(required = false) String lastname,
            @RequestPart(required = false) String phonenum,
            @RequestPart(required = false) String dob,
            @RequestPart(required = false) String idNumber,
            @RequestPart(required = false) String gender,
            @RequestPart(required = false) String insurance,
            @RequestPart(required = false) String province,
            @RequestPart(required = false) String district,
            @RequestPart(required = false) String ward,
            @RequestPart(required = false) String ethnic,
            @RequestPart(required = false) String referralCode
    ) {
        Map<String, Object> response = new HashMap<>();

        try {
            PatientUpdateDTO dto = new PatientUpdateDTO();
            dto.setFirstname(firstname);
            dto.setLastname(lastname);
            dto.setPhonenum(phonenum);
            dto.setDob(dob);
            dto.setIdNumber(idNumber);
            dto.setGender(gender);
            dto.setInsurance(insurance);
            dto.setProvince(province);
            dto.setDistrict(district);
            dto.setWard(ward);
            dto.setEthnic(ethnic);
            dto.setReferralCode(referralCode);

            Patient patient = patientService.updatePatientInfoWithAvatar(email, dto, avatar);

            response.put("success", true);
            response.put("message", "Cập nhật thông tin thành công!");
            response.put("avatarUrl", patient.getAvatar());

            return ResponseEntity.ok()
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .body(response);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Cập nhật thất bại: " + e.getMessage());
            response.put("avatarUrl", null);

            return ResponseEntity.status(400)
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .body(response);
        }
    }
}