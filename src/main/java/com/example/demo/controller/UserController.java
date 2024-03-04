package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getUserAll(){
    return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getById(@PathVariable Long id){
        return userRepository.findById(id);
    }

    @PostMapping
    public User postUser(@RequestBody User user){
        return userRepository.save(user);

    }

    @PutMapping("/{id}")
    public User putUser(@PathVariable Long id, @RequestBody User userAtualizado){
        Optional<User> userOptional = userRepository.findById(id);
        User userExistente = userOptional.get();

        userExistente.setNome(userAtualizado.getNome());
        userExistente.setIdade(userAtualizado.getIdade());

        return userRepository.save(userExistente);
    }

    @DeleteMapping("/{id}")
    public void userDelete(@PathVariable Long id){
        Optional<User> userOptional = userRepository.findById(id);

        userRepository.delete(userOptional.get());

    }
}
