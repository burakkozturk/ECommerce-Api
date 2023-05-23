package com.emekpazari.base.business.concrete;

import com.emekpazari.base.business.abstracts.CategoryService;
import com.emekpazari.base.business.request.CreateCategoryRequest;
import com.emekpazari.base.business.request.UpdateCategoryRequest;
import com.emekpazari.base.business.response.GetAllCategoryResponse;
import com.emekpazari.base.business.response.GetByIdCategoryResponse;
import com.emekpazari.base.core.mappers.ModelMapperService;
import com.emekpazari.base.dataAccess.CategoryRepository;
import com.emekpazari.base.entities.concretes.Category;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    private ModelMapperService modelMapperService;



    @Override
    public List<GetAllCategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();

        List<GetAllCategoryResponse> categoryResponses = categories.stream()
                .map(category -> this.modelMapperService.forResponse()
                        .map(category, GetAllCategoryResponse.class)).collect(Collectors.toList());

        return categoryResponses;
    }

    @Override
    public GetByIdCategoryResponse getById(int id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        GetByIdCategoryResponse response = this.modelMapperService.forResponse()
                .map(category, GetByIdCategoryResponse.class);

        return response;
    }

    @Override
    public void add(CreateCategoryRequest createCategoryRequest) {
        Category category = this.modelMapperService.forRequest().map(createCategoryRequest,Category.class);
        this.categoryRepository.save(category);
    }

    @Override
    public void update(UpdateCategoryRequest updateCategoryRequest) {
        Category category = this.modelMapperService.forRequest()
                .map(updateCategoryRequest, Category.class);
        this.categoryRepository.save(category);

    }

    @Override
    public void delete(int id) {
        this.categoryRepository.deleteById(id);
    }

    @Override
    public GetByIdCategoryResponse getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name);
        if (category == null) {
            // İstediğiniz hata durumunu veya uygun şekilde işlem yapılmasını sağlayabilirsiniz.
            throw new RuntimeException("Kategori bulunamadı: " + name);
        }

        GetByIdCategoryResponse response = modelMapperService.forResponse()
                .map(category, GetByIdCategoryResponse.class);

        return response;
    }
}
