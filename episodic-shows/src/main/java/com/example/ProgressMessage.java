package com.example;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgressMessage {
  private Long userId;
  private Long episodeId;
  private LocalDateTime createdAt;
  private int offset;
}

