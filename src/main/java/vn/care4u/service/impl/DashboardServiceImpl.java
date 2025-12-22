package vn.care4u.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import vn.care4u.entity.Department;
import vn.care4u.model.dto.AccountDTO;
import vn.care4u.model.dto.DashboardDTO;
import vn.care4u.model.dto.DepartmentDTO;
import vn.care4u.service.AccountService;
import vn.care4u.service.DashboardService;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

	private final AccountService accServ;

	@Override
	public DashboardDTO getData() {
		DashboardDTO dto = new DashboardDTO();

		Map<String, Long> numberedData = new HashMap<>();
		long totalAccounts = accServ.count();
		long activeAccounts = accServ.countActiveAccounts();
		List<AccountDTO> recentAccounts = accServ.findRecentAccounts();

		numberedData.put("totalAccounts", totalAccounts);
		numberedData.put("activeAccounts", activeAccounts);

		dto.setNumberedData(numberedData);
		dto.setRecentAccounts(recentAccounts);
		return dto;
	}
}
