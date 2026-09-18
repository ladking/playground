package com.currencyconverter.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


public class HttpUtils  {
    private HttpClient client;
    private ObjectMapper mapper = new ObjectMapper();



    public HttpUtils(){
        this.client = HttpClient.newHttpClient();
    }


    public <T> Response get(String url, Class<T> responseType) throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .GET()
        .build();

        HttpResponse<String> resp = this.client.send(request,HttpResponse.BodyHandlers.ofString());
        ObjectMapper jsonResponse = mapper.readValue(resp.body(), responseType);
        return new Response(resp.statusCode(), jsonResponse);
    }


    public record Response(
        Integer statusCode,
        Object body
    ){}
}