package dev.urner.volodb.rest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class VolodbErrorResponse {
  private final int status;
  private final String message;
  private final long timeStamp;
}
