package com.example.postgresql.batch.demo.repository;

import com.example.postgresql.batch.demo.domain.Control;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ControlRepository extends JpaRepository<Control,Long> {

    Control findTopByOrderByIdDesc();

}
