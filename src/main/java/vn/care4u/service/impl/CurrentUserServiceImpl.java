package vn.care4u.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import vn.care4u.enumeration.ErrorCode;
import vn.care4u.exception.GeneralException;
import vn.care4u.repository.DoctorRepository;
import vn.care4u.service.CurrentUserService;

@Service
@RequiredArgsConstructor
public class CurrentUserServiceImpl implements CurrentUserService {

    private final DoctorRepository doctorRepo;


    @Override
    public String currentEmail() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getName() == null) throw new GeneralException(ErrorCode.UNAUTHORIZED);
        return auth.getName(); // JwtFilter đã set name = email
    }

    @Override
    public Long currentDoctorId() {
        return doctorRepo.findIdByAccountEmail(currentEmail())
                .orElseThrow(() -> new GeneralException(ErrorCode.ACCOUNT_NOT_FOUND));
    }
}
