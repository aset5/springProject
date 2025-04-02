package com.example.buysell.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String GO_API_URL = "http://localhost:8081/products";

    @GetMapping
    public String products(@RequestParam(name = "searchWord", required = false) String title,
                           Principal principal,
                           Model model) {
        ResponseEntity<Object[]> response = restTemplate.getForEntity(GO_API_URL, Object[].class);
        model.addAttribute("products", response.getBody());
        model.addAttribute("user", principal != null ? principal.getName() : "Guest");
        return "products";
    }

    @GetMapping("/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        String url = GO_API_URL + "/" + id;
        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
        model.addAttribute("product", response.getBody());
        return "product-info";
    }

    @PostMapping
    public String createProduct(@RequestBody Object product) {
        restTemplate.postForEntity(GO_API_URL, product, Void.class);
        return "redirect:/product";
    }

    @PostMapping("/{id}/update")
    public String updateProduct(@PathVariable Long id, @RequestBody Object product) {
        String url = GO_API_URL + "/" + id;
        restTemplate.put(url, product);
        return "redirect:/product";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        String url = GO_API_URL + "/" + id;
        restTemplate.delete(url);
        return "redirect:/product";
    }
}