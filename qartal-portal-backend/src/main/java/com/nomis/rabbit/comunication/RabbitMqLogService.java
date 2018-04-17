package com.nomis.rabbit.comunication;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Created by Alexander Sokolov
 * on 4/17/18.
 */
@Component
public class RabbitMqLogService {

  private  List<String> logs=new  ArrayList<>();

  public void pushLog(String message) {
    if (logs==null)  logs=new ArrayList<>();
    logs.add(message);
  }

  public List<String> getLogs() {
    List<String> currentlogs=logs;
    logs=null;
    return currentlogs;
  }
}
