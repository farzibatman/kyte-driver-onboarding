package com.jmalik.kyte.service;

import com.jmalik.kyte.dto.DriverDto;
import com.jmalik.kyte.entity.Driver;
import com.jmalik.kyte.enums.DriverStatus;
import com.jmalik.kyte.exception.ResourceNotFoundException;
import com.jmalik.kyte.mapper.DriverMapper;
import com.jmalik.kyte.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverManagementService {

  private final DriverMapper driverMapper;
  private final DriverRepository driverRepository;

  @Transactional
  public DriverDto save(DriverDto driverDto) {
    Driver driver = driverMapper.mapToEntity(driverDto);
    driver.setStatus(DriverStatus.CREATED);
    driver = save(driver);
    log.info("Created Driver", driverDto.getId());
    return driverMapper.mapToDto(driver);
  }

  public Driver get(UUID driverId) {
    return driverRepository.findById(driverId)
        .orElseThrow(() -> new ResourceNotFoundException("Driver", driverId.toString()));
  }

  public DriverDto update(UUID driverId, DriverDto driverDto) {
    Driver driver = get(driverId);
    driver.setName(driverDto.getName());
    driver.setContactNumber(driverDto.getContactNumber());
    driver.setDriversLicense(driverDto.getDriversLicense());
    driver.setVehicleType(driverDto.getVehicleType());
    driver.setVehicleNumber(driverDto.getVehicleNumber());
    driver = save(driver);
    log.info("[DriverId = {}] Updated Driver", driverDto.getId());
    return driverMapper.mapToDto(driver);
  }

  private Driver save(Driver driver) {
    return driverRepository.save(driver);
  }

  public DriverDto updateStatus(UUID driverId, DriverStatus driverStatus) {
    Driver driver = get(driverId);
    driver.setStatus(driverStatus);
    driver = save(driver);
    log.info("[DriverId = {}] Updated Driver Status", driverId);
    return driverMapper.mapToDto(driver);
  }
}
