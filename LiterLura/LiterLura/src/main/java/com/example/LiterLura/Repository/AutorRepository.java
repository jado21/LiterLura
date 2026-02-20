package com.example.LiterLura.Repository;

import com.example.LiterLura.Models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long>{
    List<Autor> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(Integer nacimiento,Integer defuncion);
}
