package dev.urner.volodb.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "VolunteerNote")
@Getter
@Setter
@RequiredArgsConstructor
public class VolunteerNote {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "timestamp")
  private LocalDateTime timestamp;

  @Column(name = "volunteer")
  private int volunteer;

  @Column(name = "type")
  private String type;

  @Column(name = "user")
  @JsonProperty("user")
  private String username;

  @Column(name = "note")
  @JsonProperty("note")
  private String noteContent;
}
