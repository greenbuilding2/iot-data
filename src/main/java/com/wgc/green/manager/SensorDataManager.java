package com.wgc.green.manager;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wgc.green.entity.Location;
import com.wgc.green.entity.SensorData;
import com.wgc.green.repository.SensorDataRepository;

@Component
public class SensorDataManager {
	@Autowired
	private SensorDataRepository sensorDataRepository;
	@Autowired
	private RequestIotConfig requestIotConfig;

	public List<SensorData> getSensorDataBySensorIdAndTime(long sensorId, Date startTime, Date endTime) {
		if(sensorId < 0 || startTime == null || endTime == null || startTime.compareTo(endTime) > 0) {
			return null;
		}
		return sensorDataRepository.findBySensorIdAndTime(sensorId, startTime, endTime);
	}

	public List<SensorData> getSensorDataByNodeIdAndTime(long nodeId, Date startTime, Date endTime) {
		if(nodeId < 0 || startTime == null || endTime == null || startTime.compareTo(endTime) > 0) {
			return null;
		}
		List<SensorData> sensorDataList = new LinkedList<>();
		List<Long> sensorIdList = requestIotConfig.getSensorIDListByNodeId(nodeId);
		for(Long id : sensorIdList) {
			sensorDataList.addAll( sensorDataRepository.findBySensorIdAndTime(id, startTime, endTime));

		}
		return sensorDataList;
	}

	public List<SensorData> getSensorDataByClusterIdAndTime(long clusterId, Date startTime, Date endTime) {
		if(clusterId < 0 || startTime == null || endTime == null || startTime.compareTo(endTime) > 0) {
			return null;
		}
		List<SensorData> sensorDataList = new LinkedList<>();
		List<Long> sensorIdList = requestIotConfig.getSensorIDListByClusterId(clusterId);
		for(Long id : sensorIdList) {
			sensorDataList.addAll( sensorDataRepository.findBySensorIdAndTime(id, startTime, endTime));
		}
		return sensorDataList;
	}

	public void addSensorData(List<SensorData> sensorDataList) {
		Map<Long, Location> locMap = new HashMap<>();
		for(SensorData sd : sensorDataList) {
            Location loc = locMap.get(sd.getSensorId());
            System.out.println("loc is >>>"+loc);
			if(loc == null) {
				loc = requestIotConfig.getLocationBySensorId(sd.getSensorId());
				locMap.put(sd.getSensorId(), loc);
			}
//			sensorDataRepository.insertByLocation(sd, loc);
			sensorDataRepository.insert(sd);
		}
	}

	public void updateSensorData(List<SensorData> sensorDataList) {
		// System.out.println("nodeId = " + sensorDataList.get(0).getNodeId());
		for(SensorData sd : sensorDataList) {
			Optional<SensorData> res = sensorDataRepository.findById( sd.getId());
			SensorData oldOne = null;
			if(res.isPresent()) {
				oldOne = res.get();
				if(sd.getSensorId() > 0) {
					oldOne.setSensorId( sd.getSensorId());
				}
				if(sd.getData() != null) {
					oldOne.setData( sd.getData());
				}
				if(sd.getDate() != null) {
					oldOne.setDate( sd.getDate());
				}

				if(sd.getUnit() != null) {
					oldOne.setUnit( sd.getUnit());
				}
				if(sd.getType() != null) {
					oldOne.setType( sd.getType());
				}
				sensorDataRepository.save(oldOne);  // save = upsert;  insert
			}
		}

	}

	public void deleteSensorData(String id) {
		sensorDataRepository.deleteById(id);
	}

	public List<SensorData> testGet() {
		// TODO Auto-generated method stub
		return sensorDataRepository.findAll();
	}

	public List<SensorData> testIotConifgCall() {
		// TODO Auto-generated method stub
		return requestIotConfig.testRestAPICall();
	}

}
