package com.example.GeocodingApp.repository;

import com.example.GeocodingApp.document.Address;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AddressRepository  extends ElasticsearchRepository<Address,String> {

}
