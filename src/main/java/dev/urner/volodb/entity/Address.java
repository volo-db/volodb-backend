package dev.urner.volodb.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import dev.urner.volodb.converter.AddressStatusConverter;
import dev.urner.volodb.entity.Enums.AddressStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
@RequiredArgsConstructor
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @ManyToOne
  @JoinColumn(name = "person")
  private Person person;

  @Column(name = "status")
  @Convert(converter = AddressStatusConverter.class)
  private AddressStatus status;

  @Column(name = "name")
  private String name;

  @Column(name = "careof")
  private String careof;

  @ManyToOne
  @JoinColumn(name = "country")
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
  @JsonIdentityReference(alwaysAsId = true)
  private Country country;

  @Column(name = "state")
  private String state;

  @Column(name = "street")
  private String street;

  @Column(name = "postal_code")
  private String postalcode;

  @Column(name = "city")
  private String city;

}
