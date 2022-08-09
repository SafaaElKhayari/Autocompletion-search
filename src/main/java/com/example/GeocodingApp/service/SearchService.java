package com.example.GeocodingApp.service;
import com.example.GeocodingApp.document.Address;
import com.example.GeocodingApp.document.History;
import com.example.GeocodingApp.document.SearchTermDTO;
import com.example.GeocodingApp.helper.Indices;
import com.example.GeocodingApp.search.SearchUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class SearchService {

    private static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final Logger LOG = LoggerFactory.getLogger(SearchService.class);
    private final RestHighLevelClient client;

    @Autowired
    public SearchService(RestHighLevelClient client) {
        this.client = client;
    }


//Search *******************************************************************

    public List<Address> SearchSuggestions(SearchTermDTO dto) {

        final SearchRequest request = SearchUtils.buildSearchRequest(
                Indices.REGIONS_INDEX,
                Indices.STREETS_INDEX,
                Indices.AMENITY_INDEX,
                Indices.CITIES_INDEX,
                Indices.QUARTER_INDEX,
                Indices.PREF_PROV_INDEX
                ,
                dto
        );

        return searchInternal(request);
    }






//Search Internal  *******************************************************************

    private List<Address> searchInternal(SearchRequest request) {
        if (request == null) {
            LOG.error("Failed to build search request");
            return Collections.emptyList();
        }

        try {
            final SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            final SearchHit[] searchHits = response.getHits().getHits();
            final List<Address> addresses = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                addresses.add(
                        MAPPER.readValue(hit.getSourceAsString(), Address.class)
                );
            }


            return addresses;



        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return Collections.emptyList();
        }

    }


    public List<History> getHistory() {

        try {

            final SearchSourceBuilder builder = new SearchSourceBuilder();
            builder.query(QueryBuilders.matchAllQuery());
            builder.size(10);
            builder.trackScores(true);
            builder.sort(SortBuilders.fieldSort("created").order(SortOrder.DESC));
            SearchRequest request = new SearchRequest("history");
            request.source(builder);

            final SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            final SearchHit[] searchHits = response.getHits().getHits();
            final List<History> records = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                records.add(
                        MAPPER.readValue(hit.getSourceAsString(), History.class)
                );
            }


            return records;



        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return Collections.emptyList();
        }

    }




    public void saveHistory(JsonNode searchTerm) throws IOException {


        String id = (searchTerm.get("id")!=null)?searchTerm.get("id").asText():null;
        String name = (searchTerm.get("name")!=null)? (searchTerm.get("name").asText()) :null;
        String address = (searchTerm.get("address")!=null)?searchTerm.get("address").asText():null;
        String city = (searchTerm.get("city")!=null) ?searchTerm.get("city").asText():null;
        String province = (searchTerm.get("province")!=null) ?searchTerm.get("province").asText():null;
        String street_address = (searchTerm.get("street_address")!=null) ?searchTerm.get("street_address").asText():null;
        String region = (searchTerm.get("region")!=null) ?searchTerm.get("region").asText():null;
        String type = (searchTerm.get("type")!=null) ?searchTerm.get("type").asText():null;
        JsonNode geometry = searchTerm.get("coordinates");
        String type_json= geometry.get("type").asText();
        ArrayNode coordinates = (ArrayNode) geometry.get("coordinates");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        String strJson ="{\"name\":\"" + name +
                    "\" ,\"address\":\"" + address +
                    "\",\"type\":\"" + type +
                    "\",\"street_address\":\"" + street_address +
                    "\",\"region\":\"" + region +
                     "\",\"created\":\"" + dtf.format(now) +
                    "\",\"province\":\"" + province +
                    "\",\"city\":\"" + city +
                    "\",\"coordinates\":{\"type\":\"" + type_json + "\",\"coordinates\":" + coordinates + "}}";

            BulkRequest request = new BulkRequest();
            IndexRequest indexRequest =  new IndexRequest("history", "_doc",id);
            indexRequest.source(strJson, XContentType.JSON);
             request.add(indexRequest);
             client.bulk(request, RequestOptions.DEFAULT);

    }

    public void InsertDataService(JsonNode content) throws IOException {
        //System.out.println(content);

        String index = (content.get("index") != null) ? content.get("index").asText() : null;
        JsonNode data = (content.get("content") != null) ? content.get("content") : null;
        JsonNode champs = (content.get("champs") != null) ? content.get("champs") : null;
        System.out.println(data);
        ArrayNode elements = (ArrayNode) data.get("features");
        System.out.println(elements);

        if (Objects.equals(index, "Equipement") && elements != null) {

            for (int i = 0; i < elements.size(); i++) {

                JsonNode element = (JsonNode) elements.get(i);
                JsonNode properties = (JsonNode) element.get("properties");
                String id = (properties.get("id") != null) ? properties.get("id").asText() : null;
                String name = (properties.get(champs.get("name").asText()) != null) ? (properties.get(champs.get("name").asText()).asText()) : null;
                String city = (properties.get(champs.get("city").asText()) != null) ? properties.get(champs.get("city").asText()).asText() : null;
                String province = (properties.get(champs.get("province").asText()) != null) ? properties.get(champs.get("province").asText()).asText() : null;
                String street_address = (properties.get(champs.get("street_address").asText()) != null && properties.get(champs.get("street_address").asText()).asText() != "null") ? (String) properties.get(champs.get("street_address").asText()).asText() : "";
                String region = (properties.get(champs.get("region").asText()) != null) ? properties.get(champs.get("region").asText()).asText() : null;
                JsonNode geometry = (JsonNode) element.get("geometry");
                String type_json = geometry.get("type").asText();
                ArrayNode coordinates = (ArrayNode) geometry.get("coordinates");
                System.out.println(street_address != null && street_address != "null");
                String address = name + (street_address != "null" ? (" " + street_address) : "") + (city != null ? (", " + city) : " ");
                System.out.println(address);
                String strJson = "{\"name\":\"" + name +
                        "\" ,\"address\":\"" + address +
                        "\",\"type\":\"" + "amenities" +
                        "\",\"street_address\":\"" + street_address +
                        "\",\"region\":\"" + region +
                        "\",\"province\":\"" + province +
                        "\",\"city\":\"" + city +
                        "\",\"coordinates\":{\"type\":\"" + type_json + "\",\"coordinates\":" + coordinates + "}}";

                BulkRequest request = new BulkRequest();
                IndexRequest indexRequest = new IndexRequest("amenities", "_doc", id);
                indexRequest.source(strJson, XContentType.JSON);
                request.add(indexRequest);
                client.bulk(request, RequestOptions.DEFAULT);

            }

        } else if (Objects.equals(index, "Routes")) {

            for (int i = 0; i < elements.size(); i++) {

                JsonNode element = (JsonNode) elements.get(i);
                JsonNode properties = (JsonNode) element.get("properties");
                String id = (properties.get("id") != null) ? properties.get("id").asText() : null;
                String name = (properties.get(champs.get("name").asText()) != null) ? (properties.get(champs.get("name").asText()).asText()) : null;
                String city = (properties.get(champs.get("city").asText()) != null) ? properties.get(champs.get("city").asText()).asText() : null;
                String province = (properties.get(champs.get("province").asText()) != null) ? properties.get(champs.get("province").asText()).asText() : null;
                String region = (properties.get(champs.get("region").asText()) != null) ? properties.get(champs.get("region").asText()).asText() : null;
                JsonNode geometry = (JsonNode) element.get("geometry");
                String type_json = geometry.get("type").asText();
                ArrayNode coordinates = (ArrayNode) geometry.get("coordinates");
                String address = name + (city != null ? (", " + city) : " ");
                String strJson = "{\"name\":\"" + name +
                        "\" ,\"address\":\"" + address +
                        "\",\"type\":\"" + "highway" +
                        "\",\"region\":\"" + region +
                        "\",\"province\":\"" + province +
                        "\",\"city\":\"" + city +
                        "\",\"coordinates\":{\"type\":\"" + type_json + "\",\"coordinates\":" + coordinates + "}}";

                BulkRequest request = new BulkRequest();
                IndexRequest indexRequest = new IndexRequest("highway", "_doc", id);
                indexRequest.source(strJson, XContentType.JSON);
                request.add(indexRequest);
                client.bulk(request, RequestOptions.DEFAULT);


            }

        } else if (Objects.equals(index, "Villes")) {

            for (int i = 0; i < elements.size(); i++) {

                JsonNode element = (JsonNode) elements.get(i);
                JsonNode properties = (JsonNode) element.get("properties");
                String id = (properties.get("id") != null) ? properties.get("id").asText() : null;
                String name = (properties.get(champs.get("name").asText()) != null) ? (properties.get(champs.get("name").asText()).asText()) : null;
                String province = (properties.get(champs.get("province").asText()) != null) ? properties.get(champs.get("province").asText()).asText() : null;
                String region = (properties.get(champs.get("region").asText()) != null) ? properties.get(champs.get("region").asText()).asText() : null;
                JsonNode geometry = (JsonNode) element.get("geometry");
                String type_json = geometry.get("type").asText();
                ArrayNode coordinates = (ArrayNode) geometry.get("coordinates");
                String strJson = "{\"name\":\"" + name +
                        "\" ,\"address\":\"" + name +
                        "\",\"type\":\"" + "cities" +
                        "\",\"region\":\"" + region +
                        "\",\"province\":\"" + province +
                        "\",\"coordinates\":{\"type\":\"" + type_json + "\",\"coordinates\":" + coordinates + "}}";

                BulkRequest request = new BulkRequest();
                IndexRequest indexRequest = new IndexRequest("cities", "_doc", id);
                indexRequest.source(strJson, XContentType.JSON);
                request.add(indexRequest);
                client.bulk(request, RequestOptions.DEFAULT);

            }

        }else if (Objects.equals(index, "Quartiers")){

            for (int i = 0; i < elements.size(); i++) {

                JsonNode element = (JsonNode) elements.get(i);
                JsonNode properties = (JsonNode) element.get("properties");
                String id = (properties.get("id") != null) ? properties.get("id").asText() : null;
                String name = (properties.get(champs.get("name").asText()) != null) ? (properties.get(champs.get("name").asText()).asText()) : null;
                String city = (properties.get(champs.get("city").asText()) != null) ? properties.get(champs.get("city").asText()).asText() : null;
                String province = (properties.get(champs.get("province").asText()) != null) ? properties.get(champs.get("province").asText()).asText() : null;
                String region = (properties.get(champs.get("region").asText()) != null) ? properties.get(champs.get("region").asText()).asText() : null;
                JsonNode geometry = (JsonNode) element.get("geometry");
                String type_json = geometry.get("type").asText();
                ArrayNode coordinates = (ArrayNode) geometry.get("coordinates");
                String address = name + (city != null ? (", " + city) : " ");

                String strJson = "{\"name\":\"" + name +
                        "\" ,\"address\":\"" + address +
                        "\",\"type\":\"" + "quarters" +
                        "\",\"region\":\"" + region +
                        "\",\"province\":\"" + province +
                        "\",\"city\":\"" + city +
                        "\",\"coordinates\":{\"type\":\"" + type_json + "\",\"coordinates\":" + coordinates + "}}";

                BulkRequest request = new BulkRequest();
                IndexRequest indexRequest = new IndexRequest("quarters", "_doc", id);
                indexRequest.source(strJson, XContentType.JSON);
                request.add(indexRequest);
                client.bulk(request, RequestOptions.DEFAULT);

            }



        }
    }
}



/*
 try {

final SearchResponse response = client.search(request, RequestOptions.DEFAULT);
final List<Streets> addresses2 = new ArrayList<>();
        SearchHits hits = response.getHits();
        Iterator<SearchHit> iterator = hits.iterator();
        Map<String, SearchHit> distinctObjects = new HashMap<String,SearchHit>();
        while (iterator.hasNext()) {
        SearchHit searchHit = (SearchHit) iterator.next();
        Map<String, Object> source = searchHit.getSourceAsMap();
        if(source.get("name") != null){
        distinctObjects.put(source.get("name").toString(), searchHit);

        }

        }

        distinctObjects.forEach((k,v)-> {
        try {
        addresses2.add(
        MAPPER.readValue(v.getSourceAsString(), Streets.class)
        );
        } catch (JsonProcessingException e) {
        e.printStackTrace();
        }
        });

        return addresses2;

        } catch (Exception e) {
        LOG.error(e.getMessage(), e);
        return Collections.emptyList();
        }*/
