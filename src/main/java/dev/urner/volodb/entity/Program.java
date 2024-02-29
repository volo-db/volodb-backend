package dev.urner.volodb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "program")
public class Program {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "shorthand")
  private String shorthand;


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

  public String getShorthand() {
    return shorthand;
  }

  public void setShorthand(String shorthand) {
    this.shorthand = shorthand;
  }
}
