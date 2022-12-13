package com.jmalik.kyte.entity;

import com.jmalik.kyte.enums.DriverStatus;
import com.jmalik.kyte.enums.VehicleType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "driver")
@EqualsAndHashCode(callSuper = true)
public class Driver extends BaseAuditableEntity {

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "contact_number", unique = true, nullable = false)
  private String contactNumber;

  @Column(name = "drivers_license", unique = true, nullable = false)
  private String driversLicense;

  @Column(name = "status", nullable = false)
  private DriverStatus status;

  @Column(name = "vehicle_type", nullable = false)
  private VehicleType vehicleType;

  @Column(name = "vehicle_number", unique = true, nullable = false)
  private String vehicleNumber;
}
