package dev.urner.volodb.entity;

import java.util.*;
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
import lombok.Setter;

@Entity
@Table(name="volunteer")
@Getter
@Setter
@JsonDeserialize(using = VolunteerDeserializer.class)
public class Volunteer {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="organisationalId")
    private String organisationalId;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="person") //FK
    @JsonUnwrapped 
    private Person person;

    @Column(name="created")
    private LocalDateTime created;
    
    @ManyToOne
    @JoinColumn(name="status") //FK
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    @JsonIdentityReference(alwaysAsId = true)
    private VolunteerStatus status;

    @Column(name="birthday")
    private LocalDate birthday;
    
    @Column(name="birthplace")
    private String birthplace;

    @ManyToOne
    @JoinColumn(name="nationality") //FK
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "nationality")
    @JsonIdentityReference(alwaysAsId = true)
    private Country nationality;
    
    @Column(name="socialInsuranceNumber")
    private String socialInsuranceNumber;
    
    @ManyToOne
    @JoinColumn(name="healthInsurance") //FK
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    @JsonIdentityReference(alwaysAsId = true)
    private HealthInsurance healthInsurance;
    
    @Column(name="taxNumber")
    private String taxNumber;
    
    @ManyToOne
    @JoinColumn(name="religion") //FK
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    @JsonIdentityReference(alwaysAsId = true)
    private Religion religion;
    
    @Column(name="bankName")
    private String bankName;
    
    @Column(name="iban")
    private String iban;
    
    @Column(name="bic")
    private String bic;
    
    @Column(name="accountHolder")
    private String accountHolder;
    
    @ManyToOne
    @JoinColumn(name="levelOfSchoolEdu") //FK
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    @JsonIdentityReference(alwaysAsId = true)
    private SchoolEdu levelOfSchoolEdu;
    
    @ManyToOne
    @JoinColumn(name="levelOfVocationalEdu") //FK
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "name")
    @JsonIdentityReference(alwaysAsId = true)
    private VocationalEdu levelOfVocationalEdu;
    
    @Column(name="ongoingLegalProceedings")
    private boolean ongoingLegalProceedings;

    @JsonIgnoreProperties("volunteer")
    @OneToMany(mappedBy = "volunteer")
    private List<Contract> contracts;


    // define constructors
    public Volunteer() {

    }
    

    // public Volunteer(String firstName, String lastName, String email) {
    //     this.firstName = firstName;
    //     this.lastName = lastName;
    //     this.email = email;
    // }

    // define getter/setter

  

    public Long getId() {
        return id;
    }


    public String getOrganisationalId() {
      return organisationalId;
    }

    public void setOrganisationalId(String organisationalId) {
      this.organisationalId = organisationalId;
    }

    public Person getPerson() {
      return person;
    }

    public void setPerson(Person person) {
      this.person = person;
    }

    public LocalDateTime getCreated() {
      return created;
    }

    public void setCreated(LocalDateTime created) {
      this.created = created;
    }

    public VolunteerStatus getStatus() {
      return status;
    }

    public void setStatus(VolunteerStatus status) {
      this.status = status;
    }

    public LocalDate getBirthday() {
      return birthday;
    }

    public void setBirthday(LocalDate birthday) {
      this.birthday = birthday;
    }

    public String getBirthplace() {
      return birthplace;
    }

    public void setBirthplace(String birthplace) {
      this.birthplace = birthplace;
    }

    public Country getNationality() {
      return nationality;
    }

    public void setNationality(Country nationality) {
      this.nationality = nationality;
    }

    public String getSocialInsuranceNumber() {
      return socialInsuranceNumber;
    }

    public void setSocialInsuranceNumber(String socialInsuranceNumber) {
      this.socialInsuranceNumber = socialInsuranceNumber;
    }
    public HealthInsurance getHealthInsurance() {
      return healthInsurance;
    }

    public void setHealthInsurance(HealthInsurance healthInsurance) {
      this.healthInsurance = healthInsurance;
    }

    public String getTaxNumber() {
      return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
      this.taxNumber = taxNumber;
    }

    public Religion getReligion() {
      return religion;
    }

    public void setReligion(Religion religion) {
      this.religion = religion;
    }

    public String getBankName() {
      return bankName;
    }

    public void setBankName(String bankName) {
      this.bankName = bankName;
    }

    public String getIban() {
      return iban;
    }

    public void setIban(String iban) {
      this.iban = iban;
    }

    public String getBic() {
      return bic;
    }

    public void setBic(String bic) {
      this.bic = bic;
    }

    public String getAccountHolder() {
      return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
      this.accountHolder = accountHolder;
    }

    public SchoolEdu getLevelOfSchoolEdu() {
      return levelOfSchoolEdu;
    }

    public void setLevelOfSchoolEdu(SchoolEdu levelOfSchoolEdu) {
      this.levelOfSchoolEdu = levelOfSchoolEdu;
    }

    public VocationalEdu getLevelOfVocationalEdu() {
      return levelOfVocationalEdu;
    }

    public void setLevelOfVocationalEdu(VocationalEdu levelOfVocationalEdu) {
      this.levelOfVocationalEdu = levelOfVocationalEdu;
    }

    public boolean isOngoingLegalProceedings() {
      return ongoingLegalProceedings;
    }

    public void setOngoingLegalProceedings(boolean ongoingLegalProceedings) {
      this.ongoingLegalProceedings = ongoingLegalProceedings;
    }

    public List<Contract> getContracts() {
      return contracts;
    }

    public void setContracts(List<Contract> contracts) {
      this.contracts = contracts;
    }

    


    // define toString
    // @Override
    // public String toString() {
    //     return "Employee{" +
    //             "id=" + id +
    //             ", firstName='" + firstName + '\'' +
    //             ", lastName='" + lastName + '\'' +
    //             ", email='" + email + '\'' +
    //             '}';
    //}
}








