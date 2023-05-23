package com.emekpazari.base.business.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdProductResponse {
    private int id;
    private String name;
    private String categoryName;
    private String userName;
    private String photoUrl;
    private int price;
    private String description;
}
