package dev.urner.volodb.rest;


public class VolunteerErrorResponse {
  
  private int status;

  private String message;

  private long timeStamp;

  // define Constructors
  public VolunteerErrorResponse() {
  }

  public VolunteerErrorResponse(int status, String message, long timeStamp) {
    this.status = status;
    this.message = message;
    this.timeStamp = timeStamp;
  }

  // define getter/setter
  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public long getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(long timeStamp) {
    this.timeStamp = timeStamp;
  }

  
}
