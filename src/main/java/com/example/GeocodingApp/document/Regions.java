package com.example.GeocodingApp.document;

import com.example.GeocodingApp.helper.Indices;
import com.example.GeocodingApp.service.GeoShape;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.geo.GeoJsonMultiPolygon;

@Document(indexName = Indices.REGIONS_INDEX)
@Setting(settingPath = "static/mapping/address.json")
public class Regions {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
    private String name;
    @Field(type = FieldType.Keyword)
    private String type;
    @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
    private String address;
    @GeoShapeField
    private GeoShape coordinates ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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



    public GeoShape getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(GeoShape coordinates) {
        this.coordinates = coordinates;
    }


}
