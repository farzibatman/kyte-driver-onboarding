package com.jmalik.kyte.service;

import com.jmalik.kyte.dto.DriverDto;
import org.springframework.stereotype.Service;

@Service
public class DriverOnboardingProcessService {

  public void initiateOnboardingProcesses(DriverDto driverDto) {
    /*
        Initiate onboarding process(es) in async.
        Maybe Publish to a Queue (like Kafka)
     */
  }
}
