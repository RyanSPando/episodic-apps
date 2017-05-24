package com.example.episodicevents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QueueEvent {
  private Long userId;
  private Long episodeId;
  private String createdAt;
  private int offset;
}
