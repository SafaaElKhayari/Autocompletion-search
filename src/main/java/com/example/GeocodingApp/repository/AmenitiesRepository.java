package com.example.GeocodingApp.repository;

import com.example.GeocodingApp.document.Amenity;
import com.example.GeocodingApp.document.Cities;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AmenitiesRepository  extends ElasticsearchRepository<Amenity,String> {
}
