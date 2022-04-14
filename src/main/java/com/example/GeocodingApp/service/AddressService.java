package com.example.GeocodingApp.service;

import com.example.GeocodingApp.document.Address;
import com.example.GeocodingApp.document.SearchTermDTO;
import com.example.GeocodingApp.helper.Indices;
import com.example.GeocodingApp.repository.AddressRepository;
import com.example.GeocodingApp.search.SearchUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
public class AddressService {
    private static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final Logger LOG = LoggerFactory.getLogger(AddressService.class);

    private final RestHighLevelClient client;

    @Autowired
    public AddressService(RestHighLevelClient client) {
        this.client = client;
    }

    public List<Address> search(final SearchTermDTO dto) {
        final SearchRequest request = SearchUtils.buildSearchRequest(
                Indices.ADDRESS_INDEX,
                dto
        );

        return searchInternal(request);
    }


    private List<Address> searchInternal(final SearchRequest request) {
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




   /* public void save(final Address address){
        repository.save(address);
    }

    public Address findById(final String id){
        return repository.findById(id).orElse(null);
    }

    public Address getSearchTerm(String searchTerm) {
        return repository.findById(searchTerm).orElse(null);
    }*/
}
