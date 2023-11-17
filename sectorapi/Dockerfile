FROM openjdk:17
WORKDIR /app

CMD ["./gradlew", "clean", "bootJar"]

COPY . .

ENTRYPOINT ["java","-jar","build/libs/sectorapi-0.0.1-SNAPSHOT.jar"]