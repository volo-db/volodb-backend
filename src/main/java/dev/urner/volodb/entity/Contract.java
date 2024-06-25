package dev.urner.volodb.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import dev.urner.volodb.converter.HashMapConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "contract")
@Getter
@Setter
@RequiredArgsConstructor
@JsonPropertyOrder({ "id", "created", "volunteerId", "projectId", "program", "start", "end", "visaNecessary",
    "incomingVolunteer", "contactPerson", "holiday", "salary", "metadata" })
public class Contract {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "timestamp")
  @JsonProperty("created")
  private LocalDateTime dateOfCreation;

  @ManyToOne
  @JoinColumn(name = "volunteer") // FK
  @JsonProperty("volunteerId")
  @JsonAlias({ "volunteerId", "volunteer" })
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
  @JsonIdentityReference(alwaysAsId = true)
  private Volunteer volunteer;

  @ManyToOne
  @JoinColumn(name = "program") // FK
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "shorthand")
  @JsonIdentityReference(alwaysAsId = true)
  private Program program;

  @ManyToOne
  @JoinColumn(name = "project") // FK
  private Project project;

  @ManyToOne
  @JoinColumn(name = "contactPersonOfProject") // FK
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
  @JsonIdentityReference(alwaysAsId = true)
  private ContactPerson contactPerson;

  @Column(name = "start")
  private LocalDate start;

  @Column(name = "end")
  private LocalDate end;

  @Column(name = "visaNecessary")
  private boolean visaNecessary;

  @Column(name = "incomingVolunteer")
  private boolean incomingVolunteer;

  @ManyToOne
  @JoinColumn(name = "salary") // FK
  private Salary salary;

  @Column(name = "holiday")
  private int holiday;

  @Column(name = "seminarDays")
  private int seminarDays;

  @Convert(converter = HashMapConverter.class)
  @Column(name = "metadata") // ToDo: JSON-Abhängigkeit
  private Map<String, Object> metadata;

  @OneToOne(mappedBy = "contract")
  private LegalGuardian legalGuardian;
}
