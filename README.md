# Wikimedia Kafka Project

## Overview
This project demonstrates a **Kafka-based microservices setup** using **Spring Boot**. It consists of:

1. **Wikimedia Producer** – connects to the Wikimedia recent changes stream and publishes events to a Kafka topic.  
2. **Wikimedia Consumer** – consumes events from the Kafka topic and stores them in a MySQL database.  

Both services are containerized with Docker and can connect to **host Kafka and MySQL** services.

---

## Prerequisites
- **Docker** installed on your machine  
- **Apache Kafka** running on `localhost:9092`  
- **MySQL** running on `localhost:3306` (via XAMPP)  
- **Java 17** (optional if building locally)  
- **Maven** (optional if building locally)

---

## Project Structure

wikimedia-kafka-project/
├── wikimedia-producer/
│ ├── src/
│ │ └── main/
│ │ ├── java/
│ │ └── resources/
│ │ └── application.properties
│ ├── pom.xml
│ └── Dockerfile
├── wikimedia-consumer/
│ ├── src/
│ │ └── main/
│ │ ├── java/
│ │ └── resources/
│ │ └── application.properties
│ ├── pom.xml
│ └── Dockerfile
└── README.md


---

## Configuration

### Producer (`application.properties`)
```properties
spring.application.name=wikimedia-producer
spring.kafka.producer.bootstrap-servers=host.docker.internal:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer

### Consumer (`application.properties`)
spring.application.name=wikimedia-consumer
spring.kafka.consumer.bootstrap-servers=host.docker.internal:9092
spring.kafka.consumer.group-id=wikimediaConsumers
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer

spring.datasource.url=jdbc:mysql://host.docker.internal:3306/wikimedia
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update

