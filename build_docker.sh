./gradlew bootJar
docker build -f Dockerfile -t dd_security_server .
docker run --net=host --restart always --name=ddserver -p 9445:9445 -di ddserver:1.0


./gradlew wrapper --gradle-version=8.1 --distribution-type=bin