package com.example.GeocodingApp.repository;
import com.example.GeocodingApp.document.Streets;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface StreetsRepository extends ElasticsearchRepository<Streets,String> {

}
