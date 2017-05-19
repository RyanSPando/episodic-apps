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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

public class ShowsControllerTest {

  @Autowired
  MockMvc mvc;

  @Autowired
  ShowsRepository showsRepository;

  @Before
  public void setup() {
    showsRepository.deleteAll();
  }

  @Test
  public void showsController_createsShow() throws Exception {

    Map<String, Object> payload = new HashMap<String, Object>() {
      {
        put("name", "joe");
      }
    };

    ObjectMapper mapper = new ObjectMapper();

    String json = mapper.writeValueAsString(payload);

    MockHttpServletRequestBuilder request = post("/shows")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .content(json);

    mvc
      .perform(request)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id", notNullValue()))
      .andExpect(jsonPath("$.name", equalTo("joe")));
  }

  @Test
  public void showsController_getsAShow() throws Exception {

    Show show = new Show();
    show.setName("joe2");

    showsRepository.save(show);

    MockHttpServletRequestBuilder getRequest = get("/shows")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    mvc.perform(getRequest)
      .andExpect(status().is2xxSuccessful())
      .andExpect(jsonPath("$[0].id", notNullValue()))
      .andExpect(jsonPath("$[0].name", equalTo("joe2")));
  }
}
