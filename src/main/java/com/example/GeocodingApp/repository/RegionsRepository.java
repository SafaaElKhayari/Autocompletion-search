package com.example.GeocodingApp.repository;

import com.example.GeocodingApp.document.Regions;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RegionsRepository extends ElasticsearchRepository<Regions,String> {
}
