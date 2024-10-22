package br.com.intellistocks.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.intellistocks.api.models.user.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}