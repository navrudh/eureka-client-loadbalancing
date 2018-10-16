package com.navrudh.microservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockEndpointController {

  @Value("${eureka.instance.instanceId}")
  private String instanceId;

  @GetMapping("/instanceId")
  public @ResponseBody String getInstanceId() {
    return instanceId;
  }
}
