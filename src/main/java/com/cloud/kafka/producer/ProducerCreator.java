package com.cloud.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;

import com.cloud.kafka.constants.KafkaConstants;
import com.cloud.kafka.serializer.CustomSerializer;
import com.google.gson.JsonObject;

public class ProducerCreator {
	
	public static Producer<String, JsonObject> createProducer(){
		Properties props = new Properties();
		
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.BROKER_LIST);
		props.put(ProducerConfig.CLIENT_ID_CONFIG, KafkaConstants.CLIENT_ID);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomSerializer.class.getName());
		
		props.put("security.protocol", KafkaConstants.kafkaProtocol);
		props.put(SslConfigs.SSL_PROTOCOL_CONFIG, KafkaConstants.sslProtocol);
		
		props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, KafkaConstants.TRUSTSTORE_LOCATION);
		props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, KafkaConstants.TRUSTSTORE_PASSWORD);
		
		props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, KafkaConstants.KEYSTORE_LOCATION);
		props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, KafkaConstants.KEYSTORE_PASSWORD);
		props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, KafkaConstants.KEYPAIR_PASSWORD);
		
		return new KafkaProducer<>(props);	
	}

}
