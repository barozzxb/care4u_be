package vn.care4u.model.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DashboardDTO {

	private Map<String, Long> numberedData;
	private List<Map<String, List<Long>>> chartData;
	private List<AccountDTO> recentAccounts;
}
