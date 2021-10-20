# Web Crawler

## 1. Run project

This project uses gradle to run as it, is a spring application you can run the project
with the following command:

```shell
./gradlew bootRun
```

## 2. API

The project has a separate API from the front-end, to which you can make request from 
any device. The following endpoints are available for the API.

```shell
GET http://localhost:8080/api/crawler?url=[any URL]
```

```shell
GET http://localhost:8080/api/crawler/selected?url=[any URL]
```

## 3. GitHub Actions

**CI Pipeline** GitHub Action name

This project has Continuos Delivery (CI) through a GitHub Action, this action simply 
runs the test and makes sure that the test always pass.

The GitHub Action **CI Pipeline** will be triggered every time a pull request is made 
to the main branch of the project.

## 4. Docker

You can create a docker container in a very simple way, the project has a Dockerfile 
already configured for its implementation.

#### -- Warning

### 4.1 Create jar file 

Before attempting to build the docker container with the Dockerfile you must generate 
a **jar** file using Gradle with the following command:

```shell
./gradlew build
```
### 4.2 Create Image Docker

Once the jar file is created, you must execute the following command to create the 
docker image

```shell
docker build --build-arg JAR_FILE=build/libs/\*.jar -t [name-image] .
```

**name-image:** you must replace it with the name you want your image to have

**Example:**

```shell
docker build --build-arg JAR_FILE=build/libs/\*.jar -t annonymous/web-crawler .
```

### 4.3 Run Docker Container

When your docker image is built, you can now create a container of your base image 
with the following command:

```shell
docker run -p [port]:8080 [name-image]
```

**port:** you port you want the application to run on.
**name-image:** you must replace it with the name you want your image to have.

### 4.4 Docker Hub

The image is stored in dockerhub, you can download it and run it with the following 
image name.

```shell
docker pull pablohdz/webcrawler
```

<footer style="display: flex; align-items: center; justify-content: center; 
margin: 10px">
    Made with <img style="width: 30px" src="https://img.icons8.
com/fluency/48/000000/hearts.png" 
alt="Heart"/> by 
Pablo Hernandez
</footer>