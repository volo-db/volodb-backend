package dev.urner.volodb.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "person")
@Getter
@Setter
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "lastname")
  private String lastname;

  @Column(name = "firstname")
  private String firstname;

  @ManyToOne
  @JoinColumn(name = "gender")
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
  @JsonIdentityReference(alwaysAsId = true)
  private Gender gender;

}
