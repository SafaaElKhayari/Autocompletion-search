package com.example.GeocodingApp.service;
import com.example.GeocodingApp.document.Feature;
import com.example.GeocodingApp.document.SearchTermDTO;
import com.example.GeocodingApp.helper.Indices;
import com.example.GeocodingApp.search.SearchUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class AddressService {

   /* private static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final Logger LOG = LoggerFactory.getLogger(AddressService.class);
    private final ConvertToPOJO convertToPOJO;

    private final RestHighLevelClient client;

    @Autowired
    public AddressService(ConvertToPOJO convertToPOJO, RestHighLevelClient client) {
        this.convertToPOJO = convertToPOJO;
        this.client = client;
    }


    public List<Feature> search(final SearchTermDTO dto) {

        final SearchRequest request = SearchUtils.buildSearchRequest(
                Indices.DATA_INDEX,
                dto
        );

        return searchInternal2(request);
    }





    private List<Feature> searchInternal2(final SearchRequest request) {
        if (request == null) {
            LOG.error("Failed to build search request");
            return Collections.emptyList();
        }

        try {
            System.out.println("hi from service ");
            final SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            final SearchHit[] searchHits = response.getHits().getHits();
            final List<Feature> addresses = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                //System.out.println(hit.getSourceAsString());
                addresses.add(
                        MAPPER.readValue(hit.getSourceAsString(), Feature.class)
                );
            }

            return addresses;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }
*/


}
