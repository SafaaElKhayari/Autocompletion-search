package com.example.GeocodingApp.document;
import com.example.GeocodingApp.service.GeoShape;

public class Address {

     String name;
     String type;
     String city;
     String address;
     String region;
     String province;
     String street_address;
     GeoShape coordinates ;

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public GeoShape getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(GeoShape coordinates) {
        this.coordinates = coordinates;
    }




}
