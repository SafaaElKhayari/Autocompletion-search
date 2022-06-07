package com.example.GeocodingApp.document;
import com.example.GeocodingApp.helper.Indices;
import com.example.GeocodingApp.service.GeoShape;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.geo.GeoJsonPolygon;

@Document(indexName = Indices.AREAS_INDEX)
@Setting(settingPath = "static/mapping/address.json")

public class Areas {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
    private String name;
    @Field(type = FieldType.Keyword)
    private String type;
    @Field(type = FieldType.Keyword)
    private String city;
    @GeoShapeField
    private GeoJsonPolygon coordinates ;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public GeoJsonPolygon getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(GeoJsonPolygon coordinates) {
        this.coordinates = coordinates;
    }
}
