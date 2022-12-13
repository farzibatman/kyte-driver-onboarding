package com.jmalik.kyte.dto;

import com.jmalik.kyte.enums.DriverStatus;
import com.jmalik.kyte.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto {

  private UUID id;
  private String name;
  private String contactNumber;
  private String driversLicense;
  private DriverStatus status;
  private VehicleType vehicleType;
  private String vehicleNumber;
}
