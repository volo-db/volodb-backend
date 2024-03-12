package dev.urner.volodb.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "volunteer")
@Getter
@Setter
@RequiredArgsConstructor
@JsonDeserialize(using = VolunteerDeserializer.class)
public class Volunteer {

  // define fields
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "created")
  private LocalDateTime created;

  @Column(name = "organisationalId")
  private String organisationalId;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "person") // FK
  @JsonUnwrapped
  @JsonIgnoreProperties("id")
  private Person person;

  @ManyToOne
  @JoinColumn(name = "status") // FK
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
  @JsonIdentityReference(alwaysAsId = true)
  private VolunteerStatus status;

  @Column(name = "birthday")
  private LocalDate birthday;

  @Column(name = "birthplace")
  private String birthplace;

  @ManyToOne
  @JoinColumn(name = "nationality") // FK
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "nationality")
  @JsonIdentityReference(alwaysAsId = true)
  private Country nationality;

  @Column(name = "socialInsuranceNumber")
  private String socialInsuranceNumber;

  @ManyToOne
  @JoinColumn(name = "healthInsurance") // FK
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
  @JsonIdentityReference(alwaysAsId = true)
  private HealthInsurance healthInsurance;

  @Column(name = "taxNumber")
  private String taxNumber;

  @ManyToOne
  @JoinColumn(name = "religion") // FK
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
  @JsonIdentityReference(alwaysAsId = true)
  private Religion religion;

  @Column(name = "bankName")
  private String bankName;

  @Column(name = "iban")
  private String iban;

  @Column(name = "bic")
  private String bic;

  @Column(name = "accountHolder")
  private String accountHolder;

  @ManyToOne
  @JoinColumn(name = "levelOfSchoolEdu") // FK
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
  @JsonIdentityReference(alwaysAsId = true)
  private SchoolEdu levelOfSchoolEdu;

  @ManyToOne
  @JoinColumn(name = "levelOfVocationalEdu") // FK
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
  @JsonIdentityReference(alwaysAsId = true)
  private VocationalEdu levelOfVocationalEdu;

  @Column(name = "ongoingLegalProceedings")
  private boolean ongoingLegalProceedings;

}
