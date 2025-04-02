package com.example.buysell.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String GO_API_URL = "http://localhost:8081/products";

    public Object[] getAllProducts() {
        ResponseEntity<Object[]> response = restTemplate.getForEntity(GO_API_URL, Object[].class);
        return response.getBody();
    }

    public Object getProductById(Long id) {
        String url = GO_API_URL + "/" + id;
        ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
        return response.getBody();
    }

    public void createProduct(Object product) {
        restTemplate.postForEntity(GO_API_URL, product, Void.class);
    }

    public void updateProduct(Long id, Object product) {
        String url = GO_API_URL + "/" + id;
        restTemplate.put(url, product);
    }

    public void deleteProduct(Long id) {
        String url = GO_API_URL + "/" + id;
        restTemplate.delete(url);
    }
}