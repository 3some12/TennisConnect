package TennisConnect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private CourseSession session;

    private LocalDateTime orderTime;
    private String orderStatus;
    private java.time.LocalDate sessionDate;
    private String sessionTime;
    private Double totalPrice;
}