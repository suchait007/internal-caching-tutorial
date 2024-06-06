package com.caching.tutorial.service;


import com.caching.tutorial.dto.ProductDTO;
import com.caching.tutorial.jpa.ProductRepository;
import com.caching.tutorial.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;


    @Cacheable(cacheNames = "products")
    public ProductDTO getProductById(Long id) {

        Product product = productRepository.findById(id).get();
        return getProductDTO(product);
    }


    @Cacheable(cacheNames = "products", key = "#name")
    public ProductDTO getProductByName(String name) {
        Product product =  productRepository.findProductByName(name);
        return getProductDTO(product);
    }

    @Cacheable(cacheNames = "products", key = "#name", condition = "#type.equals('testvalue')")
    public ProductDTO getProductByNameAndType(String name, String type) {
        Product product =  productRepository.findProductByNameAndType(name, type);
        return getProductDTO(product);
    }


    private ProductDTO getProductDTO(Product product) {

        ProductDTO dto = new ProductDTO();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());

        return dto;

    }
}
