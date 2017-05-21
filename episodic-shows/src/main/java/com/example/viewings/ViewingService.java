package com.example.viewings;

import com.example.shows.Episode;
import com.example.shows.EpisodesRepository;
import com.example.shows.Show;
import com.example.shows.ShowsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ViewingService {
  private final ViewingsRepository viewingsRepository;
  private final ShowsRepository showsRepository;
  private final EpisodesRepository episodesRepository;

  public ViewingService(ViewingsRepository viewingsRepository, ShowsRepository showsRepository, EpisodesRepository episodesRepository) {
    this.viewingsRepository = viewingsRepository;
    this.showsRepository = showsRepository;
    this.episodesRepository = episodesRepository;
  }

  @Transactional
  public void updateOrCreateViewing(ViewingRequest viewingRequest, Long userId) {
    Episode episode = this.episodesRepository.findOne(viewingRequest.getEpisodeId());

    List<Viewing> viewings = this.viewingsRepository.findAll();

    Optional<Viewing> viewingInDb = viewings.stream()
      .filter(viewing -> viewing.getEpisodeId() == episode.getId() && viewing.getShowId() == episode.getShowId())
      .findFirst();

    if (viewingInDb.isPresent()) {
      viewingsRepository.setViewingInfoById(viewingRequest.getUpdatedAt(),
        viewingRequest.getTimecode(), viewingInDb.get().getId());
    } else {
      Viewing viewing = new Viewing();
      viewing.setUserId(userId);
      viewing.setShowId(episode.getShowId());
      viewing.setEpisodeId(viewingRequest.getEpisodeId());
      viewing.setUpdatedAt(viewingRequest.getUpdatedAt());
      viewing.setTimecode(viewingRequest.getTimecode());

      viewingsRepository.save(viewing);
    }
  }

  public List<ViewingResponse> getViewings(Long userId) {

    List<Viewing> viewings = this.viewingsRepository.getViewingsByUserId(userId);

    List<ViewingResponse> viewingResponses = viewings.stream().map(viewing ->
    {
      Show show = showsRepository.findOne(viewing.getShowId());
      Episode episode = episodesRepository.findOne(viewing.getEpisodeId());
      return new ViewingResponse(show, episode, viewing.getUpdatedAt(), viewing.getTimecode());
    })
      .collect(Collectors.toList());

    return viewingResponses;
  }
}
