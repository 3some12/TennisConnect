package TennisConnect.repository;

import TennisConnect.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserUserId(Long userId);
    List<Order> findByCourseCoachCoachId(Long coachId);
}