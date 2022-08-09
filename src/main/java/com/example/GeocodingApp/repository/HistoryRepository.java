package com.example.GeocodingApp.repository;

import com.example.GeocodingApp.document.Cities;
import com.example.GeocodingApp.document.History;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface HistoryRepository extends ElasticsearchRepository<History,String> {

    
}
