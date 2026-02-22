package com.example.LiterLura.Repository;

import com.example.LiterLura.Models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro,Long> {

    boolean existsByIdLibroApi(Long aLong);

    List<Libro> findByIdiomasContaining(String idioma);

}
