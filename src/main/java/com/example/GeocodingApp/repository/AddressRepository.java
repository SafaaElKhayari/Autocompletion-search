package com.example.GeocodingApp.repository;

import com.example.GeocodingApp.document.Address;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface AddressRepository  extends ElasticsearchRepository<Address,String> {
    //List<Address> search(QueryBuilder query);

}
