package com.cloud.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cloud.kafka.constants.KafkaConstants;
import com.cloud.kafka.csv.CSVProducer;

@SpringBootApplication
public class KafkaSslCsvApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSslCsvApplication.class, args);
		new CSVProducer(KafkaConstants.FILE_LOCATION);
	}

}
