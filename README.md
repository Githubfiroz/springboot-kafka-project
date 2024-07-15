# springboot-kafka-project


The following two microservices need to be started as they are independent: SpringBootProducerApplication and SpringBootConsumerApplication.

If Kafka, Zookeeper, and MySQL are running on an Ubuntu instance at AWS EC2, the application.properties files of both the producer and consumer applications need to be updated with the IP details for Kafka and MySQL.

MySQL Workbench also needs to be updated with the IP address if these containers are running on an Ubuntu instance at AWS EC2.

Start Zookeeper first, then Kafka, and finally MySQL.
