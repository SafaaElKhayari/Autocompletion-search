package com.example.GeocodingApp.service;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;


@Service
public class ReadStreetFromOSMFile {


    private final RestHighLevelClient client;

    @Autowired
    public ReadStreetFromOSMFile(RestHighLevelClient client) {
        this.client = client;
    }


    public  void extractJSON() throws FileNotFoundException {

        JsonObject object = (JsonObject) new JsonParser().parse(new FileReader("C:\\Users\\safaaa\\Downloads\\data\\highway.geojson"));
        Map<String, JsonObject> distinctObjects = new HashMap<String,JsonObject>();
        JsonArray elements = (JsonArray) object.get("features");
        String name = "";
        String id;

        for(int i=0; i<elements.size(); i++){

                JsonObject element = (JsonObject) elements.get(i);
                JsonObject properties = (JsonObject) element.get("properties");
                id=properties.get("full_id").getAsString();

                if(properties.get("name:fr") != JsonNull.INSTANCE){
                    name = properties.get("name:fr").getAsString();
                }else if(properties.get("name") != JsonNull.INSTANCE){
                    name = properties.get("name").getAsString();
                }else{
                    name=null;
                }

                if (name!=null){
                    String[] splited = name.split("\\s+");
                    if(splited.length==2 ){
                        if(StringUtils.isNumeric(splited[1])){
                            name ="invalid";
                        }
                    }
                }


                JsonObject returnValue = distinctObjects.put(name, element);
                JsonObject geometry = (JsonObject) element.get("geometry");
                JsonArray coordinates = (JsonArray) geometry.get("coordinates");
                String type =geometry.get("type").getAsString();
                String city = properties.get("joinname:fr")!= JsonNull.INSTANCE ?properties.get("joinname:fr").getAsString():null;
                String region =properties.get("joinname:fr_2")!= JsonNull.INSTANCE ?properties.get("joinname:fr_2").getAsString(): null;
                String province =properties.get("name:fr_2")!= JsonNull.INSTANCE ? properties.get("name:fr_2").getAsString(): null;



                if (returnValue==null && name!=null && name != "invalid"){
                    try {
                        System.out.println(i);

                        String address = name+  (city != null? ( " , "+ city) :" ");
                        String strJson ="{\"name\":\"" + name + "\" ,\"address\":\"" + address + "\",\"city\":\"" + city + "\",\"region\":\"" + region + "\",\"province\":\"" + province + "\",\"type\":\"highway\",\"coordinates\":{\"type\":\"" + type + "\",\"coordinates\":" + coordinates + "}}";
                        BulkRequest request = new BulkRequest();
                        IndexRequest indexRequest =  new IndexRequest("highway", "_doc",id);
                        indexRequest.source(strJson, XContentType.JSON);
                        request.add(indexRequest);
                        client.bulk(request, RequestOptions.DEFAULT);

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }


                }






            }

    }

    public  void readAmenities() throws FileNotFoundException {

        JsonObject object = (JsonObject) new JsonParser().parse(new FileReader("C:\\Users\\safaaa\\Downloads\\data\\amenity.geojson"));
        Map<String, JsonObject> distinctObjects = new HashMap<String,JsonObject>();
        JsonArray elements = (JsonArray) object.get("features");
        String name = "";
        String id;

        for(int i=0; i<elements.size(); i++){

            JsonObject element = (JsonObject) elements.get(i);
            JsonObject properties = (JsonObject) element.get("properties");
            id=properties.get("full_id").getAsString();

            if(properties.get("name:fr") != JsonNull.INSTANCE){
                name = properties.get("name:fr").getAsString();
            }else if(properties.get("name") != JsonNull.INSTANCE){
                name = properties.get("name").getAsString();
            }else{
                name=null;
            }




            JsonObject returnValue = distinctObjects.put(name, element);
            JsonObject geometry = (JsonObject) element.get("geometry");
            JsonArray coordinates = (JsonArray) geometry.get("coordinates");
            String type =geometry.get("type").getAsString();
            String city = properties.get("name:fr_2")!= JsonNull.INSTANCE ?properties.get("name:fr_2").getAsString():null;
            String region =properties.get("name:fr_3")!= JsonNull.INSTANCE ?properties.get("name:fr_3").getAsString(): null;
            String province =properties.get("provincename:fr")!= JsonNull.INSTANCE ? properties.get("provincename:fr").getAsString(): null;
            String street_address =properties.get("addr:street")!= JsonNull.INSTANCE ? properties.get("addr:street").getAsString(): null;

            if (street_address!=null){
                String[] splited = street_address.split("\\s+");
                if(splited.length==2 ){
                    if(StringUtils.isNumeric(splited[1])){
                        street_address ="invalid";
                    }
                }
            }

            if (returnValue==null && name!=null && street_address != "invalid"){
                try {
                    System.out.println(i);

                    String address = name+(street_address!= null? ( " "+ street_address) :"") + (city != null? ( " , "+ city) :" ");
                    String strJson ="{\"name\":\"" + name + "\" ,\"address\":\"" + address + "\",\"street_address\":\"" + street_address + "\",\"city\":\"" + city + "\",\"region\":\"" + region + "\",\"province\":\"" + province + "\",\"type\":\"highway\",\"coordinates\":{\"type\":\"" + type + "\",\"coordinates\":" + coordinates + "}}";
                    BulkRequest request = new BulkRequest();
                    IndexRequest indexRequest =  new IndexRequest("amenities", "_doc",id);
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





/*    REGIONS

             JsonObject element = (JsonObject) elements.get(i);
                JsonObject properties = (JsonObject) element.get("properties");

                id=properties.get("id").getAsString();

                if(properties.get("name:fr")!= null){
                    name = properties.get("name:fr").getAsString();
                }else{
                    name = properties.get("name").getAsString();
                }
            System.out.println(name);
                JsonObject returnValue = distinctObjects.put(name, element);

                JsonObject geometry = (JsonObject) element.get("geometry");
                JsonArray coordinates = (JsonArray) geometry.get("coordinates");
                String type =geometry.get("type").getAsString();

                if (returnValue==null){
                    try {
                        String address = "Région "+ name;

                        System.out.println(address);
                        String strJson ="{\"name\":\"" + name + "\" ,\"address\":\"" + address + "\",\"type\":\"regions\",\"coordinates\":{\"type\":\"" + type + "\",\"coordinates\":" + coordinates + "}}";
                        BulkRequest request = new BulkRequest();
                        IndexRequest indexRequest =  new IndexRequest("regions", "_doc",Integer.toString(i));
                        indexRequest.source(strJson, XContentType.JSON);
                        request.add(indexRequest);
                        client.bulk(request, RequestOptions.DEFAULT);
 */
