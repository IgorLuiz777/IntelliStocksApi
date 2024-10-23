package br.com.intellistocks.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.intellistocks.api.models.user.User;
import br.com.intellistocks.api.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> userList() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public User editUser(User user, Long id) {
        if (!userRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User NOT FOUND, id:" + id);
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User NOT FOUND, id:" + id);
        userRepository.deleteById(id);
    }
}
