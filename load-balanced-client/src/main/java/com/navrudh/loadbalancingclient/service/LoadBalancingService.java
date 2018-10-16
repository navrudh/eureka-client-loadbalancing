package com.navrudh.loadbalancingclient.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoadBalancingService {

  private static org.slf4j.Logger LOG = LoggerFactory.getLogger(LoadBalancingService.class);

  private final LoadBalancerClient loadBalancer;

  private final ObjectMapper mapper;

  @Autowired
  public LoadBalancingService(
      final LoadBalancerClient loadBalancer, final ObjectMapper objectMapper) {
    this.loadBalancer = loadBalancer;
    this.mapper = objectMapper;
  }

  private static HttpEntity<?> getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", MediaType.TEXT_PLAIN_VALUE);
    return new HttpEntity<>(headers);
  }

  public String getInstanceId(String url) throws JsonProcessingException {
    ServiceInstance serviceInstance = loadBalancer.choose("microservice");
    LOG.info("URI: " + serviceInstance.getUri());
    String baseUrl = serviceInstance.getUri().toString() + url;
    RestTemplate restTemplate = new RestTemplate();
    try {
      return restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class).getBody();
    } catch (Exception ex) {
      LOG.error(mapper.writeValueAsString(ex));
    }
    return null;
  }
}
