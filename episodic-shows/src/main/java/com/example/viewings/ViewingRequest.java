package com.example.viewings;

import lombok.Value;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@Value
public class ViewingRequest {
  private final Long episodeId;
  private final LocalDateTime updatedAt;
  private final int timecode;
}
