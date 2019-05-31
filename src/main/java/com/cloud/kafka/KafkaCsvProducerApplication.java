package com.cloud.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cloud.kafka.constants.KafkaConstants;
import com.cloud.kafka.csv.CSVProducer;

@SpringBootApplication
public class KafkaCsvProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaCsvProducerApplication.class, args);
		new CSVProducer(KafkaConstants.FILE_LOCATION);
	}

}
