package com.example.episodicevents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = PlayEvent.class, name = "play"),
  @JsonSubTypes.Type(value = PauseEvent.class, name = "pause"),
  @JsonSubTypes.Type(value = ProgressEvent.class, name = "progress"),
  @JsonSubTypes.Type(value = FastForwardEvent.class, name = "fastForward"),
  @JsonSubTypes.Type(value = RewindEvent.class, name = "rewind"),
  @JsonSubTypes.Type(value = ScrubEvent.class, name = "scrub")
})

public class Event {
    @Id
    private String id;
    private Long userId;
    private Long showId;
    private Long episodeId;
    private String createdAt;
}
