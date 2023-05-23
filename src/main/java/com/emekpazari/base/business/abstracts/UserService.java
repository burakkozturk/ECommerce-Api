package com.emekpazari.base.business.abstracts;

import com.emekpazari.base.business.request.CreateUserRequest;
import com.emekpazari.base.business.request.UpdateUserRequest;
import com.emekpazari.base.business.response.GetAllUserResponse;
import com.emekpazari.base.business.response.GetByIdUserResponse;
import com.emekpazari.base.entities.concretes.User;

import java.util.List;

public interface UserService {
    List<GetAllUserResponse> getAll();
    GetByIdUserResponse getById(int id);
    void add(CreateUserRequest createUserRequest);
    void update(UpdateUserRequest updateUserRequest);
    void delete(int id);

}
