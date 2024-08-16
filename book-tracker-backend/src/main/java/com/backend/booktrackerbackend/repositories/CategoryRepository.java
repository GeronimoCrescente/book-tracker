package com.backend.booktrackerbackend.repositories;

import com.backend.booktrackerbackend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
