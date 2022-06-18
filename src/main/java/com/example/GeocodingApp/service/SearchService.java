package com.example.GeocodingApp.service;
import com.example.GeocodingApp.document.Address;
import com.example.GeocodingApp.document.SearchTermDTO;
import com.example.GeocodingApp.helper.Indices;
import com.example.GeocodingApp.search.SearchUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        final SearchRequest request = SearchUtils.buildSearchRequest(Indices.REGIONS_INDEX,
                Indices.STREETS_INDEX
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
