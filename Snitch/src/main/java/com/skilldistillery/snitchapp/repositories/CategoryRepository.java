package com.skilldistillery.snitchapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.snitchapp.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
