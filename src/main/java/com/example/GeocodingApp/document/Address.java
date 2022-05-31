package com.example.GeocodingApp.document;
import com.example.GeocodingApp.helper.Indices;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Document(indexName = Indices.ADDRESS_INDEX)
@Setting(settingPath = "static/Settings.json")
@Getter
@Setter
public class Address {

    @Id
    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
    private String description;

    @Field(type = FieldType.Text)
    private String lat;

    @Field(type = FieldType.Text)
    private String lon;

   /* @Field(type = FieldType.Object)
    @GeoPointField
    private GeoPoint location;

    GeoJsonMultiPoint coordinates;*/



    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
