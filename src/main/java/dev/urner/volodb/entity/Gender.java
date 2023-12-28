package dev.urner.volodb.entity;

import jakarta.persistence.*;

@Entity
@Table(name="gender")
public class Gender {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private int id;

  /*
    1 = 'male'
    2 = 'female'
    3 = 'diverse'
    4 = 'not specified'
   */

  @Column(name="name")
  private String name;

  // define getter/setter
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}