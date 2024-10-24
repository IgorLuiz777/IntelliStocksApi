package br.com.intellistocks.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.intellistocks.api.models.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String email);
}