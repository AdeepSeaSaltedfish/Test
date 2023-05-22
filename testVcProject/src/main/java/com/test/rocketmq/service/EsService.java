package com.test.rocketmq.service;//package com.test.rocketmq.service;
//
//import com.alibaba.fastjson.JSON;
//import com.test.rocketmq.model.User;
//import com.test.rocketmq.model.UserEs;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.bulk.BulkResponse;
//import org.elasticsearch.action.delete.DeleteRequest;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.search.SearchResponse;
//import org.elasticsearch.action.support.master.AcknowledgedResponse;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.action.update.UpdateResponse;
//import org.elasticsearch.client.RequestOptions;
//import org.elasticsearch.client.RestHighLevelClient;
//import org.elasticsearch.client.indices.CreateIndexRequest;
//import org.elasticsearch.client.indices.CreateIndexResponse;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.index.query.TermQueryBuilder;
//import org.elasticsearch.rest.RestStatus;
//import org.elasticsearch.search.aggregations.AggregationBuilders;
//import org.elasticsearch.search.aggregations.Aggregations;
//import org.elasticsearch.search.aggregations.bucket.terms.Terms;
//import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
//import org.elasticsearch.search.aggregations.metrics.Avg;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.elasticsearch.xcontent.XContentType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
///**
// * @Author: yjj
// * @Date: 2022/06/29/11:03
// * @Description:
// */
//@Slf4j
//@Service
//public class EsService {
//
//    @Autowired
//    @Qualifier("restHighLevelClient")
//    public RestHighLevelClient client;
//
//    private static final String ES_INDEX = "first_index";
//
//    public boolean createUserIndex(String index) throws IOException {
//
//        //创建索引(建表)
//        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
//        createIndexRequest.settings(Settings.builder()
//                .put("index.number_of_shards", 1)
//                .put("index.number_of_replicas", 0)
//        );
//        createIndexRequest.mapping("{\n" +
//                "  \"properties\": {\n" +
//                "    \"city\": {\n" +
//                "      \"type\": \"keyword\"\n" +
//                "    },\n" +
//                "    \"sex\": {\n" +
//                "      \"type\": \"keyword\"\n" +
//                "    },\n" +
//                "    \"name\": {\n" +
//                "      \"type\": \"keyword\"\n" +
//                "    },\n" +
//                "    \"id\": {\n" +
//                "      \"type\": \"keyword\"\n" +
//                "    },\n" +
//                "    \"age\": {\n" +
//                "      \"type\": \"integer\"\n" +
//                "    }\n" +
//                "  }\n" +
//                "}", XContentType.JSON);
//
//        CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
//        return createIndexResponse.isAcknowledged();
//    }
//
//    //删除索引(删表)
//    public Boolean deleteUserIndex(String index) throws IOException {
//        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
//        AcknowledgedResponse deleteIndexResponse = client.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
//        return deleteIndexResponse.isAcknowledged();
//    }
//
//    //创建文档(插入数据)
//    public Boolean createUserDocument(UserEs document) throws Exception {
//        UUID uuid = UUID.randomUUID();
//        document.setId(uuid.toString());
//        IndexRequest indexRequest = new IndexRequest(ES_INDEX)
//                .id(document.getId())
//                .source(JSON.toJSONString(document), XContentType.JSON);
//        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
//        return indexResponse.status().equals(RestStatus.OK);
//    }
//
//    //批量创建文档
//    public Boolean bulkCreateUserDocument(List<UserEs> documents) throws IOException {
//        BulkRequest bulkRequest = new BulkRequest();
//        for (UserEs document : documents) {
//            String id = UUID.randomUUID().toString();
//            document.setId(id);
//            IndexRequest indexRequest = new IndexRequest(ES_INDEX)
//                    .id(id)
//                    .source(JSON.toJSONString(document), XContentType.JSON);
//            bulkRequest.add(indexRequest);
//        }
//        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
//        return bulkResponse.status().equals(RestStatus.OK);
//    }
//
//    //查看文档
//    public UserEs getUserDocument(String id) throws IOException {
//        GetRequest getRequest = new GetRequest(ES_INDEX, id);
//        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
//        UserEs result = new UserEs();
//        if (getResponse.isExists()) {
//            String sourceAsString = getResponse.getSourceAsString();
//            result = JSON.parseObject(sourceAsString, UserEs.class);
//        } else {
//            log.error("没有找到该 id 的文档");
//        }
//        return result;
//    }
//    //更新文档
//    public Boolean updateUserDocument(UserEs document) throws Exception {
//        UserEs resultDocument = getUserDocument(document.getId());
//        UpdateRequest updateRequest = new UpdateRequest(ES_INDEX, resultDocument.getId());
//        updateRequest.doc(JSON.toJSONString(document), XContentType.JSON);
//        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);
//        return updateResponse.status().equals(RestStatus.OK);
//    }
//
//    //删除文档
//    public String deleteUserDocument(String id) throws Exception {
//        DeleteRequest deleteRequest = new DeleteRequest(ES_INDEX, id);
//        DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);
//        return response.getResult().name();
//    }
//
//    //搜索操作
////    public List<UserEs> searchUserByCity(String city) throws Exception {
////        SearchRequest searchRequest = new SearchRequest();
////        searchRequest.indices(ES_INDEX);
////        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
////        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("city", city);
////        searchSourceBuilder.query(termQueryBuilder);
////        searchRequest.source(searchSourceBuilder);
////        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
////        return getSearchResult(searchResponse);
////    }
//
//    //聚合搜索
//    public List<UserEs> aggregationsSearchUser() throws Exception {
//        SearchRequest searchRequest = new SearchRequest(ES_INDEX);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        TermsAggregationBuilder aggregation = AggregationBuilders.terms("by_city")
//                .field("city")
//                .subAggregation(AggregationBuilders
//                        .avg("average_age")
//                        .field("age"));
//        searchSourceBuilder.aggregation(aggregation);
//        searchRequest.source(searchSourceBuilder);
//        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
//        Aggregations aggregations = searchResponse.getAggregations();
//        Terms byCityAggregation = aggregations.get("by_city");
//        List<UserEs> userCityList = new ArrayList<>();
//        for (Terms.Bucket buck : byCityAggregation.getBuckets()) {
//            UserEs userCityDTO = new UserEs();
//            userCityDTO.setUsername(buck.getKeyAsString());
//            userCityDTO.setDocTotalNum(buck.getDocCount());
//            // 获取子聚合
//            Avg averageBalance = buck.getAggregations().get("average_age");
//            userCityDTO.setAvgAge(averageBalance.getValue());
//            userCityList.add(userCityDTO);
//        }
//        return userCityList;
//    }
//}
//
//
