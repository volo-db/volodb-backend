package dev.urner.volodb.entity;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import dev.urner.volodb.service.VolunteerStatusService;


public class VolunteerDeserializer extends StdDeserializer {

  private final VolunteerStatusService vss;

  @Autowired
  protected VolunteerDeserializer(VolunteerStatusService vss) {
    this(null, vss);
  }

  
  protected VolunteerDeserializer(Class<?> vc, VolunteerStatusService vss) {
    super(vc);
    this.vss = vss;
  }

  @Override
  public Volunteer deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    
    Person myPerson = new Person();
    VolunteerStatus myVoloStatus;
    Volunteer myVolunteer = new Volunteer();
    
    JsonNode node = jp.getCodec().readTree(jp);
    
    myPerson.setFirstname(node.get("firstname").asText());
    myPerson.setSurname(node.get("surname").asText());
    myPerson.setGender(new Gender(node.get("gender").asText()));
    
    myVoloStatus = vss.findByName(node.get("status").asText());
    
    myVolunteer.setPerson(myPerson);
    myVolunteer.setOrganisationalId(node.get("organisationalId").asText());
    myVolunteer.setCreated(LocalDateTime.parse( node.get("created").asText()));
    myVolunteer.setStatus(myVoloStatus);

    return myVolunteer;

  }
  
}
