package com.example.GeocodingApp.controller;
import com.example.GeocodingApp.document.Address;
import com.example.GeocodingApp.document.History;
import com.example.GeocodingApp.document.SearchTermDTO;
import com.example.GeocodingApp.service.ReadStreetFromOSMFile;
import com.example.GeocodingApp.service.SearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping( "/api")
@CrossOrigin(origins = "http://localhost:3000")
public class MainController {

    private final SearchService service;
    private final ReadStreetFromOSMFile readFileService;

    @Autowired
    public MainController(SearchService service, ReadStreetFromOSMFile readFileService) {
        this.service = service;
        this.readFileService = readFileService;
    }


    @PostMapping(value = "/search", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Address> getSearchSuggestions(@RequestBody final SearchTermDTO searchTerm){
        return service.SearchSuggestions(searchTerm);
    }

    @PostMapping(value = "/insertData", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void insertData(@RequestBody final JsonNode content) throws IOException {

        service.InsertDataService(content);
    }




    @GetMapping(value = "/extract")
    public void extract() throws IOException {
        readFileService.extractJSON();
    }


    @GetMapping(value = "/extractAmenity")
    public void extractAmenity() throws IOException {
        readFileService.readAmenities();
    }


    @GetMapping(value = "/getHistory")
    public List<History> history()  {
       return service.getHistory();
    }


    @PostMapping(value = "/saveHistory",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public void saveHistory(@RequestBody final JsonNode searchTerm) throws IOException {
        System.out.println(searchTerm.toString());
        service.saveHistory(searchTerm);
    }



    @PostMapping(value = "/geocoding",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity<JsonNode> getAddress(@RequestBody final SearchTermDTO searchTerm) throws IOException , JsonProcessingException {

        System.out.println(searchTerm.toString());
        BufferedReader rd  = null;
        StringBuilder sb = null;
        String line = null;
        URL url = new URL("http://www.overpass-api.de/api/xapi?*[amenity=cinema][bbox=-180,-90,180,90]");

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
            return ResponseEntity.ok(mapper.readTree("{\"id\": \"ERROR\", \"name\": \"Alice\"}"));
        }




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