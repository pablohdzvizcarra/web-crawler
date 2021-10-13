# Web Crawler

## GitHub Actions

**CI Pipeline** GitHub Action name

This project has Continuos Delivery (CI) through a GitHub Action, this action simply 
runs the test and makes sure that the test always pass.

The GitHub Action **CI Pipeline** will be triggered every time a pull request is made 
to the main branch of the project.

## 2. Docker

You can create a docker container in a very simple way, the project has a Dockerfile 
already configured for its implementation.

#### -- Warning

### 2.1 Create jar file 

Before attempting to build the docker container with the Dockerfile you must generate 
a **jar** file using Gradle with the following command:

```shell
./gradlew build
```
### 2.2 Create Image Docker

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

### 2.3 Run Docker Container

When your docker image is built, you can now create a container of your base image 
with the following command:

```shell
docker run -p [port]:8080 [name-image]
```

**port:** you port you want the application to run on.
**name-image:** you must replace it with the name you want your image to have.