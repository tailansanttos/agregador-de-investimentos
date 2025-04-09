package com.tailan.investimentos.service;

import com.tailan.investimentos.exceptions.UserEmailExistsException;
import com.tailan.investimentos.exceptions.UserNotFoundException;
import com.tailan.investimentos.model.domain.User;
import com.tailan.investimentos.model.dtos.UserDto;
import com.tailan.investimentos.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.username());
        user.setPassword(userDto.password());
        user.setEmail(userDto.email());

       if ( emailAlreadyExists(userDto.email())) {
           throw  new UserEmailExistsException("Email já cadastrado");
       }

        userRepository.save(user);
        return userDto;
    }

    public boolean  emailAlreadyExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        User user = new User();
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usúario não encontrado."));
        user.setUsername(userDto.username());
        user.setPassword(userDto.password());
        userRepository.save(user);
        return userDto;
    }

    public User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usúario não encontrado."));
        return user;
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usúario não encontrado."));
        userRepository.delete(user);
    }

    public List<UserDto> getUsers(){
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserDto(user.getUsername(),user.getEmail(),user.getPassword())).toList();

    }
}
