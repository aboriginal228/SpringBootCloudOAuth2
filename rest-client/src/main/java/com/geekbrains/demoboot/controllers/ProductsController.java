package com.geekbrains.demoboot.controllers;

import com.geekbrains.demoboot.Spec.ProdSpec;
import com.geekbrains.demoboot.entities.Product;
import com.geekbrains.demoboot.services.ProductsService;
import com.geekbrains.demoboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductsController {

    private ProductsService productsService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showProductsList(Principal principal, Model model, @RequestParam(name = "filter", required = false) String filter,
                                   @RequestParam(name = "page", defaultValue = "0") int page) {

        Product product = new Product();
        Specification<Product> playerSpec = Specification.where(null);

        if (filter != null) {
            playerSpec = playerSpec.and(ProdSpec.nameContains(filter));
        }
        model.addAttribute("products", productsService.getAllProducts(playerSpec, PageRequest.of(page, 20)));
        model.addAttribute("filter", filter);
        model.addAttribute("product", product);
        model.addAttribute("mostv", productsService.get3MostViewed());

        if(principal != null) model.addAttribute("principal", userService.findByUserName(principal.getName()));

        return "products";
    }

    @GetMapping("/feigh")
    @ResponseBody
    public Map<String, Object> getMap(@RequestParam(name = "filter", required = false) String filter,
                                      @RequestParam(name = "page", defaultValue = "0") int page) {

        Product product = new Product();
        Map<String, Object> map = new HashMap<>();
        Specification<Product> playerSpec = Specification.where(null);

        if (filter != null) {
            playerSpec = playerSpec.and(ProdSpec.nameContains(filter));
        }
        map.put("products", productsService.getAllProducts(playerSpec, PageRequest.of(page, 20)));
        map.put("filter", filter);
        map.put("product", product);
        map.put("mostv", productsService.get3MostViewed());

        List<Product> products = (List<Product>) map.get("products");
        System.out.println(products.get(1).getClass());

        return map;
    }

    @PostMapping("/feigh/add")
    @ResponseBody
    public String addProductFeigh(@RequestBody Product product) {
        productsService.add(product);
        System.out.println("123");
        return "success";
    }

    @PostMapping("/add")
    @Secured("ROLE_ADMIN")
    public String addProduct(@ModelAttribute(value = "product")Product product) {
        productsService.add(product);
        return "redirect:/products";
    }

    @GetMapping("/show/{id}")
    @Secured("ROLE_ADMIN")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        model.addAttribute("product", product);
        return "product-page";
    }

    @GetMapping("/delete/{id}")
    @Secured("ROLE_ADMIN")
    public String deleteOneProduct(Model model, @PathVariable(value = "id") Long id) {
        productsService.remove(id);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String getId(@PathVariable(value = "id") Long id){
        return productsService.getById(id).getTitle();
    }

    @RequestMapping("/greeting")
    @ResponseBody
    public String greeting() {
        return "String.format)!!!!";
    }


}
