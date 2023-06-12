package com.emekpazari.base.business.response;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class RegisterResponse {
    private int id;
    private String name;
    private String password;
    // Diğer kullanıcı bilgilerini de buraya ekleyebilirsiniz.

    public RegisterResponse(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
    }



    // Getter ve setter metodlarını ekleyin.
}
