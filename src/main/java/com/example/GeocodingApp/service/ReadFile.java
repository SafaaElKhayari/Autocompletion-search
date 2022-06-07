package com.example.GeocodingApp.service;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;


@Service
public class ReadFile {


    private final RestHighLevelClient client;

    @Autowired
    public ReadFile(RestHighLevelClient client) {
        this.client = client;
    }


    public  void extractJSON() throws FileNotFoundException {

        JsonObject object = (JsonObject) new JsonParser().parse(new FileReader("C:\\Users\\safaaa\\Downloads\\streetsData.geojson"));
        Map<String, JsonObject> distinctObjects = new HashMap<String,JsonObject>();
        JsonArray elements = (JsonArray) object.get("features");
        String name;

        for(int i=0; i<elements.size(); i++){

                JsonObject element = (JsonObject) elements.get(i);
                JsonObject properties = (JsonObject) element.get("properties");

                if(properties.get("name:fr")!= null){
                    name = properties.get("name:fr").getAsString();
                }else{
                    name = properties.get("name").getAsString();
                }

                JsonObject returnValue = distinctObjects.put(name, element);

                JsonObject geometry = (JsonObject) element.get("geometry");
                JsonArray coordinates = (JsonArray) geometry.get("coordinates");

                if (returnValue==null){
                    try {

                        String strJson ="{\"id\":\"" + Integer.toString(i+1) + "\",\"name\":\"" + name + "\" ,\"city\":\"Casablanca\",\"type\":\"streets\",\"coordinates\":{\"type\":\"LineString\",\"coordinates\":" + coordinates + "}}";
                        BulkRequest request = new BulkRequest();
                        IndexRequest indexRequest =  new IndexRequest("streets", "_doc", Integer.toString(i+1));
                        indexRequest.source(strJson, XContentType.JSON);
                        request.add(indexRequest);
                        client.bulk(request, RequestOptions.DEFAULT);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }


                }






            }

    }

}
