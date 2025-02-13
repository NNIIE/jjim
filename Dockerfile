FROM gradle:8.0-jdk17 AS build
WORKDIR /home/gradle/project

COPY --chown=gradle:gradle . .
RUN gradle bootJar --no-daemon

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/*.jar app.jar

RUN apt-get update && apt-get install -y wget \
  && wget https://github.com/jwilder/dockerize/releases/download/v0.6.1/dockerize-linux-amd64-v0.6.1.tar.gz \
  && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-v0.6.1.tar.gz \
  && rm dockerize-linux-amd64-v0.6.1.tar.gz \
  && apt-get clean && rm -rf /var/lib/apt/lists/*

EXPOSE 8080

ENTRYPOINT ["dockerize", "-wait", "tcp://mysql:3306", "-timeout", "10s", "java", "-jar", "app.jar"]