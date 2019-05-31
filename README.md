# KafkaSslCsv
Produces a CSV file to Kafka topic utilizing SSL

TO USE
Inside the KafkaConstants class insert your custom information. 
Note that all information listed is needed in order for this program to run successfully.

Note that this program formats the information as so: 

The first item in the row will be the object key and the rest of the items in the row will be values inside that object then this whole object will be pushed inside a larger object. 

Example: 

Name,Favorite Color, Favorite Animal, Favorite Food

John Doe,Blue,Dog,Pizza,.......

Jane Doe,Green,Sheep,Tacos,.......


Will be processed as:

{

  "John Doe": {
  
    "Favorite Color": "Blue",
    
    "Favorite Animal": "Dog",
    
    "Favorite Food": "Pizza",
    
    .......
  },
  
  "Jane Doe": {
  
    "Favorite Color": "Green",
    
    "Favorite Animal": "Sheep",
    
    "Favorite Food": "Tacos",
    
    .......
    
  },
  
  .......
  
}


If you wish to reformat it, it will have to be done in the CSVProducer class
