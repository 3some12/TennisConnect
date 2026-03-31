package TennisConnect.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @ManyToOne
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "court_id")
    private Court court;

    private String courseName;
    private String courseLevel;
    private Integer capacity;
    private Double coursePrice;
    private String courseStatus;
}