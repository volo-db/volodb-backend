package dev.urner.volodb.entity;

import java.util.List;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="UserRole")
@Getter
@Setter
public class UserRole {
  
  //define fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  // //user_roles
  // @ManyToMany(mappedBy = "roles")
  // private List<User> users;
  
}
