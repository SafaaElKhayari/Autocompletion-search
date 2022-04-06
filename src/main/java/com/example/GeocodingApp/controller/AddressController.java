package com.example.GeocodingApp.controller;

import com.example.GeocodingApp.document.Address;
import com.example.GeocodingApp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    private final AddressService service;

    @Autowired
    public AddressController(AddressService service) {
        this.service = service;
    }

    @PostMapping
    public void saveAddress (@RequestBody final Address address){
        service.save(address);
    }

    @GetMapping("/{id}")
    public Address getAddress(@PathVariable final String id){
        return service.findById(id);
    }

}
