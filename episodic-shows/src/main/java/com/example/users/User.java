package com.example.users;

import lombok.Data;
import javax.persistence.*;

@Entity(name = "users")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;

  private String email;

}
