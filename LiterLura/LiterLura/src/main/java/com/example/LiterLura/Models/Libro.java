package com.example.LiterLura.Models;

import com.example.LiterLura.DTO.DatosAutor;
import com.example.LiterLura.DTO.DatosLibro;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long idLibroApi;

    @Column
    private String titulo;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)    private
    List<Autor> autors;

    @ElementCollection(fetch = FetchType.EAGER)
    private
    List<String> idiomas;

    @Column(name = "numero_descargas")
    private
    int numeroDescargas;

    public Libro(){}

    public Libro(DatosLibro libro){
        this.idLibroApi = libro.idLibroApi();
        this.titulo= libro.titulo();
        this.numeroDescargas= libro.numeroDescargas();
        this.idiomas=libro.idiomas();
        this.autors=new ArrayList<>();
        for(DatosAutor e : libro.autors()){
            //this.autors.add(new Autor(e));
            Autor autor = new Autor(e);
            autor.setLibro(this);
            this.autors.add(autor);
        }
    }

    public Long getIdLibroApi() {
        return idLibroApi;
    }

    public String getTitulo() {
        return titulo;
    }

    public List<Autor> getAutors() {
        return autors;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public int getNumeroDescargas() {
        return numeroDescargas;
    }
}
