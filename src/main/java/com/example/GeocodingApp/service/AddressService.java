package com.example.GeocodingApp.service;

import com.example.GeocodingApp.document.Address;
import com.example.GeocodingApp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository repository;

    @Autowired
    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }


    public void save(final Address address){
        repository.save(address);
    }

    public Address findById(final String id){
        return repository.findById(id).orElse(null);
    }
}
