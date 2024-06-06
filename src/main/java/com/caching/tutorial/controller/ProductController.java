package com.caching.tutorial.controller;

import com.caching.tutorial.dto.ProductDTO;
import com.caching.tutorial.service.ProductService;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/v1")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final CacheManager cacheManager;

    private final ProductService productService;

    @GetMapping("/id")
    public ProductDTO getProductById(@RequestParam("id") Long id) {
        return productService.getProductById(id);
    }


    @GetMapping("/name")
    public ProductDTO getProductByName() {
        return productService.getProductByName("Product1");
    }

    @GetMapping("/name-and-type")
    public ProductDTO getProductByNameAndType() {
        return productService.getProductByNameAndType("Product1", "electronics");
    }

    @GetMapping("/cache/all")
    public void getAllCachedData()  {

        CaffeineCache cache = (CaffeineCache) cacheManager.getCache("products");
        Cache<Object, Object> currentCache = cache.getNativeCache();

        currentCache.asMap().entrySet()
                .stream().forEach(entry -> {
                    log.info("Cache entry key : " + entry.getKey() + " value : " + entry.getValue());
                });


    }


}
