package com.example.GeocodingApp.repository;

import com.example.GeocodingApp.document.Quarters;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface QuarterRepository extends ElasticsearchRepository<Quarters,String> {


}
