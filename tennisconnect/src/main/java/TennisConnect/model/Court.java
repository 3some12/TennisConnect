package TennisConnect.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "court")
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courtId;
    private String courtName;
    private String courtLocation;
    private String courtStatus;
}