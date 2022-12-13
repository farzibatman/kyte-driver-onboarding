package com.jmalik.kyte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.jmalik.kyte")
public class DriverManagementApp {
  public static void main(String[] args) {
    SpringApplication.run(DriverManagementApp.class, args);
  }
}