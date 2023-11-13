# User Registration Service

---

## Quickstart

### Maven
```html
1. mvn clean install
```

### Docker

`1. docker --version`

`2. docker build -t thestackschool/user-registration-service:1.0 .`

`3. docker run -p 8080:8080 thestackschool/user-registration-service:1.0`

> **_NOTE:_**  This fails because the application is connected to local datasource which is running in a different network. This steps will create an image for the application. The next step is to use this image with a database service by overriding datasource properties.

`4. docker login`

`5. docker push thestackschool/user-registration-service:1.0`

> **_NOTE:_** Now the image is available on dockerHub. Even if we delete the image from the host, we can pull from the hub and use it on any machine.


---
---
Dockerfile
```html
FROM openjdk:17-alpine
ARG JAR_FILE=target/user-registration-service.jar
ADD ${JAR_FILE} user-registration-application.jar
ENTRYPOINT ["java","-jar","/user-registration-application.jar"]
```

> **_NOTE:_** Override Dockerfile properties while building a docker image.
`Example: docker build -t thestackschool/user-registration-service:1.0 `**--build-arg**` JAR_FILE=target/some-name.jar .`

---
---
application.yaml
```yaml
server:
  port: 9999
spring:
  datasource:
    password: ${MYSQL_PASSWORD:root}
    url: jdbc:mysql://${HOST:localhost}:${PORT:3306}/user_registration01
    username: ${MYSQL_USERNAME:root}
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```
> **_NOTE:_** Override application.properties configuration while running the docker container.
> `Example:  docker run -p 9666:9666 `**-e**` PORT=9666 thestackschool/user-registration-service:1.0`

### Docker Compose
Since our application depends on database service. we will need to go for multi-container service called docker compose in order to run the application.
```yaml
---
version: '3.7'
services:
  user-registration:
    image: thestackschool/user-registration-service:1.0
    container_name: user-registration-service
    restart: always
    # can use `docker-compose up --build ` to build the image named as line 5 and run the image
    # if we use `docker-compose up`, then it will use the local image or pull from registry if not exists
    build: .
    ports:
      - "9999:9999"
    environment:
      MYSQL_HOST: mysqldb
      MYSQL_USERNAME: root
      MYSQL_PASSWORD: secret
      MYSQL_DATABASE: user_registration01
      MYSQL_PORT: 3306
      SERVER_PORT: 9999
    depends_on:
      - mysqldb

  mysqldb:
    image: mysql:latest
    container_name: mysql-db
    restart: always
    environment:
      #This variable is optional and allows you to specify the name of a database to be created on image startup
      MYSQL_DATABASE: user_registration01
      #This variable is mandatory and specifies the password that will be set for the MySQL root superuser account.
      MYSQL_ROOT_PASSWORD: secret
    ports:
      - "3307:3306"
    expose:
      - "3306"
    volumes:
      - db-data:/var/lib/mysql
volumes:
  db-data:
```

> `docker-compose up --build`  
<small>This will rebuild the image and run</small>


> **NOTE** Then we can connect through DB client like Workbench using the below details:
```html
hostname = 127.0.0.1
port = 3307
user = root
password = secret
```
