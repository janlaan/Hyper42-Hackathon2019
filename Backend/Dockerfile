FROM openjdk:11-jre
ARG APP_FILE=specified_in_pom.jar
ENV APPLICATION $APP_FILE
ENV APP_HOME /app
ENV SPRING_CONFIG_LOCATION classpath:/config/,file:./config/
ENV SPRING_CONFIG_NAME application
VOLUME $APP_HOME/config
EXPOSE 7090
COPY target/$APP_FILE $APP_HOME/
WORKDIR /app
CMD /usr/bin/java -Djavax.net.ssl.trustStore=/crypto-config/hyper42.jks -Djavax.net.ssl.trustStorePassword=changeit -jar $APPLICATION