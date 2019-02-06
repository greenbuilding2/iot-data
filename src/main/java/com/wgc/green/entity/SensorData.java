package com.wgc.green.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@Document(collection = "sensor_data")
public class SensorData {
	@Id
	private String id;
	@Indexed(direction = IndexDirection.ASCENDING)    // @Indexed(unique = true)
    private long sensorId;
//	@Transient
//  @Field("Model")
//  @JsonProperty("unit")
    private String unit;
    private Double data;
	@Indexed(direction = IndexDirection.ASCENDING)
    private String type;
	@Indexed(direction = IndexDirection.ASCENDING)
    private Date date;
//  private String modelNum;
//  private String seriesNum;


//	@Indexed(direction = IndexDirection.ASCENDING)
//	private long nodeId;
//	@Indexed(direction = IndexDirection.ASCENDING)
//	private long clusterId;
//	@Indexed(direction = IndexDirection.ASCENDING)
//	private long roomId;
//	@Indexed(direction = IndexDirection.ASCENDING)
//	private long floorId;
//	private long buildingId;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getSensorId() {
		return sensorId;
	}
	public void setSensorId(long sensorId) {
		this.sensorId = sensorId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getData() {
		return data;
	}
	public void setData(Double data) {
		this.data = data;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
