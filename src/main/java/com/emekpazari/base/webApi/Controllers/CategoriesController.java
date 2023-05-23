package com.emekpazari.base.webApi.Controllers;

import com.emekpazari.base.business.abstracts.CategoryService;
import com.emekpazari.base.business.request.CreateCategoryRequest;
import com.emekpazari.base.business.request.UpdateCategoryRequest;
import com.emekpazari.base.business.response.GetAllCategoryResponse;
import com.emekpazari.base.business.response.GetByIdCategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/category")
@CrossOrigin
public class CategoriesController {
    private CategoryService categoryService;

    @Autowired
    public CategoriesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<GetAllCategoryResponse> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("/{id}")
    public GetByIdCategoryResponse getById(@PathVariable int id){
        return categoryService.getById(id);
    }

    @GetMapping("/name/{name}")
    public GetByIdCategoryResponse getByName(@PathVariable String name){
        return categoryService.getCategoryByName(name);
    }


    @PostMapping()
    @ResponseStatus(code= HttpStatus.CREATED)
    public void add(@RequestBody()@Valid CreateCategoryRequest createCategoryRequest){
        this.categoryService.add(createCategoryRequest);
    }

    @PutMapping()
    public void update(@RequestBody() UpdateCategoryRequest updateCategoryRequest){
        this.categoryService.update(updateCategoryRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.categoryService.delete(id);
    }
}
