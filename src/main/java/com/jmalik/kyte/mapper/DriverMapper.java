package com.jmalik.kyte.mapper;

import com.jmalik.kyte.dto.DriverDto;
import com.jmalik.kyte.entity.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper implements BaseMapper<Driver, DriverDto> {

  @Override
  public DriverDto mapToDto(Driver driver) {
    return DriverDto.builder()
        .id(driver.getId())
        .name(driver.getName())
        .contactNumber(driver.getContactNumber())
        .status(driver.getStatus())
        .vehicleType(driver.getVehicleType())
        .vehicleNumber(driver.getVehicleNumber())
        .driversLicense(driver.getDriversLicense())
        .build();
  }

  @Override
  public Driver mapToEntity(DriverDto driverDto) {
    Driver driver = new Driver();
    driver.setName(driverDto.getName());
    driver.setContactNumber(driverDto.getContactNumber());
    driver.setStatus(driverDto.getStatus());
    driver.setVehicleType(driverDto.getVehicleType());
    driver.setVehicleNumber(driverDto.getVehicleNumber());
    driver.setDriversLicense(driverDto.getDriversLicense());
    return driver;
  }

}
