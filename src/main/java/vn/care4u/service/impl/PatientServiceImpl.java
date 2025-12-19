package vn.care4u.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.care4u.entity.Patient;
import vn.care4u.model.dto.PatientUpdateDTO;
import vn.care4u.repository.PatientRepository;
import vn.care4u.service.PatientService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

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
        Optional<Patient> patientByAccount = patientRepo.findByAccount_Email(accountEmail);
        if (patientByAccount.isPresent()) {
            return patientByAccount.get();
        }

        Optional<Patient> patientByEmail = patientRepo.findByEmail(accountEmail);
        if (patientByEmail.isPresent()) {
            return patientByEmail.get();
        }

        throw new RuntimeException("Không tìm thấy bệnh nhân với email: " + accountEmail);
    }

    @Override
    public Patient updatePatientInfo(String accountEmail, PatientUpdateDTO dto) {

        Optional<Patient> patientOpt = patientRepo.findByAccount_Email(accountEmail);

        if (!patientOpt.isPresent()) {
            patientOpt = patientRepo.findByEmail(accountEmail);
        }

        Patient patient;
        if (patientOpt.isPresent()) {
            patient = patientOpt.get();
        } else {
            patient = new Patient();
            patient.setEmail(accountEmail);
        }

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

        if (dto.getAvatar() != null && !dto.getAvatar().isEmpty()) {
            patient.setAvatar(dto.getAvatar());
        }

        return patientRepo.save(patient);
    }
}
