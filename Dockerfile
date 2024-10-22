# Create the Runtime Image
FROM amazoncorretto:22 AS runtime
EXPOSE 8099:8080
RUN mkdir /app
COPY /build/libs/*.jar /app/ktor-docker-sample.jar
ENTRYPOINT ["java","-jar","/app/ktor-docker-sample.jar"]

# docker build -t ktormqtt .
# docker run -p 8099:8080 ktormqtt -d