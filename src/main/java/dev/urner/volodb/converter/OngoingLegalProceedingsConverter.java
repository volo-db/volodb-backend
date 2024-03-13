package dev.urner.volodb.converter;

import dev.urner.volodb.entity.Enums.OngoingLegalProceedingsState;
import jakarta.persistence.AttributeConverter;

public class OngoingLegalProceedingsConverter implements AttributeConverter<OngoingLegalProceedingsState, Boolean> {

  @Override
  public Boolean convertToDatabaseColumn(OngoingLegalProceedingsState state) {
    switch (state) {
      case YES:
        return true;

      case NO:
        return false;

      default:
        return null;
    }
  }

  @Override
  public OngoingLegalProceedingsState convertToEntityAttribute(Boolean dbData) {
    if (dbData == null)
      return OngoingLegalProceedingsState.NOT_SET;

    return dbData ? OngoingLegalProceedingsState.YES : OngoingLegalProceedingsState.NO;

  }

}
