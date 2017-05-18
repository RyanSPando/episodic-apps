package com.example.shows;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shows")
public class ShowsController {
  private final ShowsRepository showsRepository;


  public ShowsController(ShowsRepository showsRepository) {
    this.showsRepository = showsRepository;
  }

  @PostMapping
  public Show createShow(@RequestBody Show show) {
    return this.showsRepository.save(show);
  }

  @GetMapping
  public Iterable<Show> getShows() {
    return this.showsRepository.findAll();
  }
}
