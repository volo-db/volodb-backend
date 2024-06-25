package dev.urner.volodb.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "VolunteerDocument")
@Getter
@Setter
@RequiredArgsConstructor
public class VolunteerDocument {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "timestamp")
  private LocalDateTime timestamp;

  @Column(name = "name")
  private String name;

  @Column(name = "volunteer")
  private int volunteerId;

  @ManyToOne
  @JoinColumn(name = "type")
  private VolunteerDocumentType documentType;

  @Column(name = "size")
  private Long size;

  @Column(name = "path")
  private String path;

  @Column(name = "user")
  @JsonProperty("user")
  private String username;

}
