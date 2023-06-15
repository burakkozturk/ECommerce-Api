package com.emekpazari.base.business.concrete;

import com.emekpazari.base.business.abstracts.UserService;
import com.emekpazari.base.business.request.CreateUserRequest;
import com.emekpazari.base.business.request.UpdateUserRequest;
import com.emekpazari.base.business.response.GetAllUserResponse;
import com.emekpazari.base.business.response.GetByIdUserResponse;
import com.emekpazari.base.core.mappers.ModelMapperService;
import com.emekpazari.base.dataAccess.UserRepository;
import com.emekpazari.base.entities.concretes.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserManager implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapperService modelMapperService;


    @Override
    public List<GetAllUserResponse> getAll() {
        List<User> users = userRepository.findAll();

        List<GetAllUserResponse> userResponses = users.stream()
                .map(user -> this.modelMapperService.forResponse()
                        .map(user, GetAllUserResponse.class)).collect(Collectors.toList());

        return userResponses;
    }

    @Override
    public GetByIdUserResponse getById(int id) {
        User user = userRepository.findById(id).orElseThrow();
        GetByIdUserResponse response = this.modelMapperService.forResponse()
                .map(user, GetByIdUserResponse.class);

        return response;
    }

    @Override
    public void add(CreateUserRequest createUserRequest) {
        User user = this.modelMapperService.forRequest().map(createUserRequest,User.class);
        this.userRepository.save(user);
    }

    @Override
    public void update(UpdateUserRequest updateUserRequest) {
        User user = this.modelMapperService.forRequest()
                .map(updateUserRequest, User.class);
        this.userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        this.userRepository.deleteById(id);
    }
}
