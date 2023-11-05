FROM openjdk:17-jdk-slim
LABEL authors="Vitalii Seniuk"

ARG JAR_FILE=target/*.jar
ARG DB_HOST
ARG DB_USER
ARG DB_PASSWORD
ARG SPRING_DATA_REDIS_HOST
ARG SPRING_DATA_REDIS_PORT
ARG SPRING_DATA_REDIS_PASSWORD
ARG EUREKA_URL
ARG SPRING_RABBITMQ_HOST
ARG SPRING_RABBITMQ_PORT
ARG STORAGE_BASE_PATH
ARG RABBITMQ_MOVE_QUEUE_NAME
ARG RABBITMQ_EXCHANGE_NAME
ARG RABBITMQ_ROUTING_KEY
ARG MANAGEMENT_ZIPKIN_TRACING_ENDPOINT

# Set environment variables from build-time arguments
ENV DB_HOST=$DB_HOST \
    DB_USER=$DB_USER \
    DB_PASSWORD=$DB_PASSWORD \
    SPRING_DATA_REDIS_HOST=$SPRING_DATA_REDIS_HOST \
    SPRING_DATA_REDIS_PORT=$SPRING_DATA_REDIS_PORT \
    SPRING_DATA_REDIS_PASSWORD=$SPRING_DATA_REDIS_PASSWORD \
    EUREKA_URL=$EUREKA_URL \
    SPRING_RABBITMQ_HOST=$SPRING_RABBITMQ_HOST \
    SPRING_RABBITMQ_PORT=$SPRING_RABBITMQ_PORT \
    STORAGE_BASE_PATH=$STORAGE_BASE_PATH \
    RABBITMQ_MOVE_QUEUE_NAME=$RABBITMQ_MOVE_QUEUE_NAME \
    RABBITMQ_EXCHANGE_NAME=$RABBITMQ_EXCHANGE_NAME \
    RABBITMQ_ROUTING_KEY=$RABBITMQ_ROUTING_KEY \
    MANAGEMENT_ZIPKIN_TRACING_ENDPOINT=$MANAGEMENT_ZIPKIN_TRACING_ENDPOINT

COPY ${JAR_FILE} worker-storage-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/worker-storage-service.jar"]