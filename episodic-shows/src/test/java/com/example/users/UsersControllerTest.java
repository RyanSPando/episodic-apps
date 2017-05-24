package com.example.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
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
@AutoConfigureMockMvc(secure = false)
public class UsersControllerTest {

  @Autowired
  UsersRepository usersRepository;

  @Autowired
  MockMvc mvc;

  @Before
  public void setup() {
    usersRepository.deleteAll();
  }

  @Test
  public void userController_createsUser() throws Exception {

    Map<String, Object> payload = new HashMap<String, Object>() {
      {
        put("email", "joe@example.com");
      }
    };

    ObjectMapper mapper = new ObjectMapper();

    String json = mapper.writeValueAsString(payload);

    MockHttpServletRequestBuilder request = post("/users")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .content(json);

    mvc
      .perform(request)
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.id", notNullValue()));
  }

  @Test
  public void userController_getsAUser() throws Exception {

    User user = new User();
    user.setEmail("joe2@example.com");

    usersRepository.save(user);

    MockHttpServletRequestBuilder getRequest = get("/users")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    mvc.perform(getRequest)
      .andExpect(status().is2xxSuccessful())
      .andExpect(jsonPath("$[0].id", notNullValue()))
      .andExpect(jsonPath("$[0].email", equalTo("joe2@example.com")));
  }
}
