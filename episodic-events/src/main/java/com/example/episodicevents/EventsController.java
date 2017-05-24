package com.example.episodicevents;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventsController {
  private final EventsRepository eventsRepository;
  private final RabbitTemplate rabbitTemplate;

  public EventsController(EventsRepository eventsRepository, RabbitTemplate rabbitTemplate) {
    this.eventsRepository = eventsRepository;
    this.rabbitTemplate = rabbitTemplate;
  }

  @PostMapping("/")
  public Event addEvent(@RequestBody Event event) {

    if(event.getClass().isAssignableFrom(ProgressEvent.class)) {
      ProgressEvent progressEvent = (ProgressEvent) event;
      rabbitTemplate.convertAndSend("#", "episodic-progress",
        new QueueEvent(progressEvent.getUserId(), progressEvent.getEpisodeId(),
          progressEvent.getCreatedAt(), progressEvent.getData().getOffset()));
    }
    return eventsRepository.save(event);
  }

  @GetMapping("/recent")
  public List<Event> getEvents() {
    return eventsRepository.findFirst20ByOrderByCreatedAtDesc();
  }
}
