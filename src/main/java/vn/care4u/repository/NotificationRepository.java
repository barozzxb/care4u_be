package vn.care4u.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.care4u.entity.Account;
import vn.care4u.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	public List<Notification> findByReceiver(Account acc);
}
