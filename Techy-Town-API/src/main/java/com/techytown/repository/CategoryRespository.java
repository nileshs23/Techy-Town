package com.techytown.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techytown.model.Category;

@Repository
public interface CategoryRespository extends JpaRepository<Category, Integer> {

}
