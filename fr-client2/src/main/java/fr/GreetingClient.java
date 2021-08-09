package fr;

import feign.Headers;
import fr.entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "geek-spring-cloud-eureka-client")
public interface GreetingClient {

    String AUTH_TOKEN = "Authorization";

    @RequestMapping("/demo/products/greeting")
    String greeting();

    @GetMapping("/demo/products/feigh")
    @Headers("Content-Type: application/json")
    Map<String, Object> showProductsList(@RequestParam(name = "filter", required = false) String filter,
                                         @RequestParam(name = "page", defaultValue = "0") int page, @RequestHeader(AUTH_TOKEN) String bearerToken);

    @PostMapping("/demo/products/feigh/add")
    String addProductFeigh(@ModelAttribute(value = "product")Product product);

//    @PostMapping("/demo/feigh/add")
//    String addProductFeigh();
}
