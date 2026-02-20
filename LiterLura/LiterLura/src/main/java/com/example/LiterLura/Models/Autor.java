package com.example.LiterLura.Models;

import com.example.LiterLura.DTO.DatosAutor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private
    String nombre;

    @Column
    private
    int birthYear;

    @Column
    private
    int deathYear;


    @ManyToOne
    private Libro libro;

    public Autor(){}

    public Autor(DatosAutor autor){
        this.nombre=autor.nombre();
        this.birthYear= autor.birthYear();
        this.deathYear=autor.deathYear();
    }

    public String getNombre() {
        String[] values = this.nombre.split(",");
        return values[1]+" "+values[0];
    }

    public int getBirthYear() {
        return birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
