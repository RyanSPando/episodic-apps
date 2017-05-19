package com.example.shows;

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

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc()
public class EpisodesControllerTest {
  private Long showId;

  @Autowired
  MockMvc mvc;

  @Autowired
  ShowsRepository showsRepository;

  @Autowired
  EpisodesRepository episodeRepository;

  @Before
  public void setup() {
    episodeRepository.deleteAll();
    showsRepository.deleteAll();

    Show show = new Show();
    show.setName("Pinky and the Brain");

    Show dbShow = showsRepository.save(show);

    showId = dbShow.getId();
  }

  @Test
  public void episodesController_getsEpisodes() throws Exception {

    Episode episode = new Episode();
    episode.setShow_id(showId);
    episode.setEpisodeNumber(2);
    episode.setSeasonNumber(3);

    episodeRepository.save(episode);

    MockHttpServletRequestBuilder getRequest = get("/shows/" + showId.toString() + "/episodes")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    mvc.perform(getRequest)
      .andExpect(status().is2xxSuccessful())
      .andExpect(jsonPath("$[0].id", notNullValue()))
      .andExpect(jsonPath("$[0].episodeNumber", equalTo(2)))
      .andExpect(jsonPath("$[0].seasonNumber", equalTo(3)))
      .andExpect(jsonPath("$[0].title", equalTo("S3 E2")));

  }

  @Test
  public void episodesController_createsEpisodes() throws Exception {
    Map<String, Object> payload = new HashMap<String, Object>() {
      {
        put("seasonNumber", "3");
        put("episodeNumber", "2");
      }
    };

    ObjectMapper mapper = new ObjectMapper();

    String json = mapper.writeValueAsString(payload);
    System.out.println("json = " + json);

    MockHttpServletRequestBuilder getRequest = post("/shows/" + showId.toString() + "/episodes")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .content(json);

    mvc.perform(getRequest)
      .andExpect(status().is2xxSuccessful())
      .andExpect(jsonPath("$.id", notNullValue()))
      .andExpect(jsonPath("$.episodeNumber", equalTo(2)))
      .andExpect(jsonPath("$.seasonNumber", equalTo(3)))
      .andExpect(jsonPath("$.title", equalTo("S3 E2")));

  }
}
