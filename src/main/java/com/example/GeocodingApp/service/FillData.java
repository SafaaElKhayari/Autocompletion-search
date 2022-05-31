package com.example.GeocodingApp.service;

import com.example.GeocodingApp.document.Input;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import java.io.FileReader;

public class FillData {
    //parse json file
    /*
    JSONParser parser = new JSONParser();
    Object object = parser.parse(new FileReader("input.json"));
    JSONObject jsonData = (JSONObject) object;
    // index parsed contents
    IndexQuery indexQuery = new IndexQueryBuilder().withIndexName(indexName).
            withSource(jsonData.toString()).build();
     elasticsearchTemplate.index(indexQuery);*/
}
