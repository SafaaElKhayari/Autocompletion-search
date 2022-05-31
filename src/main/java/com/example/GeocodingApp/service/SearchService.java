package com.example.GeocodingApp.service;

import com.example.GeocodingApp.document.SearchTermDTO;
import com.example.GeocodingApp.document.Streets;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SearchService {

    private static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final Logger LOG = LoggerFactory.getLogger(AddressService.class);
    private final RestHighLevelClient client;

    @Autowired
    public SearchService(RestHighLevelClient client) {
        this.client = client;
    }


//Search *******************************************************************
    public List<Streets> SearchSuggestions(SearchTermDTO dto) {
        final SearchRequest request = SearchUtils.buildSearchRequest(
                Indices.STREETS_INDEX,
                dto
        );

        return searchInternal(request);
    }


//Search Internal  *******************************************************************

    private List<Streets> searchInternal(SearchRequest request) {
        if (request == null) {
            LOG.error("Failed to build search request");
            return Collections.emptyList();
        }

        try {
            System.out.println("Hey from the new service ");
            final SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            final SearchHit[] searchHits = response.getHits().getHits();
            final List<Streets> addresses = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                addresses.add(
                        MAPPER.readValue(hit.getSourceAsString(), Streets.class)
                );
            }

            return addresses;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return Collections.emptyList();
        }

    }


}
