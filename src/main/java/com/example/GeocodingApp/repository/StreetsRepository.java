package com.example.GeocodingApp.repository;
import com.example.GeocodingApp.document.Highway;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface StreetsRepository extends ElasticsearchRepository<Highway,String> {

}
