package com.emekpazari.base.dataAccess;

import com.emekpazari.base.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategoryId(int categoryId);
    List<Product> findByUserId(int userId);


}
