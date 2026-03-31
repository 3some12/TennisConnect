package TennisConnect.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "coach")
public class Coach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coachId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Integer experienceYears;
    private String specialty;
    private String coachStatus;
    private String passwordHash;
}