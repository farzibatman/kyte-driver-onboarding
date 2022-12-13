package com.jmalik.kyte.utils;

import com.jmalik.kyte.exception.BadRequestException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class BaseValidator {

  private static final String IS_NULL_MESSAGE_FORMAT = "%s is null";
  private static final String IS_BLANK_MESSAGE_FORMAT = "%s is blank";
  private static final String CHANGE_NOT_ALLOWED_MESSAGE_FORMAT = "%s field is not updatable";


  private BaseValidator() {}

  public static void validateNotNull(Object object, String objectName) {
    if (object == null) {
      throw new BadRequestException(IS_NULL_MESSAGE_FORMAT, objectName);
    }
  }

  public static void validateNotBlank(String value, String objectName) {
    if ( StringUtils.isBlank(value)) {
      throw new BadRequestException(IS_BLANK_MESSAGE_FORMAT, objectName);
    }
  }

  public static void validateEquals(Object object1, Object object2, String objectName) {
    if (!Objects.equals(object1, object2)) {
      throw new BadRequestException(CHANGE_NOT_ALLOWED_MESSAGE_FORMAT, objectName);
    }
  }
}
