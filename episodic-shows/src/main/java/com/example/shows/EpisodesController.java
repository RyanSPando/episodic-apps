package com.example.shows;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/shows/{showId}/episodes")
public class EpisodesController {
  private final EpisodesRepository episodesRepository;

  public EpisodesController(EpisodesRepository episodesRepository) {
    this.episodesRepository = episodesRepository;
  }

  @GetMapping
  public List<EpisodeResponse> getEpisode(@PathVariable Long showId) {

   return this.episodesRepository.findByShowId(showId).stream()
     .map(EpisodeResponse::new).collect(toList());
  }

  @PostMapping
  public EpisodeResponse makeEpisode(@PathVariable Long showId, @RequestBody Episode newEpisode) {
    newEpisode.setShowId(showId);

    return new EpisodeResponse(this.episodesRepository.save(newEpisode));
  }
}
