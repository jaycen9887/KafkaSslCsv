package com.cloud.kafka.serializer;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonObject;

public class CustomSerializer implements Serializer<JsonObject>{
	private Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {}

	//Serializes the JsonObject into a byte array. Can easily be deserialized by converting them back into a JsonObject. 
	@Override
	public byte[] serialize(String topic, JsonObject data) {
		byte[] retVal = null;
		
		try {
			retVal = data.toString().getBytes();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return retVal;
	}

	@Override
	public void close() {}

}
