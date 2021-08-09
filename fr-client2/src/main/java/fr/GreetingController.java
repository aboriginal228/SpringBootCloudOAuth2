package fr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {
    private GreetingClient greetingClient;

    @Autowired
    public void setGreetingClient(GreetingClient greetingClient) {
        this.greetingClient = greetingClient;
    }

    @RequestMapping("/get-greeting")
    @ResponseBody
    public String greeting(Model model) {
        String answer = greetingClient.greeting();
        model.addAttribute("greeting", answer);
        return answer;
    }

    @GetMapping("/get-products")
    public String products(Model model, @RequestParam(name = "filter", required = false) String filter,
                                        @RequestParam(name = "page", defaultValue = "0") int page) {
        Map<String, Object> map = greetingClient.showProductsList(filter, page, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJhbGV4Iiwic2NvcGUiOlsid2ViY2xpZW50Il0sIm9yZ2FuaXphdGlvbiI6ImFsZXgwLjYxNzEzNTM4NjAyNTQyMDUiLCJpbmZvcm1hdGlvbiI6IndANDM0I0dZZmd5MjIzZ2IzIiwiZXhwIjoxNjI4NTgxNDU1LCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwianRpIjoidk5YekQ5aW5Ec2oxXzliZmFHM3VjXzRnd29ZIiwiY2xpZW50X2lkIjoiZmxhbWV0b2tlbiJ9.00igUB61dd7_DPXyAvjBcD3Ah4eO3w8dWg-m1jCmXME");
        ObjectMapper mapper = new ObjectMapper();
        Product product = new Product();
        model.addAttribute("products", mapper.convertValue(map.get("products"), new TypeReference<List<Product>>() {
        }));
        model.addAttribute("filter", (String) map.get("filter"));
        model.addAttribute("product", product);
        model.addAttribute("mostv", mapper.convertValue(map.get("mostv"), new TypeReference<List<Product>>() {
        }));
        return "products";
    }

    @GetMapping("/add-product")
    @ResponseBody
    public String addProduct(@RequestParam(name = "title") String title,
                             @RequestParam(name = "price") int price,
                             @RequestParam(name = "views") int views) {
        Product product = new Product(title, price, views);
        System.out.println(product);
        greetingClient.addProductFeigh(product);
        return "success";
    }

//    @GetMapping("/add-product")
//    @ResponseBody
//    public String addProduct() {
//        return "success";
//    }

//    @GetMapping("/get-products")
//    public String products(Map<String, Object> model, @RequestParam(name = "filter", required = false) String filter,
//                           @RequestParam(name = "page", defaultValue = "0") int page) {
//        model = greetingClient.showProductsList(filter, page);
//        return "products";
//    }


}
