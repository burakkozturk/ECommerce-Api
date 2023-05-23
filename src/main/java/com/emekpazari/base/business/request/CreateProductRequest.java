package com.emekpazari.base.business.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    private String name;
    private int categoryId;
    private int userId;
//    burası sonradan eklendi
    private String photoUrl;
    private int price;
    private String description;
}
