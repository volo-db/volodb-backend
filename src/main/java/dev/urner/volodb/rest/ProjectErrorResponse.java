package dev.urner.volodb.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ProjectErrorResponse {
  private int status;

  private String message;

  private long timeStamp;
}
