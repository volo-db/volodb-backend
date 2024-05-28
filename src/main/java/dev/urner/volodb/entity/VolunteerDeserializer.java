package dev.urner.volodb.entity;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import dev.urner.volodb.entity.Enums.OngoingLegalProceedingsState;
import dev.urner.volodb.exception.VolunteerInvalidFormatException;
import dev.urner.volodb.service.CountryService;
import dev.urner.volodb.service.HealthInsuranceService;
import dev.urner.volodb.service.ReligionService;
import dev.urner.volodb.service.SchoolEduService;
import dev.urner.volodb.service.VocationalEduService;
import dev.urner.volodb.service.VolunteerStatusService;

public class VolunteerDeserializer extends StdDeserializer {

  private final VolunteerStatusService vss;
  private final CountryService cs;
  private final HealthInsuranceService his;
  private final ReligionService rs;
  private final SchoolEduService ses;
  private final VocationalEduService ves;

  @Autowired
  protected VolunteerDeserializer(VolunteerStatusService vss, CountryService cs, HealthInsuranceService his,
      ReligionService rs, SchoolEduService ses, VocationalEduService ves) {
    this(null, vss, cs, his, rs, ses, ves);
  }

  protected VolunteerDeserializer(Class<?> vc, VolunteerStatusService vss, CountryService cs,
      HealthInsuranceService his, ReligionService rs, SchoolEduService ses, VocationalEduService ves) {
    super(vc);
    this.vss = vss;
    this.cs = cs;
    this.his = his;
    this.rs = rs;
    this.ses = ses;
    this.ves = ves;
  }

  @Override
  public Volunteer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

    Person myPerson = new Person();
    VolunteerStatus myVoloStatus = null;
    Volunteer myVolunteer = new Volunteer();

    JsonNode node = jp.getCodec().readTree(jp);

    // Error-Handling:
    List<String> errorMessages = new ArrayList<String>();

    if ((!node.has("organisationalId")) || node.get("organisationalId").asText() == "") {
      errorMessages.add("field 'organisationalId' is emty but has to be set");
    }

    if ((!node.has("person"))) {
      errorMessages.add("field 'person' is emty but has to be set");
    }

    if ((node.has("person"))) {
      JsonNode personNode = node.get("person");

      if ((!personNode.has("gender")) || personNode.get("gender").asText() == "") {
        errorMessages.add("field 'gender' is emty but has to be set");
      }

      if ((!personNode.has("lastname")) || personNode.get("lastname").asText() == "") {
        errorMessages.add("field 'lastname' is emty but has to be set");
      }

      if ((!personNode.has("firstname")) || personNode.get("firstname").asText() == "") {
        errorMessages.add("field 'firstname' is emty but has to be set");
      }
    }

    if (node.has("status")) {
      String statusText = node.get("status").asText();

      try {
        myVoloStatus = vss.findByName(statusText);
      } catch (Exception e) {
        errorMessages.add("Volunteer-Status '" + statusText + "' not found.");
      }
    }

    if (node.has("ongoingLegalProceedings")) {
      if (!(node.get("ongoingLegalProceedings").asText().toLowerCase().equals("true"))
          && !(node.get("ongoingLegalProceedings").asText().toLowerCase().equals("yes"))
          && !(node.get("ongoingLegalProceedings").asText().toLowerCase().equals("false"))
          && !(node.get("ongoingLegalProceedings").asText().toLowerCase().equals("no"))
          && !(node.get("ongoingLegalProceedings").asText().toLowerCase().equals("null"))
          && !(node.get("ongoingLegalProceedings").asText().toLowerCase().equals("not_set")))
        errorMessages.add("ongoingLegalProceedings has to be 'yes', 'true', 'no', 'false', 'not_set' or 'null'");
    }

    if (!(errorMessages.size() == 0)) {
      StringJoiner errorMessage = new StringJoiner("\n");

      String leadingMessage = "Invalid JSON format for Volunteer:";

      errorMessage.add(leadingMessage);

      for (String message : errorMessages) {
        errorMessage.add(message);
      }

      throw new VolunteerInvalidFormatException(errorMessage.toString());
    }

