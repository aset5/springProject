package com.example.buysell.services;

import com.example.buysell.dto.product.ProductCreateRequestDto;
import com.example.buysell.dto.product.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final RestTemplate restTemplate;
    private static final String GO_API_URL = "http://localhost:8080/products";

    public List<ProductDto> listProducts(String title) {
        String url = (title != null) ? GO_API_URL + "?title=" + title : GO_API_URL;
        ResponseEntity<List<ProductDto>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        return response.getBody() != null ? response.getBody() : Collections.emptyList();
    }

    public ProductDto saveProduct(String jwtToken, ProductCreateRequestDto productDto) {
        HttpHeaders headers = createAuthHeaders(jwtToken);
        HttpEntity<ProductCreateRequestDto> request = new HttpEntity<>(productDto, headers);
        ResponseEntity<ProductDto> response = restTemplate.exchange(
                GO_API_URL, HttpMethod.POST, request, ProductDto.class);

        return response.getBody();
    }

    public void deleteProduct(String jwtToken, Long id) {
        String url = GO_API_URL + "/" + id;
        HttpHeaders headers = createAuthHeaders(jwtToken);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange(url, HttpMethod.DELETE, entity, Void.class);
        log.info("Deleted product with id = {}", id);
    }

    public ProductDto getProductById(Long id) {
        String url = GO_API_URL + "/" + id;
        ResponseEntity<ProductDto> response = restTemplate.exchange(
                url, HttpMethod.GET, null, ProductDto.class);

        return response.getBody();
    }

    private HttpHeaders createAuthHeaders(String jwtToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
