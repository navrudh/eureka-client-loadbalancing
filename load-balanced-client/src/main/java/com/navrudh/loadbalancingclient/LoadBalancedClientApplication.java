package com.navrudh.loadbalancingclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoadBalancedClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(LoadBalancedClientApplication.class, args);
  }
}
