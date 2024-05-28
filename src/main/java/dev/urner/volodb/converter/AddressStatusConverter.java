package dev.urner.volodb.converter;

import dev.urner.volodb.entity.Enums.AddressStatus;
import dev.urner.volodb.exception.AddressStatusNotFoundException;
import jakarta.persistence.AttributeConverter;

public class AddressStatusConverter implements AttributeConverter<AddressStatus, Integer> {

  @Override
  public Integer convertToDatabaseColumn(AddressStatus status) {
    switch (status) {
      case ACTIVE:
        return 1;

      case INACTIVE:
        return 0;

      default:
        throw new AddressStatusNotFoundException("AddressSatus \"" + status.toString() + "\" is not valid.");
    }
  }

  @Override
  public AddressStatus convertToEntityAttribute(Integer dbData) {
    switch (dbData) {
      case 1:
        return AddressStatus.ACTIVE;

      case 0:
        return AddressStatus.INACTIVE;

      default:
        throw new AddressStatusNotFoundException("AddressSatus \"" + dbData.toString() + "\" is not valid.");
    }
  }

}
