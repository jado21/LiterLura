package com.example.LiterLura.API;

import com.example.LiterLura.DTO.DatosLibro;
import com.example.LiterLura.DTO.DatosRespuesta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Call {

    String URL_BASE="https://gutendex.com/";

    public DatosLibro consultarLibro(String nombreLibro){

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_BASE+"books/?search="+nombreLibro))
                .GET()
                .build();

        HttpResponse<String> response;

        try {
            response=client.send(
                    request,
                    HttpResponse.BodyHandlers.ofString()
            );
        }catch (IOException | InterruptedException e){
            throw new RuntimeException(e);
        }

        ObjectMapper mapper = new ObjectMapper();

        try{
        DatosRespuesta respuesta =mapper.readValue(response.body(), DatosRespuesta.class);
            return respuesta.libros().get(0);

        }catch (JsonProcessingException e){
            System.out.println(e.getMessage());
        }

        return null;
    }


}
