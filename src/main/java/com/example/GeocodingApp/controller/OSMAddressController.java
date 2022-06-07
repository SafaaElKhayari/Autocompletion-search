package com.example.GeocodingApp.controller;
import com.example.GeocodingApp.document.SearchTermDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping( "/")
public class OSMAddressController {

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/reverseGeocoding",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity<JsonNode>  getCoordinates(@RequestBody final SearchTermDTO searchTerm) throws IOException ,JsonProcessingException {

        BufferedReader rd  = null;
        StringBuilder sb = null;
        String line = null;

        URL url = new URL("https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat="+searchTerm.getLat()+"&lon="+searchTerm.getLon());

        try{

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            rd  = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            sb = new StringBuilder();

        while ((line = rd.readLine()) != null)
        {
            sb.append(line + '\n');
        }



            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(sb.toString());

            System.out.println(json.get("features").asText());

            return ResponseEntity.ok(json);



        }catch ( Exception e ){

            ObjectMapper mapper = new ObjectMapper();
            return ResponseEntity.ok(mapper.readTree("{\"id\": \"132\", \"name\": \"Alice\"}"));
        }




    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/geocoding",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity<JsonNode>  getAddress(@RequestBody final SearchTermDTO searchTerm) throws IOException ,JsonProcessingException {

        System.out.println(searchTerm.toString());
        BufferedReader rd  = null;
        StringBuilder sb = null;
        String line = null;
        URL url = new URL("https://nominatim.openstreetmap.org/search?q="+searchTerm.getSearchTerm()+"&format=geojson");

        try{

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            rd  = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            sb = new StringBuilder();

            while ((line = rd.readLine()) != null)
            {
                sb.append(line + '\n');
            }



            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(sb.toString());
            return ResponseEntity.ok(json);



        }catch ( Exception e ){

            ObjectMapper mapper = new ObjectMapper();
            return ResponseEntity.ok(mapper.readTree("{\"id\": \"132\", \"name\": \"Alice\"}"));
        }




    }






}
