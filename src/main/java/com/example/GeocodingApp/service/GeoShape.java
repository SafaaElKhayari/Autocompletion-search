package com.example.GeocodingApp.service;

import com.fasterxml.jackson.databind.JsonNode;

public class GeoShape {
    String type;
    JsonNode coordinates;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JsonNode getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(JsonNode coordinates) {
        this.coordinates = coordinates;
    }
}
