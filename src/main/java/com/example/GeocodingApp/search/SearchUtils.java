package com.example.GeocodingApp.search;
import com.example.GeocodingApp.document.SearchTermDTO;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;


public class SearchUtils {


/*Search Request*/

    public static SearchRequest buildSearchRequest(final String indexName1,final String indexName2, final SearchTermDTO dto){

        try{
            final SearchSourceBuilder builder = new SearchSourceBuilder();
            builder.query(getQueryBuilder(dto));
            builder.sort(SortBuilders.scoreSort().order(SortOrder.DESC));
            builder.sort("_score", SortOrder.DESC);
            SearchRequest request = new SearchRequest(indexName1,indexName2);
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
        /*QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("name", dto.getSearchTerm()))
                .must(QueryBuilders.matchQuery("address", dto.getSearchTerm()));*/

        return QueryBuilders.matchQuery("name", dto.getSearchTerm());



}
    }

