package com.example.shows;

import lombok.Data;

@Data
public class EpisodeResponse {
  private final Long id;
  private final int seasonNumber;
  private final int episodeNumber;
  private final String title;


  public EpisodeResponse(Episode episode) {
    this.id = episode.getId();
    this.seasonNumber = episode.getSeasonNumber();
    this.episodeNumber = episode.getEpisodeNumber();
    this.title = String.format("S%d E%d", episode.getSeasonNumber(), episode.getEpisodeNumber());
  }
}
