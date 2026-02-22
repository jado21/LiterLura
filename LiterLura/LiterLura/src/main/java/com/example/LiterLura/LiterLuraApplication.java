package com.example.LiterLura;

import com.example.LiterLura.Repository.AutorRepository;
import com.example.LiterLura.Repository.LibroRepository;
import com.example.LiterLura.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiterLuraApplication implements CommandLineRunner {

	@Autowired
	LibroRepository libroRepository;

	@Autowired
	AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterLuraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Service service = new Service(libroRepository,autorRepository);

		Scanner cin = new Scanner(System.in);

		char opc=' ';
		do{
			System.out.println("Tenemos las siguientes opciones:");
			System.out.println("1) Buscar libro por Titutlo");
			System.out.println("2) Listar libros Registrados");
			System.out.println("3) Lista de Autores");
			System.out.println("4) Lista de Autores Vivos en un Año Determinado");
			System.out.println("5) Lista de Libros por Idioma");
			System.out.println("6) Top 10 Libros Mas Descargados");
			System.out.println();
			System.out.println("0) SALIR");
			System.out.println();
			System.out.print("Ingresa una opcion -> ");
			opc = cin.next().charAt(0);

			switch (opc){
				case '1':
					String variable=" ";
					System.out.println("Ingresa el nombre del libro");
					cin.nextLine();
					variable=cin.nextLine();
					service.consultarLibro(variable);
					break;
				case '2':
					service.listarLibrosRegistrados();
					break;
					case '3':
					service.listaDeAutores();
					break;
				case '4':
					int year=0;
					System.out.println("Ingresa el año:");
					cin.nextLine();
					year=cin.nextInt();
					service.listaDeAutoresVivosEnUnDeterminadoAnio(year);
					break;
				case '5':
					System.out.println("Libros Segun Idioma escribe:");
					System.out.println("EN -> English");
					System.out.println("ES -> Español");
					System.out.print("Opcion elegida -> ");
					cin.nextLine();
					String alt = cin.nextLine();
					alt=alt.toLowerCase();
					service.librosPorIdioma(alt);
					break;
				case '6':
					service.librosMasDescargados();
					break;
				case '0':
					break;
				default:
					System.out.println("No tenemos esa Opcion");
					break;
			}

		}while (opc!='0');



	}
}
