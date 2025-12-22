package vn.care4u.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.care4u.entity.Patient;
import vn.care4u.model.dto.PatientUpdateDTO;
import vn.care4u.repository.PatientRepository;
import vn.care4u.service.PatientService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private static final Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientRepository patientRepo;

    @Override
    public <S extends Patient> S save(S entity) {
        return patientRepo.save(entity);
    }

    @Override
    public boolean existsById(Long id) {
        return patientRepo.existsById(id);
    }

    @Override
    public Patient getPatientById(String accountEmail) {
        log.info("🔍 Tìm kiếm bệnh nhân với email: {}", accountEmail);
        
        // Thử tìm theo account.email trước
        Optional<Patient> patientByAccount = patientRepo.findByAccount_Email(accountEmail);
        if (patientByAccount.isPresent()) {
            log.info("✅ Tìm thấy bệnh nhân qua account.email");
            return patientByAccount.get();
        }
        
        // Nếu không có, thử tìm theo patient.email
        Optional<Patient> patientByEmail = patientRepo.findByEmail(accountEmail);
        if (patientByEmail.isPresent()) {
            log.info("✅ Tìm thấy bệnh nhân qua patient.email");
            return patientByEmail.get();
        }
        
        log.error("❌ Không tìm thấy bệnh nhân với email: {}", accountEmail);
        throw new RuntimeException("Không tìm thấy bệnh nhân với email: " + accountEmail);
    }

    @Override
    public Patient updatePatientInfo(String accountEmail, PatientUpdateDTO dto) {
        log.info("🔄 Bắt đầu cập nhật thông tin cho email: {}", accountEmail);
        
        // Thử tìm theo account.email trước
        Optional<Patient> patientOpt = patientRepo.findByAccount_Email(accountEmail);
        
        // Nếu không có, thử tìm theo patient.email
        if (!patientOpt.isPresent()) {
            log.info("⚠️ Không tìm thấy qua account.email, thử patient.email");
            patientOpt = patientRepo.findByEmail(accountEmail);
        }
        
        Patient patient;
        if (patientOpt.isPresent()) {
            patient = patientOpt.get();
            log.info("📝 Cập nhật bệnh nhân có sẵn - ID: {}", patient.getId());
        } else {
            // ⭐ TẠO MỚI nếu chưa có
            log.info("➕ Tạo mới bệnh nhân với email: {}", accountEmail);
            patient = new Patient();
            patient.setEmail(accountEmail);
        }

        // Cập nhật các trường cơ bản
        if (dto.getFirstname() != null && !dto.getFirstname().trim().isEmpty()) {
            patient.setFirstname(dto.getFirstname().trim());
            log.info("   ✓ Firstname: {}", dto.getFirstname());
        }
        
        if (dto.getLastname() != null && !dto.getLastname().trim().isEmpty()) {
            patient.setLastname(dto.getLastname().trim());
            log.info("   ✓ Lastname: {}", dto.getLastname());
        }
        
        if (dto.getPhonenum() != null && !dto.getPhonenum().trim().isEmpty()) {
            patient.setPhonenum(dto.getPhonenum().trim());
            log.info("   ✓ Phone: {}", dto.getPhonenum());
        }
        
        if (dto.getIdNumber() != null && !dto.getIdNumber().trim().isEmpty()) {
            patient.setIdNumber(dto.getIdNumber().trim());
            log.info("   ✓ ID Number: {}", dto.getIdNumber());
        }
        
        if (dto.getGender() != null && !dto.getGender().trim().isEmpty()) {
            patient.setGender(dto.getGender().trim());
            log.info("   ✓ Gender: {}", dto.getGender());
        }
        
        if (dto.getInsurance() != null && !dto.getInsurance().trim().isEmpty()) {
            patient.setInsurance(dto.getInsurance().trim());
            log.info("   ✓ Insurance: {}", dto.getInsurance());
        }
        
        if (dto.getRelativePhone() != null && !dto.getRelativePhone().trim().isEmpty()) {
            patient.setRelativePhone(dto.getRelativePhone().trim());
            log.info("   ✓ Relative Phone: {}", dto.getRelativePhone());
        }
        
        if (dto.getProvince() != null && !dto.getProvince().trim().isEmpty()) {
            patient.setProvince(dto.getProvince().trim());
            log.info("   ✓ Province: {}", dto.getProvince());
        }
        
        if (dto.getDistrict() != null && !dto.getDistrict().trim().isEmpty()) {
            patient.setDistrict(dto.getDistrict().trim());
            log.info("   ✓ District: {}", dto.getDistrict());
        }
        
        if (dto.getWard() != null && !dto.getWard().trim().isEmpty()) {
            patient.setWard(dto.getWard().trim());
            log.info("   ✓ Ward: {}", dto.getWard());
        }
        
        if (dto.getEthnic() != null && !dto.getEthnic().trim().isEmpty()) {
            patient.setEthnic(dto.getEthnic().trim());
            log.info("   ✓ Ethnic: {}", dto.getEthnic());
        }
        
        if (dto.getReferralCode() != null && !dto.getReferralCode().trim().isEmpty()) {
            patient.setReferralCode(dto.getReferralCode().trim());
            log.info("   ✓ Referral Code: {}", dto.getReferralCode());
        }

        // Xử lý DOB - chuyển String sang LocalDate
        if (dto.getDob() != null && !dto.getDob().isEmpty()) {
            try {
                LocalDate dob = LocalDate.parse(dto.getDob(), DateTimeFormatter.ISO_LOCAL_DATE);
                patient.setDob(dob);
                log.info("   ✓ DOB: {}", dob);
            } catch (Exception e) {
                log.error("   ✗ Lỗi parse DOB từ string '{}': {}", dto.getDob(), e.getMessage());
            }
        }

        // Cập nhật avatar
        if (dto.getAvatar() != null && !dto.getAvatar().isEmpty()) {
            patient.setAvatar(dto.getAvatar());
            log.info("   ✓ Avatar: {}", dto.getAvatar());
        }

        log.info("💾 Lưu thông tin bệnh nhân vào database...");
        Patient savedPatient = patientRepo.save(patient);
        log.info("✅ Đã lưu thành công - Patient ID: {}", savedPatient.getId());
        
        return savedPatient;
    }
}
