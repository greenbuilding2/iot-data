package com.wgc.green.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wgc.green.entity.SensorData;
import com.wgc.green.manager.RequestIotConfig;
import com.wgc.green.manager.SensorDataManager;
import com.wgc.green.repository.SensorDataRepository;

@RestController
@RequestMapping("/sensor_data")
public class SensorDataController {
	@Autowired
    private SensorDataManager sdManager;

	@CrossOrigin(origins = "*")
	@GetMapping("/building/{id}")
	public List<SensorData> getSensorDataByBuildingId(@PathVariable ("id") int buildId) {
		// dummy
		List<SensorData> sensorDataList = new LinkedList<>();
		sensorDataList = new ArrayList<>();
		SensorData sd = new SensorData();
		sensorDataList.add(sd);
		sensorDataList.add(sd);
		return sensorDataList;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/floor/{id}")
	public List<SensorData> getSensorDataByFloorId(@PathVariable ("id") int floorId) {
		// dummy
		List<SensorData> sensorDataList = new LinkedList<>();
		sensorDataList = new ArrayList<>();
		SensorData sd = new SensorData();
		sensorDataList.add(sd);
		sensorDataList.add(sd);
		return sensorDataList;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/room/{id}")
	public List<SensorData> getSensorDataByRoomId(@PathVariable ("id") int roomId, @RequestParam(value="startTime") Date startTime, @RequestParam(value="endTime") Date endTime) {
		// dummy
		List<SensorData> sensorDataList = new LinkedList<>();

		return sensorDataList;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/sensor/{id}")
	public List<SensorData> getSensorDataBySensorId(@PathVariable ("id") long id, @RequestParam(value="startTime") Date startTime, @RequestParam(value="endTime") Date endTime) {
		return sdManager.getSensorDataBySensorIdAndTime(id, startTime, endTime);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/node/{id}")
	public List<SensorData> getSensorDataByNodeId(@PathVariable ("id") long id, @RequestParam(value="startTime") Date startTime, @RequestParam(value="endTime") Date endTime) {
		return sdManager.getSensorDataByNodeIdAndTime(id, startTime, endTime);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/cluster/{id}")
	public List<SensorData> getSensorDataByClusterId(@PathVariable ("id") long id, @RequestParam(value="startTime") Date startTime, @RequestParam(value="endTime") Date endTime) {
		return sdManager.getSensorDataByClusterIdAndTime(id, startTime, endTime);
	}

	@CrossOrigin(origins = "*")
	@PostMapping
	public void addSensorData(@RequestBody List<SensorData> sensorDataList) {
        System.out.println("here in post>>>"+sensorDataList.get(0).toString());
		sdManager.addSensorData(sensorDataList);
	}

	@CrossOrigin(origins = "*")
	@PutMapping
	public void updateSensorData(@RequestBody List<SensorData> sensorDataList) {
		sdManager.updateSensorData(sensorDataList);
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/{id}")
	public void deleteSensorData(@PathVariable ("id") String id ) {
		sdManager.deleteSensorData(id);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/testget")
	public List<SensorData> getSensorDataTest() {
		return sdManager.testGet();
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/testadd")
	public void addSensorDataTest() {
		List<SensorData> sensorDataList = new LinkedList<>();
		SensorData sd1 = new SensorData();
		sd1.setDate(new Date("Sat Nov 17 00:00:00 PST 2018"));
		sd1.setData(123.0);
		sd1.setSensorId(1);
//		sd1.setNodeId(2);
//		sd1.setClusterId(3);

		SensorData sd2 = new SensorData();
		sd2.setDate(new Date("Sat Nov 17 00:00:01 PST 2018"));
		sd2.setSensorId(1);
		sd2.setData(321.0);
//		sd2.setNodeId(2);
//		sd2.setClusterId(3);


		SensorData sd3 = new SensorData();
		sd3.setDate(new Date("Sat Nov 17 00:00:02 PST 2018"));
		sd3.setSensorId(2);
//		sd3.setNodeId(3);
//		sd3.setClusterId(3);
		sensorDataList.add(sd1);
		sensorDataList.add(sd2);
		sensorDataList.add(sd3);
		sdManager.addSensorData(sensorDataList);
	}


	@CrossOrigin(origins = "*")
	@GetMapping("/testrestcall")
	public List<SensorData> getSensorDataToTestIotConifgCall() {
		return sdManager.testIotConifgCall();
	}

}
