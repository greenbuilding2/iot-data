package com.wgc.green.repository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.wgc.green.entity.SensorData;

@Component
@Repository
public interface SensorDataRepository extends MongoRepository<SensorData, String> {

    MongoClientURI uri  = new MongoClientURI("mongodb://data:greendata2019@ds123625.mlab.com:23625/greendata");
    MongoClient client = new MongoClient(uri);
	MongoTemplate mongoTemplate = new MongoTemplate(client, "greendata");

	public default List<SensorData> findBySensorIdAndTime(long sensorId, Date startTime, Date endTime) {
        Query query = new Query();
        Pageable pageableRequest = PageRequest.of(0, 100);
        query.addCriteria(Criteria.where("sensorId").is(sensorId).and("date").lte(endTime).gte(startTime));
        query.with(pageableRequest);
		return mongoTemplate.find(query, SensorData.class);
	}

//	public default List<SensorData> findByNodeIdAndTime(long nodeId, Date startTime, Date endTime) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("nodeId").is(nodeId).and("date").lte(endTime).gte(startTime));
//		return mongoTemplate.find(query, SensorData.class);
//	}
//
//	public default List<SensorData> findByClusterIdAndTime(long clusterId, Date startTime, Date endTime) {
//		Query query = new Query();
//		query.addCriteria(Criteria.where("clusterId").is(clusterId).and("date").lte(endTime).gte(startTime));
//		return mongoTemplate.find(query, SensorData.class);
//	}


}
