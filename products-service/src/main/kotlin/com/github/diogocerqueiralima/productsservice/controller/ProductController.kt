package com.github.diogocerqueiralima.productsservice.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/products")
class ProductController {

    @GetMapping
    fun getAll(): ResponseEntity<Any> {
        return ResponseEntity.ok("ok")
    }

}