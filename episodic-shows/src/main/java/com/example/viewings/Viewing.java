package com.example.viewings;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "viewings")
@Data
public class Viewing {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Long userId;
  private Long showId;
  private Long episodeId;
  private LocalDateTime updatedAt;
  @Column(name = "timecode")
  private int timecode;

}
