package com.navrudh.loadbalancingclient.controller;

import com.navrudh.loadbalancingclient.service.LoadBalancingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

@RestController
public class LoadBalancedController {

  @Autowired private LoadBalancingService loadBalancingService;

  @GetMapping("/instanceId")
  public @ResponseBody String getInstanceId() throws RestClientException, IOException {
    return loadBalancingService.getInstanceId("/instanceId");
  }
}
