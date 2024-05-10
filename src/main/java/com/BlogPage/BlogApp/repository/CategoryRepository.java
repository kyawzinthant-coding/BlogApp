package com.BlogPage.BlogApp.repository;

import com.BlogPage.BlogApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
