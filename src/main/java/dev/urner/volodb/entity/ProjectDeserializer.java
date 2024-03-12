package dev.urner.volodb.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import dev.urner.volodb.service.CountryService;

public class ProjectDeserializer extends StdDeserializer {

  private final CountryService cs;

  @Autowired
  protected ProjectDeserializer(CountryService cs) {
    this(null, cs);
  }

  protected ProjectDeserializer(Class<?> vc, CountryService cs) {
    super(vc);
    this.cs = cs;
  }

  @Override
  public Project deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

    Project myProject = new Project();

    JsonNode node = jp.getCodec().readTree(jp);

    /*
     * ALL FIELDS:
     * ---------------------------
     * organisationalId
     * name (*)
     * shorthand (*)
     * description
     * capacity
     * careof
     * country (*) <- has to be in correct (only-english) form
     * state
     * street (*)
     * postal_code (*)
     * city (*)
     * phone (*)
     * email (*)
     * ---------------------------
     */

    // Check wheather all required fields are set and extend the errorMessage if
    // necessary
    List<String> errorMessages = new ArrayList<String>();

    if ((!node.has("name")) || node.get("name").asText() == "") {
      errorMessages.add("field 'name' is emty but has to be set");
    }

    if ((!node.has("shorthand")) || node.get("shorthand").asText() == "") {
      errorMessages.add("field 'shorthand' is emty but has to be set");
    }

    if ((!node.has("country")) || node.get("country").asText() == "") {
      errorMessages.add("field 'country' is emty but has to be set");
    } else {
      try {
        cs.findByName(node.get("country").asText());
      } catch (Exception e) {
        errorMessages
            .add("field 'country' has invalid country-name or format (has to be in english form e.g. 'Germany')");
      }
    }

    if ((!node.has("street")) || node.get("street").asText() == "") {
      errorMessages.add("field 'street' is emty but has to be set");
    }

    if ((!node.has("postalCode")) || node.get("postalCode").asText() == "") {
      errorMessages.add("field 'postalCode' is emty but has to be set");
    }

    if ((!node.has("city")) || node.get("city").asText() == "") {
      errorMessages.add("field 'city' is emty but has to be set");
    }

    if ((!node.has("phone")) || node.get("phone").asText() == "") {
      errorMessages.add("field 'phone' is emty but has to be set");
    }

    if ((!node.has("email")) || node.get("email").asText() == "") {
      errorMessages.add("field 'email' is emty but has to be set");
    }

    if (!(errorMessages.size() == 0)) {
      StringJoiner errorMessage = new StringJoiner("\n");

      String leadingMessage = "Invalid JSON format for Project:";

      errorMessage.add(leadingMessage);

      for (String message : errorMessages) {
        errorMessage.add(message);
      }

      throw new ProjectInvalidFormatException(errorMessage.toString());
    }

    // Map JSON-Fields with Project-Object

    // "organisationalId": "ESTSDB001",
    if (node.has("organisationalId")) {
      myProject.setOrganisationalId(node.get("organisationalId").asText());
    }
    // "name": "Aktionszentrum Benediktbeuern",
    myProject.setName(node.get("name").asText());
    // "shorthand": "AZ",
    myProject.setShorthand(node.get("shorthand").asText());
    // "description": "Jugendbildungsstätte bla blub etc. pp!",
    if (node.has("description")) {
      myProject.setDescription(node.get("description").asText());
    }
    // "capacity": 4,
    if (node.has("capacity")) {
      myProject.setCapacity(node.get("capacity").asInt());
    }
    // "careof": null,
    if (node.has("careof")) {
      myProject.setCareof(node.get("careof").asText());
    }
    // "country": "Deutschland",
    myProject.setCountry(cs.findByName(node.get("country").asText()));
    // "state": null,
    if (node.has("state")) {
      myProject.setState(node.get("state").asText());
    }
    // "street": "Don-Bosco-Str. 1",
    myProject.setStreet(node.get("street").asText());
    // "postalCode": "83671",
    myProject.setPostalCode(node.get("postalCode").asText());
    // "city": "Benediktbeuern",
    myProject.setCity(node.get("city").asText());
    // "phone": "0049885788303",
    myProject.setPhone(node.get("phone").asText());
    // "email": "info@aktionszentrum.de"
    myProject.setEmail(node.get("email").asText());

    // Return Project
    return myProject;

  }

}
