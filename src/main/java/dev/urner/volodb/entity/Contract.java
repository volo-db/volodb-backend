package dev.urner.volodb.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import dev.urner.volodb.converter.HashMapConverter;
import jakarta.persistence.*;


@Entity
@Table(name="contract")

public class Contract {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "timestamp")
  private LocalDateTime dateOfCreation;

  @ManyToOne
  @JoinColumn(name = "volunteer") //FK
  private Volunteer volunteer;

  @ManyToOne
  @JoinColumn(name = "program") //FK
  private Program program;

  @Column(name = "project") //FK
  private int project;

  @Column(name = "contactPersonOfProject") //FK
  private int contactPerson;

  @Column(name = "start")
  private LocalDate start;

  @Column(name = "end")
  private LocalDate end;

  @Column(name = "visaNecessary")
  private boolean visaNecessary;

  @Column(name = "incomingVolunteer")
  private boolean incomingVolunteer;

  @Column(name = "salary") //FK
  private int salary;

  @Column(name = "holiday")
  private int holiday;

  @Convert(converter = HashMapConverter.class)
  @Column(name = "metadata") //ToDo: JSON-Abhängigkeit
  private Map<String, Object> metadata;



  // define getter/setter

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDateTime getDateOfCreation() {
    return dateOfCreation;
  }

  public void setDateOfCreation(LocalDateTime dateOfCreation) {
    this.dateOfCreation = dateOfCreation;
  }

  public Volunteer getVolunteer() {
    return volunteer;
  }

  public void setVolunteer(Volunteer volunteer) {
    this.volunteer = volunteer;
  }

  public Program getProgram() {
    return program;
  }

  public void setProgram(Program program) {
    this.program = program;
  }

  public int getProject() {
    return project;
  }

  public void setProject(int project) {
    this.project = project;
  }

  public int getContactPerson() {
    return contactPerson;
  }

  public void setContactPerson(int contactPerson) {
    this.contactPerson = contactPerson;
  }

  public LocalDate getStart() {
    return start;
  }

  public void setStart(LocalDate start) {
    this.start = start;
  }

  public LocalDate getEnd() {
    return end;
  }

  public void setEnd(LocalDate end) {
    this.end = end;
  }

  public boolean isVisaNecessary() {
    return visaNecessary;
  }

  public void setVisaNecessary(boolean visaNecessary) {
    this.visaNecessary = visaNecessary;
  }

  public boolean isIncomingVolunteer() {
    return incomingVolunteer;
  }

  public void setIncomingVolunteer(boolean incomingVolunteer) {
    this.incomingVolunteer = incomingVolunteer;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public int getHoliday() {
    return holiday;
  }

  public void setHoliday(int holiday) {
    this.holiday = holiday;
  }

  public Map<String, Object> getMetadata() {
    return metadata;
  }

  public void setMetadata(Map<String, Object> metadata) {
    this.metadata = metadata;
  }





}
