package dev.urner.volodb.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "project")
@Getter
@Setter
@JsonDeserialize(using = ProjectDeserializer.class)
public class Project {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "organisationalId")
  private String organisationalId;

  @Column(name = "name")
  private String name;

  @Column(name = "shorthand")
  private String shorthand;

  @Column(name = "description")
  private String description;

  @Column(name = "capacity")
  private int capacity;

  @Column(name = "careof")
  private String careof;

  @ManyToOne
  @JoinColumn(name = "country") // FK
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
  @JsonIdentityReference(alwaysAsId = true)
  private Country country;

  @Column(name = "state")
  private String state;

  @Column(name = "street")
  private String street;

  @Column(name = "postalCode")
  private String postalCode;

  @Column(name = "city")
  private String city;

  @Column(name = "phone")
  private String phone;

  @Column(name = "email")
  private String email;

}
