package com.example.GeocodingApp.search;
import com.example.GeocodingApp.document.SearchTermDTO;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;


public class SearchUtils {


/*Search Request*/

    public static SearchRequest buildSearchRequest(final String indexName, final SearchTermDTO dto){

        try{
            final SearchSourceBuilder builder = new SearchSourceBuilder();
            builder.query(getQueryBuilder(dto));
            builder.sort(SortBuilders.scoreSort().order(SortOrder.DESC));
            builder.sort("_score", SortOrder.DESC);


            //builder.trackScores(true);
           // builder.sort(SortBuilders.scoreSort().order(SortOrder.ASC));
            //builder.sort("_score", SortOrder.DESC);
           // builder.sort("name", SortOrder.DESC);


            SearchRequest request = new SearchRequest(indexName);
            request.source(builder);

            return request;

        }catch (final Exception e){
            e.printStackTrace();
            return null;
        }

    }


/* QUERY BUILDER */

    private static QueryBuilder getQueryBuilder(final SearchTermDTO dto) {
        if (dto == null) {
            return null;
        }

        //String query = "{"bool": {"must": [{"match_phrase": {"countryName": "Spain"}}], "must_not": [], "should": []}}";
        //QueryBuilder qb = QueryBuilders.wrapperQuery();
        return QueryBuilders.matchQuery("name", dto.getSearchTerm());



}
    }

