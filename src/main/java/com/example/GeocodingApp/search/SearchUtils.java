package com.example.GeocodingApp.search;

import com.example.GeocodingApp.document.SearchTermDTO;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

public class SearchUtils {


/*Search Request*/

    public static SearchRequest buildSearchRequest(final String indexName, final SearchTermDTO dto){

        try{
            final SearchSourceBuilder builder = new SearchSourceBuilder()
                    .postFilter(getQueryBuilder(dto));

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

        final List<String> fields = dto.getFields();
        if (CollectionUtils.isEmpty(fields)) {
            return null;
        }

        if (fields.size() > 1) {
            final MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(dto.getSearchTerm())
                    .type(MultiMatchQueryBuilder.Type.CROSS_FIELDS)
                    .operator(Operator.AND);

            fields.forEach(queryBuilder::field);

            return queryBuilder;
        }

        return fields.stream()
                .findFirst()
                .map(field ->
                        QueryBuilders.matchQuery(field, dto.getSearchTerm())
                                .operator(Operator.AND))
                .orElse(null);
    }
}
