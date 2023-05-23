package com.emekpazari.base.webApi.Controllers;

import com.emekpazari.base.business.abstracts.CategoryService;
import com.emekpazari.base.business.abstracts.UserService;
import com.emekpazari.base.business.request.CreateCategoryRequest;
import com.emekpazari.base.business.request.CreateUserRequest;
import com.emekpazari.base.business.request.UpdateCategoryRequest;
import com.emekpazari.base.business.request.UpdateUserRequest;
import com.emekpazari.base.business.response.GetAllCategoryResponse;
import com.emekpazari.base.business.response.GetAllUserResponse;
import com.emekpazari.base.business.response.GetByIdCategoryResponse;
import com.emekpazari.base.business.response.GetByIdUserResponse;
import com.emekpazari.base.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public List<GetAllUserResponse> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public GetByIdUserResponse getById(@PathVariable int id){
        return userService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(code= HttpStatus.CREATED)
    public void add(@RequestBody @Valid CreateUserRequest createUserRequest){
        this.userService.add(createUserRequest);
    }

    @PutMapping()
    public void update(@RequestBody UpdateUserRequest updateUserRequest){
        this.userService.update(updateUserRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        this.userService.delete(id);
    }


}
