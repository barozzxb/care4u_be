package vn.care4u.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDashboardStatsDTO {
    private long upcomingAppointments;
    private long pendingRecords;
    private long newNotifications;
}
