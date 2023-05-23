package com.emekpazari.base.business.abstracts;

import com.emekpazari.base.business.request.CreateCategoryRequest;
import com.emekpazari.base.business.request.UpdateCategoryRequest;
import com.emekpazari.base.business.response.GetAllCategoryResponse;
import com.emekpazari.base.business.response.GetByIdCategoryResponse;

import java.util.List;

public interface CategoryService {
    List<GetAllCategoryResponse> getAll();
    GetByIdCategoryResponse getById(int id);
    void add(CreateCategoryRequest createCategoryRequest);
    void update(UpdateCategoryRequest updateCategoryRequest);
    void delete(int id);
    // Yeni metod
    GetByIdCategoryResponse getCategoryByName(String name);
}
