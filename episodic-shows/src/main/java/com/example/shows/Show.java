package com.example.shows;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "shows")
@Data
public class Show {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
}
