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

  /**
   * PUT Api to update an existing Driver entry.
   * This API validates the request and updates the updatable data.
   * This API expects OAuth2 Header, that validates request comes from the same user.
   *
   * @param driverId - the ID of driver to update
   * @param driverDto - the details of driver
   * @return Response<DriverDto> with updated Driver Data
   *          or error description upon failure.
   */
  @PutMapping(value = "/{driverId}")
  // @UserManagementAuth - to validate Oauth2 header, and to ensure the request
  //                        came from the user to update
  public Response<DriverDto> update(@PathVariable(value = "driverId") UUID driverId,
                                    @RequestBody DriverDto driverDto) {
    return Response.success(driverManagementFacade.updateDriverData(driverId, driverDto));
  }

  /**
   * PUT Api to update Driver status.
   * This API validates the request and updates the Driver Status.
   * This API expects OAuth2 Header, that validates request comes from the same user.
   *
   * @param driverId - the ID of driver to update
   * @param driverStatus - the status to update to
   * @return Response<DriverDto> with updated Driver Data
   *          or error description upon failure.
   */
  @PutMapping(value = "/{driverId}/status/{status}")
  // @UserManagementAuth - to validate Oauth2 header, and to ensure the request
  //                        came from the user to update
  public Response<DriverDto> updateStatus(@PathVariable(value = "driverId") UUID driverId,
                                          @PathVariable(value = "status") DriverStatus driverStatus) {
    return Response.success(driverManagementFacade.updateStatus(driverId, driverStatus));
  }
}
