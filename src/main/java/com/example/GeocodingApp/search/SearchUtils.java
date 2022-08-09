package com.example.GeocodingApp.search;
import com.example.GeocodingApp.document.SearchTermDTO;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;


public class SearchUtils {


/*Search Request*/

    public static SearchRequest buildSearchRequest(final String indexName1,final String indexName2,final String indexName3,final String indexName4,final String indexName5,final String indexName6, final SearchTermDTO dto){

        try{
            final SearchSourceBuilder builder = new SearchSourceBuilder();
            builder.query(getQueryBuilder(dto));
            //builder.sort(SortBuilders.scoreSort().order(SortOrder.DESC));
            //builder.sort(SortBuilders.fieldSort(FieldSortBuilder.DOC_FIELD_NAME));
            builder.size(10);
            SearchRequest request = new SearchRequest(indexName1,indexName2,indexName3,indexName4,indexName5,indexName6);
            request.source(builder).searchType(SearchType.DFS_QUERY_THEN_FETCH);

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


         return QueryBuilders.matchQuery("address", dto.getSearchTerm()).fuzziness("AUTO");


                 /*QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("address", dto.getSearchTerm()))
                .should(QueryBuilders.matchQuery("name", dto.getSearchTerm())).minimumShouldMatch(1);
*/


}
    }

