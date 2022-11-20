package cool.datasnok.samples.microservices.stock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/metadata")
public class MetadataController {
  
  @GetMapping
  public String getMetadata() {
    return "Stock Microservice";
  }

}
