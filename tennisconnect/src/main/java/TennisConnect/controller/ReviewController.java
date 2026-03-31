package TennisConnect.controller;

import TennisConnect.model.Review;
import TennisConnect.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // 取得所有評論 GET /api/reviews
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // 根據ID取得評論 GET /api/reviews/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 根據訂單ID取得評論 GET /api/reviews/order/{orderId}
    @GetMapping("/order/{orderId}")
    public List<Review> getReviewsByOrder(@PathVariable Long orderId) {
        return reviewService.getReviewsByOrder(orderId);
    }

    // 新增評論 POST /api/reviews
    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewService.createReview(review);
    }

    // 刪除評論 DELETE /api/reviews/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}