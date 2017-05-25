package com.example.viewings;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ViewingsController {

  private final ViewingService viewingService;

  public ViewingsController(ViewingService viewingService) {
    this.viewingService = viewingService;
  }

  @PatchMapping("/users/{userId}/viewings")
  public void createOrUpdateViewing(@PathVariable("userId") Long userId, @RequestBody ViewingRequest viewingRequest) {
    viewingService.updateOrCreateViewing(viewingRequest, userId);
  }

  @GetMapping("/users/{userId}/recently-watched")
  public List<ViewingResponse> getViewings(@PathVariable("userId") Long userId) {
    return viewingService.getViewings(userId);
  }

}
