package com.example.shows;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/shows/{show_id}/episodes")
public class EpisodesController {
  private final EpisodesRepository episodesRepository;

  public EpisodesController(EpisodesRepository episodesRepository) {
    this.episodesRepository = episodesRepository;
  }

  @GetMapping
  public List<EpisodeResponse> getEpisode(@PathVariable Long show_id) {

   return this.episodesRepository.findByShowId(show_id).stream()
     .map(EpisodeResponse::new).collect(toList());
  }

  @PostMapping
  public EpisodeResponse makeEpisode(@PathVariable Long show_id, @RequestBody Episode newEpisode) {
    newEpisode.setShow_id(show_id);
    System.out.println(" = " + newEpisode);

    return new EpisodeResponse(this.episodesRepository.save(newEpisode));
  }
}
