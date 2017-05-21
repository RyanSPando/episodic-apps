package com.example.users;

import com.example.shows.Episode;
import com.example.shows.EpisodesRepository;
import com.example.shows.Show;
import com.example.shows.ShowsRepository;
import com.example.viewings.Viewing;
import com.example.viewings.ViewingsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ViewingsControllerTest {
  private Long userId;
  private Long showId;
  private Long episodeId;
  private Show dbshow;
  private Episode dbEpisode;
  private LocalDateTime localDateTime;


  @Autowired
  MockMvc mvc;

  @Autowired
  UsersRepository usersRepository;

  @Autowired
  ShowsRepository showsRepository;

  @Autowired
  EpisodesRepository episodesRepository;

  @Autowired
  ViewingsRepository viewingsRepository;

  @Before
  public void setup() {
    usersRepository.deleteAll();
    episodesRepository.deleteAll();
    showsRepository.deleteAll();
    viewingsRepository.deleteAll();

    User user = new User();
    user.setEmail("Brian.Boitano@goldmedalist.com");
    User dbUser = usersRepository.save(user);
    userId = dbUser.getId();

    Show show = new Show();
    show.setName("Queer Eye for the Straight Guy");
    dbshow = showsRepository.save(show);
    showId = dbshow.getId();

    Episode episode = new Episode();
    episode.setSeasonNumber(4);
    episode.setEpisodeNumber(22);
    episode.setShowId(showId);
    dbEpisode = episodesRepository.save(episode);
    episodeId = dbEpisode.getId();

  }

  @Test
  public void viewingsController_patchCreatesViewingEntry() throws Exception {

    Map<String, Object> payload = new HashMap<String, Object>() {
      {
        put("episodeId", episodeId);
        put("updatedAt", "2017-05-04T11:45:34.9182");
        put("timecode", 79);
      }
    };

    ObjectMapper mapper = new ObjectMapper();

    String json = mapper.writeValueAsString(payload);

    MockHttpServletRequestBuilder request = patch("/users/" + userId.toString() + "/viewings")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .content(json);

    mvc.perform(request)
      .andExpect(status().isOk());
  }

  @Test
  public void viewingsController_patchUpdatesViewingEntry() throws Exception {
    Viewing viewing = new Viewing();
    viewing.setTimecode(100);
    viewing.setUpdatedAt(localDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    viewing.setEpisodeId(episodeId);
    viewing.setUserId(userId);
    viewing.setShowId(showId);

    Viewing dbViewing = viewingsRepository.save(viewing);

    Map<String, Object> payload = new HashMap<String, Object>() {
      {
        put("episodeId", episodeId);
        put("updatedAt", "2017-05-04T11:45:34.9182");
        put("timecode", 150);
      }
    };

    ObjectMapper mapper = new ObjectMapper();

    String json = mapper.writeValueAsString(payload);

    MockHttpServletRequestBuilder request = patch("/users/" + userId.toString() + "/viewings")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .content(json);

    mvc.perform(request)
      .andExpect(status().isOk());

    Viewing updatedViewing = viewingsRepository.findOne(dbViewing.getId());

    assertThat(updatedViewing.getTimecode(), equalTo(150));
  }

  @Test
  public void viewingsController_getViewingEntry_recentlyWatched() throws Exception {
    ObjectMapper mapper = new ObjectMapper();

    Viewing viewing = new Viewing();
    viewing.setTimecode(100);
    viewing.setUpdatedAt(localDateTime.now().truncatedTo(ChronoUnit.SECONDS));
    viewing.setEpisodeId(episodeId);
    viewing.setUserId(userId);
    viewing.setShowId(showId);

    viewingsRepository.save(viewing);

    MockHttpServletRequestBuilder request = get("/users/" + userId.toString() + "/recently-watched")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.[0].show.id", equalTo(dbshow.getId().intValue())))
      .andExpect(jsonPath("$.[0].episode.id", equalTo(dbEpisode.getId().intValue())))
      .andExpect(jsonPath("$.[0].updatedAt", equalTo(viewing.getUpdatedAt().toString())))
      .andExpect(jsonPath("$.[0].timecode", equalTo(viewing.getTimecode())));
  }
}
