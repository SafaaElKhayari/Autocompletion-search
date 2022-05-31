package com.example.GeocodingApp.document;

import com.example.GeocodingApp.helper.Indices;
import com.example.GeocodingApp.service.GeoShape;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.geo.GeoJsonLineString;


@Document(indexName = Indices.STREETS_INDEX)
@Setting(settingPath = "static/mapping/address.json")

public class Streets {
    @Id
    @Field(type=FieldType.Keyword)
    private String id ;

    @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
    private String name ;
    @Field(type = FieldType.Text)
    private String city ;
   @GeoShapeField
    private GeoShape coordinates ;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

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

    public GeoShape getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(GeoShape coordinates) {
        this.coordinates = coordinates;
    }
}
