package dev.urner.volodb.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "User")
@Getter
@Setter
public class User {

  // Username
  @Id
  @Column(name = "email")
  private String email;

  @OneToOne
  @JoinColumn(name = "person") // FK
  private Person person;

  // Secret
  @JsonIgnore
  @Column(name = "secret")
  private String secret;

  // user_roles
  @ManyToMany
  @JoinTable(name = "user_role_mapping", joinColumns = @JoinColumn(name = "user"), inverseJoinColumns = @JoinColumn(name = "user_role"))
  private List<UserRole> roles;

  // organisational_role
  @Column(name = "organisationalRole")
  private String organisationalRole;

  @Column(name = "avatar")
  private String avatar;

}
