package com.jmalik.kyte.controller;

import com.jmalik.kyte.dto.DriverDto;
import com.jmalik.kyte.enums.DriverStatus;
import com.jmalik.kyte.facade.DriverManagementFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
public class DriverManagementController {

  private final DriverManagementFacade driverManagementFacade;

  /**
   * POST Api to create a new Driver entry.
   * This API validates the request and creates a new DB entry.
   * This API expects "api_key" Header, that validates request comes from the app.
   *
   * @param driverDto - the details of driver to onboard
   * @return Response<DriverDto> with created Driver Data
   *          or error description upon failure.
   */
  @PostMapping
  // @ApiKeyAuth - to validate api_key header
  public Response<DriverDto> create(@RequestBody DriverDto driverDto) {
    return Response.success(driverManagementFacade.onboardDriver(driverDto));
  }

  @PutMapping(value = "/{driverId}")
  // @UserManagementAuth - to validate Oauth2 header, and to ensure the request
  //                        came from the user to update
  public Response<DriverDto> update(@PathVariable(value = "driverId") UUID driverId,
                                    @RequestBody DriverDto driverDto) {
    return Response.success(driverManagementFacade.updateDriverData(driverId, driverDto));
  }

  @PutMapping(value = "/{driverId}/status/{status}")
  // @UserManagementAuth - to validate Oauth2 header, and to ensure the request
  //                        came from the user to update
  public Response<DriverDto> updateStatus(@PathVariable(value = "driverId") UUID driverId,
                                          @PathVariable(value = "status") DriverStatus driverStatus) {
    return Response.success(driverManagementFacade.updateStatus(driverId, driverStatus));
  }
}
