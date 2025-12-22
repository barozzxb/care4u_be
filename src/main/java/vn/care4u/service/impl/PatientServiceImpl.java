package vn.care4u.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vn.care4u.entity.Patient;
import vn.care4u.model.dto.PatientUpdateDTO;
import vn.care4u.repository.PatientRepository;
import vn.care4u.service.PatientService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
        Patient patient = getOrCreatePatient(accountEmail);
        applyDto(patient, dto);
        return patientRepo.save(patient);
    }

    @Override
    public Patient updatePatientInfoWithAvatar(
            String accountEmail,
            PatientUpdateDTO dto,
            MultipartFile avatar
    ) {
        Patient patient = getOrCreatePatient(accountEmail);
        applyDto(patient, dto);

        if (avatar != null && !avatar.isEmpty()) {
            try {
                // Đường dẫn lưu file: thư mục uploads/avatars trong root project
                String uploadDir = "uploads/avatars";
                Path uploadPath = Paths.get(uploadDir);

                // Tạo thư mục nếu chưa tồn tại (an toàn với multi-thread)
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Lấy phần mở rộng file
                String originalFilename = avatar.getOriginalFilename();
                String fileExtension = "";
                if (originalFilename != null && originalFilename.contains(".")) {
                    fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                }

                // Tên file unique
                String fileName = System.currentTimeMillis() + fileExtension;
                Path destinationPath = uploadPath.resolve(fileName);

                // Copy file từ input stream (tốt hơn transferTo vì không phụ thuộc temp dir)
                Files.copy(avatar.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

                // Lưu đường dẫn truy cập từ web vào DB
                patient.setAvatar("/uploads/avatars/" + fileName);

            } catch (IOException e) {
                throw new RuntimeException("Upload avatar thất bại: " + e.getMessage(), e);
            }
        }

        return patientRepo.save(patient);
    }

    private Patient getOrCreatePatient(String accountEmail) {
        Optional<Patient> patientOpt = patientRepo.findByAccount_Email(accountEmail);

        if (!patientOpt.isPresent()) {
            patientOpt = patientRepo.findByEmail(accountEmail);
        }

        if (patientOpt.isPresent()) {
            return patientOpt.get();
        }

        Patient patient = new Patient();
        patient.setEmail(accountEmail);
        return patient;
    }

    private void applyDto(Patient patient, PatientUpdateDTO dto) {
        if (dto.getFirstname() != null && !dto.getFirstname().trim().isEmpty()) {
            patient.setFirstname(dto.getFirstname().trim());
        }
        if (dto.getLastname() != null && !dto.getLastname().trim().isEmpty()) {
            patient.setLastname(dto.getLastname().trim());
        }
        if (dto.getPhonenum() != null && !dto.getPhonenum().trim().isEmpty()) {
            patient.setPhonenum(dto.getPhonenum().trim());
        }
        if (dto.getIdNumber() != null && !dto.getIdNumber().trim().isEmpty()) {
            patient.setIdNumber(dto.getIdNumber().trim());
        }
        if (dto.getGender() != null && !dto.getGender().trim().isEmpty()) {
            patient.setGender(dto.getGender().trim());
        }
        if (dto.getInsurance() != null && !dto.getInsurance().trim().isEmpty()) {
            patient.setInsurance(dto.getInsurance().trim());
        }
        if (dto.getRelativePhone() != null && !dto.getRelativePhone().trim().isEmpty()) {
            patient.setRelativePhone(dto.getRelativePhone().trim());
        }
        if (dto.getProvince() != null && !dto.getProvince().trim().isEmpty()) {
            patient.setProvince(dto.getProvince().trim());
        }
        if (dto.getDistrict() != null && !dto.getDistrict().trim().isEmpty()) {
            patient.setDistrict(dto.getDistrict().trim());
        }
        if (dto.getWard() != null && !dto.getWard().trim().isEmpty()) {
            patient.setWard(dto.getWard().trim());
        }
        if (dto.getEthnic() != null && !dto.getEthnic().trim().isEmpty()) {
            patient.setEthnic(dto.getEthnic().trim());
        }
        if (dto.getReferralCode() != null && !dto.getReferralCode().trim().isEmpty()) {
            patient.setReferralCode(dto.getReferralCode().trim());
        }
        if (dto.getDob() != null && !dto.getDob().isEmpty()) {
            try {
                LocalDate dob = LocalDate.parse(dto.getDob(), DateTimeFormatter.ISO_LOCAL_DATE);
                patient.setDob(dob);
            } catch (Exception ignored) {
            }
        }
    }
}
