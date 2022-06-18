package com.example.GeocodingApp.repository;

import com.example.GeocodingApp.document.PrefProv;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PrefProvRepository extends ElasticsearchRepository<PrefProv,String> {


}
