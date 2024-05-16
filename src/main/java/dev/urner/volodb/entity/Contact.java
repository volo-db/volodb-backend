package dev.urner.volodb.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name = "contact")
@Getter
@Setter
@RequiredArgsConstructor
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "person")
  private int personId;

  @ManyToOne
  @JoinColumn(name = "type")
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
  @JsonIdentityReference(alwaysAsId = true)
  private ContactType type;

  @Column(name = "val")
  @JsonProperty("value")
  private String value;

}
