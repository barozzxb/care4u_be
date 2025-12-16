package vn.care4u.service;

import vn.care4u.entity.Account;
import vn.care4u.model.dto.UserDetailDTO;

public interface UserDetailService {

	UserDetailDTO getDetail(Account acc);

}
