package TennisConnect.controller;

import TennisConnect.model.Coach;
import TennisConnect.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coaches")
public class CoachController {

    @Autowired
    private CoachService coachService;

    // 取得所有教練 GET /api/coaches
    @GetMapping
    public List<Coach> getAllCoaches() {
        return coachService.getAllCoaches();
    }

    // 根據ID取得教練 GET /api/coaches/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Coach> getCoachById(@PathVariable Long id) {
        return coachService.getCoachById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 註冊新教練 POST /api/coaches/register
    @PostMapping("/register")
    public Coach registerCoach(@RequestBody Coach coach) {
        return coachService.registerCoach(coach);
    }

    // 刪除教練 DELETE /api/coaches/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoach(@PathVariable Long id) {
        coachService.deleteCoach(id);
        return ResponseEntity.noContent().build();
    }
}