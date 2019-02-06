package com.wgc.green.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MongoDBSeeder implements CommandLineRunner{
	@Autowired
	private SensorDataRepository sensorDataRepository;
	
	public MongoDBSeeder(SensorDataRepository sensorDataRepository) {
		this.sensorDataRepository = sensorDataRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		this.sensorDataRepository.deleteAll();
		
	}

}
