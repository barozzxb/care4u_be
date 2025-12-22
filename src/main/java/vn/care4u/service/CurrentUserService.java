package vn.care4u.service;

import vn.care4u.entity.Doctor;

public interface CurrentUserService {
    String currentEmail();
    Long currentDoctorId();
    Doctor currentDoctor();
}
