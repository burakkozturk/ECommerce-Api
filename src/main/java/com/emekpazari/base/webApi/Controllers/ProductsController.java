package com.emekpazari.base.webApi.Controllers;

import com.emekpazari.base.business.abstracts.ProductService;
import com.emekpazari.base.business.request.CreateProductRequest;
import com.emekpazari.base.business.request.UpdateProductRequest;
import com.emekpazari.base.business.response.GetAllProductResponse;
import com.emekpazari.base.business.response.GetByIdProductResponse;
import com.emekpazari.base.business.response.GetProductsByCategoryIdResponse;
import com.emekpazari.base.business.response.GetProductsByUserIdResponse;
import com.emekpazari.base.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductsController {

    private ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<GetAllProductResponse> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{userId}/products")
    public GetProductsByUserIdResponse getUserProducts(@PathVariable("userId") int userId) {
        return productService.getUserProducts(userId);
    }

    @GetMapping("/category/{categoryId}")
    public GetProductsByCategoryIdResponse getProductsByCategoryId(@PathVariable int categoryId) {
        return productService.getProductsByCategoryId(categoryId);
    }

    @GetMapping("/{id}")
    public GetByIdProductResponse getById(@PathVariable int id) {
        return productService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody @Valid CreateProductRequest createProductRequest) {
        productService.add(createProductRequest);
    }

    @PutMapping()
    public void update(@RequestBody UpdateProductRequest updateProductRequest) {
        productService.update(updateProductRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }

}
