package dev.urner.volodb.entity;

import java.io.IOException;

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

    // "organisationalId": "ESTSDB001",
    myProject.setOrganisationalId(node.get("organisationalId").asText());
    // "name": "Aktionszentrum Benediktbeuern",
    myProject.setName(node.get("name").asText());
    // "shorthand": "AZ",
    myProject.setShorthand(node.get("shorthand").asText());
    // "description": "Jugendbildungsstätte bla blub etc. pp!",
    myProject.setDescription(node.get("description").asText());
    // "capacity": 4,
    myProject.setCapacity(node.get("capacity").asInt());
    // "careof": null,
    myProject.setCareof(node.get("careof").asText());
    // "country": "Deutschland",
    myProject.setCountry(cs.findByName(node.get("country").asText()));
    // "state": null,
    myProject.setState(node.get("state").asText());
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

    return myProject;

  }

}
