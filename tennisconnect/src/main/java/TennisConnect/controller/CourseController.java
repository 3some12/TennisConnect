package TennisConnect.controller;

import TennisConnect.model.Course;
import TennisConnect.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/coach/{coachId}")
    public List<Course> getCoursesByCoach(@PathVariable Long coachId) {
        return courseService.getCoursesByCoach(coachId);
    }

    @GetMapping("/court/{courtId}")
    public List<Course> getCoursesByCourt(@PathVariable Long courtId) {
        return courseService.getCoursesByCourt(courtId);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.getCourseById(id)
                .map(existing -> {
                    course.setCourseId(id);
                    return ResponseEntity.ok(courseService.saveCourse(course));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}