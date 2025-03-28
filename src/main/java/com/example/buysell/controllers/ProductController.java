package com.example.buysell.controllers;

import com.example.buysell.dto.product.ProductDto;
import com.example.buysell.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String products(@RequestParam(name = "searchWord", required = false) String title, Principal principal, Model model) {
        List<ProductDto> productDtos = productService.listProducts(title);

        model.addAttribute("products", productDtos); // Теперь передаём DTO
        model.addAttribute("user", principal.getName());
        model.addAttribute("searchWord", title);

        return "products";
    }
}
