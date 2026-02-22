package com.example.LiterLura.Service;

import com.example.LiterLura.API.Call;
import com.example.LiterLura.Models.Autor;
import com.example.LiterLura.Models.Libro;
import com.example.LiterLura.Repository.AutorRepository;
import com.example.LiterLura.Repository.LibroRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Service {

    Call api = new Call();
    LibroRepository libroRepository;
    AutorRepository autorRepository;

    public Service(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository =libroRepository;
        this.autorRepository=autorRepository;
    }

    public void consultarLibro(String nombreLibro){

        nombreLibro = nombreLibro.replace(" ", "%20");
        Libro libro = new Libro(api.consultarLibro(nombreLibro));

        if(libro.getIdLibroApi()==null){
            System.out.println("No se encontro el libro");
            return;
        }

        if(libroRepository.existsByIdLibroApi(libro.getIdLibroApi())){
            System.out.println("Libro Ya Registrado");
        }
        else {
            System.out.println("Guardando Libro");
            libroRepository.save(libro);
        }

        System.out.println("Datos Del Libro");

        System.out.println("ID : " + libro.getIdLibroApi());
        System.out.println("Titulo: "+ libro.getTitulo());
        for(Autor e : libro.getAutors()){
            System.out.println("Autor : "+e.getNombre()+" ("+e.getBirthYear()+"-"+e.getDeathYear()+")");
        }
        System.out.print("IDIOMAS: ");
        for (String e : libro.getIdiomas()) System.out.print(e+" ");
        System.out.println();
        System.out.println("Numero de Descargas : " + libro.getNumeroDescargas());
    }

    public void listarLibrosRegistrados(){
        List<Libro> libros = libroRepository.findAll();

        System.out.println("Tenemos los siguientes libros");
        for (Libro e : libros){
            System.out.println("- Titulo: "+e.getTitulo());
            System.out.println("- Numero de Descargas : "+e.getNumeroDescargas());
            System.out.println("- Idiomas : "+ e.getIdiomas().get(0));
            System.out.println("- Autor(es): ");
            for(Autor aut : e.getAutors()){
            System.out.print(aut.getNombre()+";");}
            System.out.println();
        }

    }

    public void listaDeAutores(){
        System.out.println("Autores Registrados");
        autorRepository.findAll().forEach(autor ->
                        System.out.println("Nombre: "+autor.getNombre()+
                                " ("+autor.getBirthYear()+" - "+autor.getDeathYear()+")")
                );
    }

    public void listaDeAutoresVivosEnUnDeterminadoAnio(int year){
        System.out.println("Autores Encontrados:");
        autorRepository.findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(year,year).forEach(autor ->
                        System.out.println("Nombre: "+autor.getNombre()+
                                " ("+autor.getBirthYear()+" - "+autor.getDeathYear()+")")
                );
    }

    public void librosPorIdioma(String idioma){
        Map<String,String> config = new HashMap<>();
        config.put("en","Ingles");
        config.put("es","Español");
        System.out.println("Libros "+config.get(idioma));
        List<Libro> libros = libroRepository.findByIdiomasContaining(idioma);
        libros.forEach(leb -> System.out.println("-> "+leb.getTitulo()));

        if(libros.isEmpty()){
            System.out.println("No tenemos libros en ese idioma");
        }
    }

    public void librosMasDescargados(){
        List<Libro> libros = libroRepository.findAll();
        List<Libro> top10 = libros.stream()
                .sorted(Comparator.comparing(Libro::getNumeroDescargas).reversed())
                .limit(10)
                .collect(Collectors.toList());

        System.out.println("Libros Mas Descargados : ");
        top10.forEach(lib ->
                System.out.println("*. "+lib.getTitulo() + "/ Descargas: "+lib.getNumeroDescargas())
        );


    }
}
                                