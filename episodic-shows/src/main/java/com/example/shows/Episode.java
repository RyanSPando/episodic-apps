package com.example.shows;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="episodes")
@Data
public class Episode {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long showId;
  private int seasonNumber;
  private int episodeNumber;


}