    // -------------------------------------------------
    // Setting Values of VOLUNTEER

    // Created
    myVolunteer.setCreated(LocalDateTime.now());

    // Organisational-ID
    myVolunteer.setOrganisationalId(node.get("organisationalId").asText());

    // PERSON
    myPerson.setFirstname(node.get("person").get("firstname").asText()); // Firstname
    myPerson.setLastname(node.get("person").get("lastname").asText()); // Lastname
    myPerson.setGender(new Gender(node.get("person").get("gender").asText())); // Gender
    myVolunteer.setPerson(myPerson);

    // VolunteerStatus
    myVolunteer.setStatus(myVoloStatus);

    // Birthday
    if (node.has("birthday") && node.get("birthday").asText() != "") {
      myVolunteer.setBirthday(LocalDate.parse(node.get("birthday").asText()));
    }

    // Birthplace
    if (node.has("birthplace") && node.get("birthplace").asText() != "") {
      myVolunteer.setBirthplace(node.get("birthplace").asText());
    }

    // Nationality
    if (node.has("nationality") && node.get("nationality").asText() != "") {
      myVolunteer.setNationality(cs.findByNationalityName(node.get("nationality").asText()));
    }

    // Social Insurance Number
    if (node.has("socialInsuranceNumber") && node.get("socialInsuranceNumber").asText() != "") {
      myVolunteer.setSocialInsuranceNumber(node.get("socialInsuranceNumber").asText());
    }

    // Health Insurance
    if (node.has("healthInsurance") && node.get("healthInsurance").asText() != "") {
      myVolunteer.setHealthInsurance(his.findByName(node.get("healthInsurance").asText()));
    }

    // Tax Number
    if (node.has("taxNumber") && node.get("taxNumber").asText() != "") {
      myVolunteer.setTaxNumber(node.get("taxNumber").asText());
    }

    // Religion
    if (node.has("religion") && node.get("religion").asText() != "") {
      myVolunteer.setReligion(rs.findByName(node.get("religion").asText()));
    }

    // Bank Name
    if (node.has("bankName") && node.get("bankName").asText() != "") {
      myVolunteer.setBankName(node.get("bankName").asText());
    }

    // IBAN
    if (node.has("iban") && node.get("iban").asText() != "") {
      myVolunteer.setIban(node.get("iban").asText());
    }

    // BIC
    if (node.has("bic") && node.get("bic").asText() != "") {
      myVolunteer.setBic(node.get("bic").asText());
    }

    // Account Holder
    if (node.has("accountHolder") && node.get("accountHolder").asText() != "") {
      myVolunteer.setAccountHolder(node.get("accountHolder").asText());
    }

    // Level of School-Education
    if (node.has("levelOfSchoolEdu") && node.get("levelOfSchoolEdu").asText() != "") {
      myVolunteer.setLevelOfSchoolEdu(ses.findByName(node.get("levelOfSchoolEdu").asText()));
    }

    // Level of Vocational-Education
    if (node.has("levelOfVocationalEdu") && node.get("levelOfVocationalEdu").asText() != "") {
      myVolunteer.setLevelOfVocationalEdu(ves.findByName(node.get("levelOfVocationalEdu").asText()));
    }

    // Ongoing Legal Proceedings
    myVolunteer.setOngoingLegalProceedings(OngoingLegalProceedingsState.NOT_SET);
    if (node.has("ongoingLegalProceedings") && node.get("ongoingLegalProceedings").asText() != "") {
      String value = node.get("ongoingLegalProceedings").asText();
      if (value.toLowerCase() == "yes" || value.toLowerCase() == "true")
        myVolunteer.setOngoingLegalProceedings(OngoingLegalProceedingsState.YES);

      if (value.toLowerCase() == "no" || value.toLowerCase() == "false")
        myVolunteer.setOngoingLegalProceedings(OngoingLegalProceedingsState.NO);
    }

    myVolunteer.setAvatar("volodb/default-files/default-avatar.png");
    return myVolunteer;

  }

}
