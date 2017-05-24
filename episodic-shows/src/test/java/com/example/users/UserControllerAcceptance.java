package com.example.users;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerAcceptance {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private UsersRepository usersRepository;

  @Test
  public void createUser() throws JSONException {
    usersRepository.deleteAll();
    Map<String, Object> payload = new HashMap<String, Object>() {
      {
        put("email", "Foo");
      }
    };

    ResponseEntity<User> responseEntity = restTemplate.postForEntity("/users", payload, User.class);
    User user= responseEntity.getBody();
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals("Foo", user.getEmail());
  }

}
