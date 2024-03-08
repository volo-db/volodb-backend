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
    myVolunteer.setCreated(LocalDateTime.now());

    JsonNode node = jp.getCodec().readTree(jp);

    List<String> errorMessages = new ArrayList<String>();

    if ((!node.has("organisationalId")) || node.get("organisationalId").asText() == "") {
      errorMessages.add("field 'organisationalId' is emty but has to be set");
    }

    if ((!node.has("gender")) || node.get("gender").asText() == "") {
      errorMessages.add("field 'gender' is emty but has to be set");
    }

    if ((!node.has("lastname")) || node.get("lastname").asText() == "") {
      errorMessages.add("field 'lastname' is emty but has to be set");
    }

    if ((!node.has("firstname")) || node.get("firstname").asText() == "") {
      errorMessages.add("field 'firstname' is emty but has to be set");
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
      if (!(node.get("ongoingLegalProceedings").asText() == "true")
          && !(node.get("ongoingLegalProceedings").asText() == "false"))
        errorMessages.add("ongoingLegalProceedings has to be true or false");
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

    myVolunteer.setOrganisationalId(node.get("organisationalId").asText());

    myPerson.setFirstname(node.get("firstname").asText());
    myPerson.setLastname(node.get("lastname").asText());
    myPerson.setGender(new Gender(node.get("gender").asText()));
    myVolunteer.setPerson(myPerson);

    myVolunteer.setStatus(myVoloStatus);

    if (node.has("birthday") && node.get("birthday").asText() != "") {
      myVolunteer.setBirthday(LocalDate.parse(node.get("birthday").asText()));
    }
    if (node.has("birthplace") && node.get("birthplace").asText() != "") {
      myVolunteer.setBirthplace(node.get("birthplace").asText());
    }
    if (node.has("nationality") && node.get("nationality").asText() != "") {
      myVolunteer.setNationality(cs.findByNationalityName(node.get("nationality").asText()));
    }
    if (node.has("socialInsuranceNumber") && node.get("socialInsuranceNumber").asText() != "") {
      myVolunteer.setSocialInsuranceNumber(node.get("socialInsuranceNumber").asText());
    }
    if (node.has("healthInsurance") && node.get("healthInsurance").asText() != "") {
      myVolunteer.setHealthInsurance(his.findByName(node.get("healthInsurance").asText()));
    }
    if (node.has("taxNumber") && node.get("taxNumber").asText() != "") {
      myVolunteer.setTaxNumber(node.get("taxNumber").asText());
    }
    if (node.has("religion") && node.get("religion").asText() != "") {
      myVolunteer.setReligion(rs.findByName(node.get("religion").asText()));
    }
    if (node.has("bankName") && node.get("bankName").asText() != "") {
      myVolunteer.setBankName(node.get("bankName").asText());
    }
    if (node.has("iban") && node.get("iban").asText() != "") {
      myVolunteer.setIban(node.get("iban").asText());
    }
    if (node.has("bic") && node.get("bic").asText() != "") {
      myVolunteer.setBic(node.get("bic").asText());
    }
    if (node.has("accountHolder") && node.get("accountHolder").asText() != "") {
      myVolunteer.setAccountHolder(node.get("accountHolder").asText());
    }
    if (node.has("levelOfSchoolEdu") && node.get("levelOfSchoolEdu").asText() != "") {
      myVolunteer.setLevelOfSchoolEdu(ses.findByName(node.get("levelOfSchoolEdu").asText()));
    }
    if (node.has("levelOfVocationalEdu") && node.get("levelOfVocationalEdu").asText() != "") {
      myVolunteer.setLevelOfVocationalEdu(ves.findByName(node.get("levelOfVocationalEdu").asText()));
    }
    if (node.has("ongoingLegalProceedings") && node.get("ongoingLegalProceedings").asText() != "") {
      myVolunteer.setOngoingLegalProceedings(Boolean.parseBoolean(node.get("ongoingLegalProceedings").asText()));
    }

    return myVolunteer;

  }

}
