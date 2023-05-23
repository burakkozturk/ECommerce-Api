package com.emekpazari.base.business.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProductsByCategoryIdResponse {
    private List<GetAllProductResponse> products;
}
