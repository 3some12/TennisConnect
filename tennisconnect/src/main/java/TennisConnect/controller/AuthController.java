package TennisConnect.controller;

import TennisConnect.model.Coach;
import TennisConnect.model.User;
import TennisConnect.service.CoachService;
import TennisConnect.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private UserService userService;
    @Autowired private CoachService coachService;
    @Autowired private PasswordEncoder passwordEncoder;

    // 學員登入
    @PostMapping("/login/user")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> body, HttpSession session) {
        String email = body.get("email");
        String password = body.get("password");

        Optional<User> userOpt = userService.findByEmail(email);
        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPasswordHash())) {
            User user = userOpt.get();
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userName", user.getFirstName() + " " + user.getLastName());
            session.setAttribute("role", "user");
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "name", user.getFirstName() + " " + user.getLastName(),
                    "role", "user",
                    "id", user.getUserId()
            ));
        }
        return ResponseEntity.status(401).body(Map.of("success", false, "message", "Invalid email or password"));
    }

    // 教練登入
    @PostMapping("/login/coach")
    public ResponseEntity<?> loginCoach(@RequestBody Map<String, String> body, HttpSession session) {
        String email = body.get("email");
        String password = body.get("password");

        Optional<Coach> coachOpt = coachService.findByEmail(email);
        if (coachOpt.isPresent() && passwordEncoder.matches(password, coachOpt.get().getPasswordHash())) {
            Coach coach = coachOpt.get();
            session.setAttribute("coachId", coach.getCoachId());
            session.setAttribute("coachName", coach.getFirstName() + " " + coach.getLastName());
            session.setAttribute("role", "coach");
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "name", coach.getFirstName() + " " + coach.getLastName(),
                    "role", "coach",
                    "id", coach.getCoachId()
            ));
        }
        return ResponseEntity.status(401).body(Map.of("success", false, "message", "Invalid email or password"));
    }

    // 確認目前登入狀態
    @GetMapping("/me")
    public ResponseEntity<?> getMe(HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (role == null) {
            return ResponseEntity.status(401).body(Map.of("loggedIn", false));
        }
        if (role.equals("user")) {
            return ResponseEntity.ok(Map.of(
                    "loggedIn", true,
                    "role", "user",
                    "name", session.getAttribute("userName"),
                    "id", session.getAttribute("userId")
            ));
        } else {
            return ResponseEntity.ok(Map.of(
                    "loggedIn", true,
                    "role", "coach",
                    "name", session.getAttribute("coachName"),
                    "id", session.getAttribute("coachId")
            ));
        }
    }

    // 登出
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of("success", true));
    }
}