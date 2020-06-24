package com.skilldistillery.snitchapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.snitchapp.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
