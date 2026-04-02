package TennisConnect.repository;

import TennisConnect.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByOrderOrderId(Long orderId);

    Optional<Review> findByOrderOrderId(Long orderId);
}