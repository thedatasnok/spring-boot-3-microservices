package cool.datasnok.samples.microservices.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class GatewayApplication {
  
  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
  }

  @GetMapping
  public String getMetadata() {
    return "Gateway Microservice";
  }

  private static final String STOCK_URL = "http://localhost:8081/";
  private static final String USER_URL = "http://localhost:8082/";

  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder builder) {
    return builder.routes()
      .route(r -> r
        .host("stock.localhost:8080")
        .uri(STOCK_URL)
      )
      .route(r -> r
        .host("user.localhost:8080")
        .uri(USER_URL)
      )
      .route(r -> r
        .path("/stock/**")
        .filters(f -> f.rewritePath("/stock/(?<segment>.*)", "/${segment}"))
        .uri(STOCK_URL)
      )
      .route(r -> r
        .path("/user/**")
        .filters(f -> f.rewritePath("/user/(?<segment>.*)", "/${segment}"))
        .uri(USER_URL)
      )
      .build();
  }

}
