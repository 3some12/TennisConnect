package TennisConnect.repository;

import TennisConnect.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCoachCoachId(Long coachId);
    List<Course> findByCourtCourtId(Long courtId);
    List<Course> findByCourseStatus(String status);
}