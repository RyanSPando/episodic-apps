package com.example.episodicevents;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventsController {
  private final EventsRepository eventsRepository;

  public EventsController(EventsRepository eventsRepository) {
    this.eventsRepository = eventsRepository;
  }

  @PostMapping("/")
  public Event addEvent(@RequestBody Event event) {
    return eventsRepository.save(event);
  }

  @GetMapping("/recent")
  public List<Event> getEvents() {
    return eventsRepository.findFirst20ByOrderByCreatedAtDesc();
  }
}
