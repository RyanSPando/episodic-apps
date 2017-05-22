package com.example.episodicevents;

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
import java.util.List;
import java.util.Map;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EventsControllerTest {
  ObjectMapper mapper = new ObjectMapper();

  @Autowired
  EventsRepository eventsRepository;

  @Autowired
  MockMvc mvc;

  @Before
  public void setup() {
    eventsRepository.deleteAll();
  }

  @Test
  public void EventsController_CreatePlayEvent() throws Exception {
    Map<String, Object> data = new HashMap<String, Object>() {
      {
        put("offset", 0);
      }
    };

    Map<String, Object> payload = new HashMap<String, Object>() {
      {
        put("type", "play");
        put("userId", 52);
        put("showId", 987);
        put("episodeId", 456);
        put("createdAt", "2017-11-08T15:59:13.0091745");
        put("data", data);
      }
    };

    String json = mapper.writeValueAsString(payload);

    MockHttpServletRequestBuilder request = post("/")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .content(json);

    mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.type", equalTo("play")));
  }

  @Test
  public void EventsController_CreatePauseEvent() throws Exception {
    Map<String, Object> data = new HashMap<String, Object>() {
      {
        put("offset", 0);
      }
    };

    Map<String, Object> payload = new HashMap<String, Object>() {
      {
        put("type", "pause");
        put("userId", 52);
        put("showId", 987);
        put("episodeId", 456);
        put("createdAt", "2017-11-08T15:59:13.0091745");
        put("data", data);
      }
    };

    String json = mapper.writeValueAsString(payload);

    MockHttpServletRequestBuilder request = post("/")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .content(json);

    mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.type", equalTo("pause")));
  }

  @Test
  public void EventsController_CreateProgressEvent() throws Exception {
    Map<String, Object> data = new HashMap<String, Object>() {
      {
        put("offset", 0);
      }
    };

    Map<String, Object> payload = new HashMap<String, Object>() {
      {
        put("type", "progress");
        put("userId", 52);
        put("showId", 987);
        put("episodeId", 456);
        put("createdAt", "2017-11-08T15:59:13.0091745");
        put("data", data);
      }
    };

    String json = mapper.writeValueAsString(payload);

    MockHttpServletRequestBuilder request = post("/")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .content(json);

    mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.type", equalTo("progress")));
  }

  @Test
  public void EventsController_CreateFastForwardEvent() throws Exception {
    Map<String, Object> data = new HashMap<String, Object>() {
      {
        put("startOffset", 4);
        put("endOffset", 408);
        put("speed", 2.5);
      }
    };

    Map<String, Object> payload = new HashMap<String, Object>() {
      {
        put("type", "fastForward");
        put("userId", 52);
        put("showId", 987);
        put("episodeId", 456);
        put("createdAt", "2017-11-08T15:59:13.0091745");
        put("data", data);
      }
    };

    String json = mapper.writeValueAsString(payload);

    MockHttpServletRequestBuilder request = post("/")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .content(json);

    mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.type", equalTo("fastForward")));

  }


  @Test
  public void EventsController_CreateRewindEvent() throws Exception {
    Map<String, Object> data = new HashMap<String, Object>() {
      {
        put("startOffset", 4);
        put("endOffset", 408);
        put("speed", 2.5);
      }
    };

    Map<String, Object> payload = new HashMap<String, Object>() {
      {
        put("type", "rewind");
        put("userId", 52);
        put("showId", 987);
        put("episodeId", 456);
        put("createdAt", "2017-11-08T15:59:13.0091745");
        put("data", data);
      }
    };

    String json = mapper.writeValueAsString(payload);

    MockHttpServletRequestBuilder request = post("/")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .content(json);

    mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.type", equalTo("rewind")));

  }

  @Test
  public void EventsController_CreateScrubEvent() throws Exception {
    Map<String, Object> data = new HashMap<String, Object>() {
      {
        put("startOffset", 4);
        put("endOffset", 408);
      }
    };

    Map<String, Object> payload = new HashMap<String, Object>() {
      {
        put("type", "scrub");
        put("userId", 52);
        put("showId", 987);
        put("episodeId", 456);
        put("createdAt", "2017-11-08T15:59:13.0091745");
        put("data", data);
      }
    };

    String json = mapper.writeValueAsString(payload);

    MockHttpServletRequestBuilder request = post("/")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .content(json);

    mvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.type", equalTo("scrub")));
  }

  @Test
  public void EventsController_getsLastTwentyEvents() throws Exception {
    Map<String, Object> data = new HashMap<String, Object>();

    Map<String, Object> payload = new HashMap<String, Object>() {
      {
        put("type", "progress");
        put("userId", 52);
        put("showId", 987);
        put("episodeId", 456);
        put("createdAt", "2017-11-08T15:59:13.0091745");
        put("data", data);
      }
    };

    for (int i = 0; i < 25; i++) {
      data.put("offset", i * 3);
      String json = mapper.writeValueAsString(payload);

      MockHttpServletRequestBuilder request = post("/")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(json);

      mvc.perform(request);
    }

    MockHttpServletRequestBuilder request = get("/")
      .accept(MediaType.APPLICATION_JSON);

    mvc.perform(request)
    .andExpect(jsonPath("$", hasSize(20)));

  }
}
