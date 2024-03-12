package dev.urner.volodb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "gender")
@Getter
@Setter
public class Gender {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  /*
   * 1 = 'male'
   * 2 = 'female'
   * 3 = 'diverse'
   * 4 = 'not specified'
   */

  @Column(name = "name")
  private String name;

  public Gender() {
  }

  public Gender(String name) {
    switch (name.toLowerCase()) {
      case "male":
        this.id = 1;
        this.name = "male";
        break;

      case "female":
        this.id = 2;
        this.name = "female";
        break;

      case "diverse":
        this.id = 3;
        this.name = "diverse";
        break;

      default:
        this.id = 4;
        this.name = "not specified";
        break;
    }
  }

}
