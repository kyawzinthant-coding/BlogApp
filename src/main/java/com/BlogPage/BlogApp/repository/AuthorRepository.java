package com.BlogPage.BlogApp.repository;

import com.BlogPage.BlogApp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  AuthorRepository extends JpaRepository<Author,Long> {
}
