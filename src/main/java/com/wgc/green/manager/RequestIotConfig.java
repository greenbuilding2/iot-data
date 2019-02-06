package com.wgc.green.manager;

import java.util.List;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import com.wgc.green.entity.Location;
import com.wgc.green.entity.SensorData;

@Component
public class RequestIotConfig {
    RestTemplate restTemplate = new RestTemplate();
    @Value("${config.data.infra_manager.ip}")
    private String IOT_CONFIG_SEVER_IP;
    @Value("${config.data.infra_manager.port}")
    private String PORT;

	public Location getLocationBySensorId(long sensorId) {
		Location loc = new Location();
        String url = "http://" + IOT_CONFIG_SEVER_IP + ":" + PORT + "/sensors/" + sensorId;
        System.out.println("url is >>>>"+url);
		ResponseEntity<String> sensorResponse = this.restTemplate.getForEntity(url, String.class);
		JSONObject sensorObj = new JSONObject(sensorResponse.getBody());
		loc.setNodeId(sensorObj.getLong("node_id"));
		loc.setClusterId(sensorObj.getLong("cluster_id"));

		url = "http://" + IOT_CONFIG_SEVER_IP + ":" + PORT + "/nodes/" + loc.getNodeId();
		ResponseEntity<String> nodeResponse = this.restTemplate.getForEntity(url, String.class);
		JSONObject nodeObj = new JSONObject(nodeResponse.getBody());
		loc.setRoomId(nodeObj.getLong("room_id"));

		url = "http://" + IOT_CONFIG_SEVER_IP + ":" + PORT + "/clusters/" + loc.getClusterId();
		ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class);
		JSONObject obj = new JSONObject(response.getBody());
		loc.setFloorId(obj.getLong("floor_id"));
		loc.setBuildingId(obj.getLong("building_id"));

		return loc;
	}


	public List<Long> getSensorIDListByNodeId(long nodeId) {
		String url = "http://" + IOT_CONFIG_SEVER_IP + ":" + PORT + "/nodes/" + nodeId + "/sensors";
	    ResponseEntity<List<Long>> response = restTemplate.exchange(
	      url,
	      HttpMethod.GET,
	      null,
	      new ParameterizedTypeReference<List<Long>>(){});
	    List<Long> sensorIdList = response.getBody();

		return sensorIdList;
	}


	public List<Long> getSensorIDListByClusterId(long clusterId) {
		String url = "http://" + IOT_CONFIG_SEVER_IP + ":" + PORT + "/clusters/" + clusterId + "/sensors";
	    ResponseEntity<List<Long>> response = restTemplate.exchange(
	      url,
	      HttpMethod.GET,
	      null,
	      new ParameterizedTypeReference<List<Long>>(){});
	    List<Long> sensorIdList = response.getBody();

		return sensorIdList;
	}

	public List<SensorData> testRestAPICall() {
		String url = "http://" + IOT_CONFIG_SEVER_IP + ":" + PORT + "/sensor_data/testget";
	    ResponseEntity<List<SensorData>> response = restTemplate.exchange(
	      url,
	      HttpMethod.GET,
	      null,
	      new ParameterizedTypeReference<List<SensorData>>(){});
	    List<SensorData> sensorDataList = response.getBody();

		return sensorDataList;
	}
}
