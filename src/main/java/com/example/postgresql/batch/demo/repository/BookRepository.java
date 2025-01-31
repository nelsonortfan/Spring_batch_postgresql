package com.example.postgresql.batch.demo.repository;

import com.example.postgresql.batch.demo.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {

    Book findTopByOrderByIdDesc();

}
