package com.emekpazari.base.business.abstracts;

import com.emekpazari.base.business.request.CreateCategoryRequest;
import com.emekpazari.base.business.request.CreateProductRequest;
import com.emekpazari.base.business.request.UpdateCategoryRequest;
import com.emekpazari.base.business.request.UpdateProductRequest;
import com.emekpazari.base.business.response.GetAllCategoryResponse;
import com.emekpazari.base.business.response.GetAllProductResponse;
import com.emekpazari.base.business.response.GetByIdCategoryResponse;
import com.emekpazari.base.business.response.GetByIdProductResponse;
import com.emekpazari.base.business.response.GetProductsByCategoryIdResponse; // Yeni eklenen import

import java.util.List;

public interface ProductService {
    List<GetAllProductResponse> getAll();
    GetByIdProductResponse getById(int id);
    void add(CreateProductRequest createProductRequest);
    void update(UpdateProductRequest updateProductRequest);
    void delete(int id);
    GetProductsByCategoryIdResponse getProductsByCategoryId(int categoryId); // Yeni metod
}
