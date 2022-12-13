package com.jmalik.kyte.utils;

import com.jmalik.kyte.dto.DriverDto;
import com.jmalik.kyte.entity.Driver;
import com.jmalik.kyte.enums.DriverStatus;
import com.jmalik.kyte.exception.BadRequestException;

import java.util.HashMap;
import java.util.Map;

public class DriverValidator {

  private static final String CONTACT_NUMBER_REGEX = "^[789]\\d{9}$";
  private static final String VEHICLE_NUMBER_REGEX = "^[A-Z]{2}[0-9]{1,2}(?:[A-Z])?(?:[A-Z]*)?[0-9]{4}$";
  private static final String IS_INVALID_MESSAGE_FORMAT = "%s is invalid";
  private static final String INVALID_STATUS_TRANSITION_MESSAGE = "Status Transition not allowed";
  private static final Map<DriverStatus, DriverStatus> ALLOWED_STATUS_TRANSITIONS = new HashMap<>();

  static {
    ALLOWED_STATUS_TRANSITIONS.put(DriverStatus.CREATED, DriverStatus.PHONE_VERIFIED);
    ALLOWED_STATUS_TRANSITIONS.put(DriverStatus.PHONE_VERIFIED, DriverStatus.ACTIVE);
  }

  private DriverValidator() {}

  public static void validateCreation(DriverDto driverDto) {
    BaseValidator.validateNotBlank(driverDto.getName(), "Driver Name");
    validatePhoneNumber(driverDto.getContactNumber());
    validateVehicleAndLicenseData(driverDto);
  }

  public static void validateUpdate(DriverDto updateRequest, Driver existing) {
    BaseValidator.validateNotBlank(updateRequest.getName(), "Driver Name");
    BaseValidator.validateEquals(updateRequest.getStatus(), existing.getStatus(), "Driver Status");
    switch (existing.getStatus()) {
      case CREATED :
        validatePhoneNumber(updateRequest.getContactNumber());
        validateVehicleAndLicenseData(updateRequest);
        break;
      case PHONE_VERIFIED:
        BaseValidator.validateEquals(updateRequest.getContactNumber(), existing.getContactNumber(), "Contact Number");
        validateVehicleAndLicenseData(updateRequest);
        break;
      case ACTIVE:
        BaseValidator.validateEquals(updateRequest.getContactNumber(), existing.getContactNumber(), "Contact Number");
        BaseValidator.validateEquals(updateRequest.getDriversLicense(), existing.getDriversLicense(), "Drivers License");
        BaseValidator.validateEquals(updateRequest.getVehicleType(), existing.getVehicleType(), "Vehicle Type");
        BaseValidator.validateEquals(updateRequest.getVehicleNumber(), existing.getVehicleNumber(), "Vehicle Number");
        break;
    }
  }

  private static void validateVehicleAndLicenseData(DriverDto driverDto) {
    BaseValidator.validateNotBlank(driverDto.getDriversLicense(), "Drivers License");
    BaseValidator.validateNotNull(driverDto.getVehicleType(), "Vehicle Type");
    validateVehicleNumber(driverDto.getVehicleNumber());
  }

  private static void validatePhoneNumber(String value) {
    BaseValidator.validateNotBlank(value, "Contact Number");
    if (!value.matches(CONTACT_NUMBER_REGEX)) {
      throw new BadRequestException(IS_INVALID_MESSAGE_FORMAT, "Contact Number");
    }
  }

  private static void validateVehicleNumber(String value) {
    BaseValidator.validateNotBlank(value, "Drivers License");
    if (!value.matches(VEHICLE_NUMBER_REGEX)) {
      throw new BadRequestException(IS_INVALID_MESSAGE_FORMAT, "Drivers License");
    }

  }

  public static void validateStatusChange(DriverStatus currentStatus, DriverStatus updatedStatus) {
    if (updatedStatus != ALLOWED_STATUS_TRANSITIONS.get(currentStatus)) {
      throw new BadRequestException(INVALID_STATUS_TRANSITION_MESSAGE);
    }
  }
}
