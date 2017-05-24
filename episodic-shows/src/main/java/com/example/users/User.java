package com.example.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import javax.persistence.*;

@Entity(name = "users")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;

  private String email;

}
