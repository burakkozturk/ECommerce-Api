package com.emekpazari.base.business.concrete;

import com.emekpazari.base.business.abstracts.ProductService;
import com.emekpazari.base.business.request.CreateProductRequest;
import com.emekpazari.base.business.request.UpdateProductRequest;
import com.emekpazari.base.business.response.*;
import com.emekpazari.base.core.mappers.ModelMapperService;
import com.emekpazari.base.dataAccess.CategoryRepository;
import com.emekpazari.base.dataAccess.ProductRepository;
import com.emekpazari.base.dataAccess.UserRepository;
import com.emekpazari.base.entities.concretes.Category;
import com.emekpazari.base.entities.concretes.Product;
import com.emekpazari.base.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductManager implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapperService modelMapperService;




    @Override
    public List<GetAllProductResponse> getAll() {
        List<Product> products = productRepository.findAll();

        List<GetAllProductResponse> productResponses = products.stream()
                .map(product -> this.modelMapperService.forResponse()
                        .map(product, GetAllProductResponse.class)).collect(Collectors.toList());

        return productResponses;
    }

    @Override
    public GetByIdProductResponse getById(int id) {
        Product product = productRepository.findById(id).orElseThrow();
        GetByIdProductResponse response = this.modelMapperService.forResponse()
                .map(product, GetByIdProductResponse.class);

        return response;
    }

    @Override
    public void add(CreateProductRequest createProductRequest) {
        Product product = this.modelMapperService.forRequest().map(createProductRequest,Product.class);
        this.productRepository.save(product);
    }

    @Override
    public void update(UpdateProductRequest updateProductRequest) {
        Product product = this.modelMapperService.forRequest()
                .map(updateProductRequest, Product.class);
        this.productRepository.save(product);
    }

    @Override
    public void delete(int id) {
        this.productRepository.deleteById(id);
    }


    @Override
    public GetProductsByCategoryIdResponse getProductsByCategoryId(int categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);

        List<GetAllProductResponse> productResponses = products.stream()
                .map(product -> modelMapperService.forResponse()
                        .map(product, GetAllProductResponse.class)).collect(Collectors.toList());

        GetProductsByCategoryIdResponse response = new GetProductsByCategoryIdResponse();
        response.setProducts(productResponses);

        return response;
    }

    @Override
    public GetProductsByUserIdResponse getUserProducts(int userId) {
        List<Product> products = productRepository.findByUserId(userId);

        List<GetAllProductResponse> productResponses = products.stream()
                .map(product -> modelMapperService.forResponse()
                        .map(product, GetAllProductResponse.class)).collect(Collectors.toList());

        GetProductsByUserIdResponse response = new GetProductsByUserIdResponse();
        response.setProducts(productResponses);

        return response;
    }


}
