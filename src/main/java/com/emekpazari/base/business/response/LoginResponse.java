package com.emekpazari.base.business.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginResponse {
    private int id;
    private String name;
    private String email;

    public LoginResponse(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getter ve setter metodlarını ekleyebilirsiniz.
}
