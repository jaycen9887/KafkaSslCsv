package com.cloud.kafka.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.cloud.kafka.constants.KafkaConstants;
import com.cloud.kafka.producer.ProducerCreator;
import com.google.gson.JsonObject;

public class CSVProducer {

	public CSVProducer(String file) {
		//Create a producer object
		Producer<String, JsonObject> producer = ProducerCreator.createProducer();
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		int iteration = 0;
		JsonObject mainObject = new JsonObject();
		
		try {
			br = new BufferedReader(new FileReader(file));
			String[] headers = null;
			while((line = br.readLine()) != null) {
				
				//skip first row due to them being identifying headers.
				if(iteration == 0) {
					headers = line.split(csvSplitBy);
					iteration++;
					continue;
				}
				
				String[] csvArray = line.split(csvSplitBy);
				
				JsonObject secondaryObject = new JsonObject();
				
				//creates the secondary objects using the headers as the keys
				for(int i = 0; i < csvArray.length; i++) {
					secondaryObject.addProperty(headers[i], csvArray[i]);
				}
				
				//adds the secondary object to the main object using the first item in the csvArray array
				mainObject.add(csvArray[0], secondaryObject);
				
				//increases the iteration
				iteration++;
			}
		} catch(FileNotFoundException e) {
			System.out.println("File Not Found Error");
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println("I/O error");
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch(IOException e){
					System.out.println("I/O Close error");
					e.printStackTrace();
				}
			}
		}
		
		//creates s ProducerRecord object to use to get information about the record that is going to be produced to Kafka topic.
		final ProducerRecord<String, JsonObject> record = new ProducerRecord<String, JsonObject>(KafkaConstants.TOPIC_ID, mainObject);
		
		try {
			//sets the metadata equal to what is received when the record is produced to the topic.
			RecordMetadata metadata = producer.send(record).get();
			System.out.println();
			System.out.println();
			System.out.println("Record sent to partition " + metadata.partition() + " with offset " + metadata.offset());
			System.out.println();
			System.out.println();
		} catch (ExecutionException e) {
			System.out.println("Error in sending record (Execution)");
			e.printStackTrace();
		} catch(InterruptedException e) {
			System.out.println("Error in sending record (Interrupted");
			e.printStackTrace();
		}
	}
}
