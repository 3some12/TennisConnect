package TennisConnect.service;

import TennisConnect.model.Course;
import TennisConnect.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public List<Course> getCoursesByCoach(Long coachId) {
        return courseRepository.findByCoachCoachId(coachId);
    }

    public List<Course> getCoursesByCourt(Long courtId) {
        return courseRepository.findByCourtCourtId(courtId);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}