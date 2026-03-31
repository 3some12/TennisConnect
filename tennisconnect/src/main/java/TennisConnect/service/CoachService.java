package TennisConnect.service;

import TennisConnect.model.Coach;
import TennisConnect.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CoachService {
    @Autowired
    private CoachRepository coachRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Coach> getAllCoaches() {
        return coachRepository.findAll();
    }

    public Optional<Coach> getCoachById(Long id) {
        return coachRepository.findById(id);
    }

    public Coach registerCoach(Coach coach) {
        coach.setPasswordHash(passwordEncoder.encode(coach.getPasswordHash()));
        return coachRepository.save(coach);
    }

    public Optional<Coach> findByEmail(String email) {
        return coachRepository.findByEmail(email);
    }

    public void deleteCoach(Long id) {
        coachRepository.deleteById(id);
    }
}