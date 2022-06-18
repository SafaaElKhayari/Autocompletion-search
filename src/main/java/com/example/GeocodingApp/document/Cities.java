package com.example.GeocodingApp.document;
import com.example.GeocodingApp.helper.Indices;
import com.example.GeocodingApp.service.GeoShape;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Document(indexName = Indices.CITIES_INDEX)
@Setting(settingPath = "static/mapping/address.json")

public class Cities extends Address {
    @Id
    @Field(type = FieldType.Keyword)
    private String id;
    @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
    private String name;
    @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
    private String address;
    @Field(type = FieldType.Keyword)
    private String type;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GeoShape getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(GeoShape coordinates) {
        this.coordinates = coordinates;
    }
}
