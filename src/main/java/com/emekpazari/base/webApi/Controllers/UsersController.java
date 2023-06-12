package com.emekpazari.base.webApi.Controllers;

import com.emekpazari.base.business.abstracts.UserService;
import com.emekpazari.base.business.request.CreateUserRequest;
import com.emekpazari.base.business.request.LoginRequest;
import com.emekpazari.base.business.request.UpdateUserRequest;
import com.emekpazari.base.business.response.GetAllUserResponse;
import com.emekpazari.base.business.response.GetByIdUserResponse;
import com.emekpazari.base.business.response.LoginResponse;
import com.emekpazari.base.business.response.RegisterResponse;
import com.emekpazari.base.dataAccess.UserRepository;
import com.emekpazari.base.entities.concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NonUniqueResultException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UsersController {
    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public UsersController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public List<GetAllUserResponse> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public GetByIdUserResponse getById(@PathVariable int id){
        return userService.getById(id);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody CreateUserRequest createUserRequest) {
        User existingUser = userRepository.findByEmail(createUserRequest.getMail());
        if (existingUser != null) {
            return ResponseEntity.badRequest().body("Bu e-posta adresi zaten kullanılıyor.");
        }

        User newUser = new User();
        newUser.setName(createUserRequest.getName());
        newUser.setPassword(createUserRequest.getPassword());
        newUser.setEmail(createUserRequest.getMail());

        userRepository.save(newUser);

        RegisterResponse registerResponse = new RegisterResponse(newUser.getId(), newUser.getName(), newUser.getEmail());
        return ResponseEntity.ok(registerResponse);
    }


    @PostMapping(value = "/login", produces = "application/json")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        try {
            User user = userRepository.findByEmail(loginRequest.getEmail());
            if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Geçersiz e-posta veya şifre.");
            }

            LoginResponse loginResponse = new LoginResponse(user.getId(), user.getName(), user.getEmail());
            return ResponseEntity.ok(loginResponse);
        } catch (NonUniqueResultException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Birden fazla kullanıcı bulundu. Lütfen sistem yöneticinize başvurun.");
        }
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
