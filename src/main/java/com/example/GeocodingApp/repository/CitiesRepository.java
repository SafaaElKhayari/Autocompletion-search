package com.example.GeocodingApp.repository;
import com.example.GeocodingApp.document.Cities;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CitiesRepository extends ElasticsearchRepository<Cities,String> {
}
