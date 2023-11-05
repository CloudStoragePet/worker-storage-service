# worker-storage-service
worker-storage-service is a Spring Boot-based microservice for managing high load tasks for storage-service tasks.

## Server port: 8082

## Requirements

- Java version 17.0.7
- Spring Boot 3.1.3
- Maven 3.9.1
- RabbitMQ 3.9.7
- Redis
- OLTP


## Technologies Used

1. Java 17
2. Spring Boot
3. AMQP
4. Redis
5. OLTP

## Application Features

Worker storage service manages user's storage by providing the following features:
- Base folder moving.

## Running the Application with Docker

To run the Storage service with Docker, follow these steps:

1. Ensure that Docker and Docker Compose are installed on your system.
2. Clone the repository and navigate to the project directory.
3. Run in `cmd`
   ```sh
      mvn clean install
   ```
4. Build the Docker image by running the following command. Make sure to replace the placeholders `<your_*>` with your
   actual values:

   ```sh
   docker build \
       --build-arg DB_HOST=<your_db_host> \
       --build-arg DB_USER=<your_db_user> \
       --build-arg DB_PASSWORD=<your_db_password> \
       --build-arg SPRING_DATA_REDIS_HOST=<your_redis_host> \
       --build-arg SPRING_DATA_REDIS_PORT=<your_redis_port> \
       --build-arg SPRING_DATA_REDIS_PASSWORD=<your_redis_password> \
       --build-arg EUREKA_URL=<your_eureka_url> \
       --build-arg SPRING_RABBITMQ_HOST=<your_rabbitmq_host> \
       --build-arg SPRING_RABBITMQ_PORT=<your_rabbitmq_port> \
       --build-arg STORAGE_BASE_PATH=<your_base_folder_path> \
       --build-arg RABBITMQ_MOVE_QUEUE_NAME=<your_rabbitmq_move_queue_name> \
       --build-arg RABBITMQ_EXCHANGE_NAME=<your_rabbitmq_exchange_name> \
       --build-arg RABBITMQ_ROUTING_KEY=<your_rabbitmq_routing_key> \
       --build-arg MANAGEMENT_OTLP_TRACING_ENDPOINT=<your_oltp_tracing_endpoint> \
       --build-arg MANAGEMENT_OTLP_METRICS_EXPORT_URL=<your_oltp_metrics_endpoint> \
       -t worker-storage-service .
    ```
http://localhost:8081/swagger-ui/index.html