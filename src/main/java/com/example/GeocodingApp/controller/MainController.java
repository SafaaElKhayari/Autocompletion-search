package com.example.GeocodingApp.controller;

import com.example.GeocodingApp.document.SearchTermDTO;
import com.example.GeocodingApp.document.Streets;
import com.example.GeocodingApp.service.SearchService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping( "/api")
public class MainController {

    private final SearchService service;

    @Autowired
    public MainController(SearchService service) {
        this.service = service;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/search", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Streets> getSearchSuggestions(@RequestBody final SearchTermDTO searchTerm){
        System.out.println("from the post new controller");
        System.out.println(searchTerm);
        return service.SearchSuggestions(searchTerm);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/importData", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public JsonNode importData(@RequestBody final JsonNode searchTerm){
        System.out.println("Import data Controller");
        System.out.println(searchTerm);




        return null;
    }


}



/*@PostMapping(value = "/searchTerm",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Feature> getSearchTerm(@RequestBody final SearchTermDTO searchTerm){
        System.out.println("from the post controller");
        System.out.println(searchTerm);
        return service.search(searchTerm);
    }*/


 /* @GetMapping(value = "/recreate")
    public void recreateIndex(){
        System.out.println("from the creator");
        service.createPOJO();
    }*/

   /* @GetMapping(value = "/fillData")
    public void fillData(){
        System.out.println("fill the data");
        service.fillData();
    }*/