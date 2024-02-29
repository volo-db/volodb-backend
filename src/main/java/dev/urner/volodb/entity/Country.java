package dev.urner.volodb.entity;

import jakarta.persistence.*;

@Entity
@Table(name="country")
public class Country {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private int id;

  @Column(name="iso2")
  private String iso2;

  @Column(name="iso3")
  private String iso3;

  @Column(name="localName")
  private String localName;

  @Column(name="continent")
  private String continent;

  @Column(name="nationality")
  private String nationality;

  // define getter/setter
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getIso2() {
    return iso2;
  }

  public void setIso2(String iso2) {
    this.iso2 = iso2;
  }

  public String getIso3() {
    return iso3;
  }

  public void setIso3(String iso3) {
    this.iso3 = iso3;
  }

  public String getLocalName() {
    return localName;
  }

  public void setLocalName(String localName) {
    this.localName = localName;
  }

  public String getContinent() {
    return continent;
  }

  public void setContinent(String continent) {
    this.continent = continent;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  // define getter/setter 

  
}
