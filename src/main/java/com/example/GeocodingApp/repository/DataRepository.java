package com.example.GeocodingApp.repository;

import com.example.GeocodingApp.document.Feature;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface DataRepository extends ElasticsearchRepository<Feature,String> {
    //List<Address> search(QueryBuilder query);
}