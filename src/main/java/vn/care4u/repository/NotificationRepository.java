package vn.care4u.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import vn.care4u.entity.Account;
import vn.care4u.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

	public List<Notification> findByReceiver(Account acc);
	public List<Notification> findByReceiver(Account acc, Pageable pageable);
	
	public Page<Notification> findAllByReceiver(Account acc, Pageable pageable);
	
	@Transactional
	@Modifying
	@Query("UPDATE Notification n SET n.isRead = true WHERE n.id = :id AND n.receiver = :acc")
	int markAsRead(Long id, Account acc);
}
