package com.jmalik.kyte.facade;

import com.jmalik.kyte.dto.DriverDto;
import com.jmalik.kyte.entity.Driver;
import com.jmalik.kyte.enums.DriverStatus;
import com.jmalik.kyte.service.DriverManagementService;
import com.jmalik.kyte.service.DriverOnboardingProcessService;
import com.jmalik.kyte.utils.DriverValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DriverManagementFacade {

  private final DriverManagementService driverManagementService;
  private final DriverOnboardingProcessService driverOnboardingProcessService;

  public DriverDto onboardDriver(DriverDto driverDto) {
    log.info("Onboarding Driver {}", driverDto);
    DriverValidator.validateCreation(driverDto);
    driverDto = driverManagementService.save(driverDto);
    driverOnboardingProcessService.initiateOnboardingProcesses(driverDto);
    log.info("[DriverId = {}] Initiated Onboarding Processes for Driver", driverDto.getId());
    return driverDto;
  }

  public DriverDto updateDriverData(UUID driverId, DriverDto driverDto) {
    Driver driver = driverManagementService.get(driverId);
    log.info("[DriverId = {}] Updating Driver", driverDto.getId());
    DriverValidator.validateUpdate(driverDto, driver);
    return driverManagementService.update(driverId, driverDto);
  }

  public DriverDto updateStatus(UUID driverId, DriverStatus driverStatus) {
    Driver driver = driverManagementService.get(driverId);
    log.info("[DriverId = {}] Updating Driver Status", driverId);
    DriverValidator.validateStatusChange(driver.getStatus(), driverStatus);
    return driverManagementService.updateStatus(driverId, driverStatus);
  }
}
