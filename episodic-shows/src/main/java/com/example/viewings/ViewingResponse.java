package com.example.viewings;

import com.example.shows.Episode;
import com.example.shows.Show;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class ViewingResponse {
  private Show show;
  private Episode episode;
  private LocalDateTime updatedAt;
  private int timecode;
}
