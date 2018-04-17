package com.nomis.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Eugene Tsydzik
 * on 4/14/18.
 */
@Service
@Data
public class PropertyService {

  @Value("${com.rednavis.socket.connectUrl}")
  private String connectUrl;
}