package com.lvy.springboot.controller;

import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rest/hc")
public class HazelcastController {

  private final Logger logger = LoggerFactory.getLogger(HazelcastController.class);
  @Autowired
  private HazelcastInstance hazelcastInstance;

  @GetMapping(value = "/writeMap")
  public String writeDataToHazelcast(@RequestParam String key, @RequestParam String value) {
    Map<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
    hazelcastMap.put(key, value);
    return "Data is stored.";
  }

  @GetMapping(value = "/readMap")
  public String readDataFromHazelcast(@RequestParam String key) {
    Map<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
    return hazelcastMap.get(key);
  }

  @GetMapping(value = "/readAll")
  public Map<String, String> readAllDataFromHazelcast() {
    Map<String, String> hazelcastMap = hazelcastInstance.getMap("my-map");
    return hazelcastInstance.getMap("my-map");
  }

  @GetMapping(value = "/writeList")
  public String readList() {
   List<String> list = hazelcastInstance.getList("myList");
   list.add("测试1");
   list.add("测试2");
    return "success";
  }

  @GetMapping(value = "/readList")
  public List<String> getList() {
    List<String> list = hazelcastInstance.getList("myList");
    return list;
  }

}