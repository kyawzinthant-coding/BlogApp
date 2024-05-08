package com.BlogPage.BlogApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long blogID;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 255, nullable = false)
    private String short_text;

    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToOne
    @JoinColumn(name="author_id", nullable = false)
    private Author author;

    private LocalDate Created_at;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private  Category category;
}
