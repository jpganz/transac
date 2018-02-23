# Transac (Transactions API)

This API was created to collect the provided information and store it. Shares the data collected that belongs to the last minute.
 
 You can check it through swagger running it at localhost:8080 (8080 by default)
 
 This project was created with spring, hazelcast and a in memory database H2.
 
 Data is stored at h2 and can be checked at localhost:8080/h2 , credentials can be found at the application.properties file
 
 It currently holds the endpoint /transaction for methods GET and POST
 
 More description about the endpoints can be found on the swagger page at localhost:8080
 
 IMPORTANT to notice, the POST endpoint will receive a parameter with a valid double valie and a epoch expressed in milliseconds like: 1519378957000

## Getting Started

You need to have java 1.8 to run this project
To compile the project you need maven 3.2+ 
All repositories used here are public and should not have any restriction.


### Prerequisites

Java 1.8
Maven 3.2+

```
You can compile it using mvn clean install or mvn clean install -DskipTests (to avoid running the tests)
After that you can execute: java -jar $PROJECT_PATH/target/demo-VERSION.jar
```


## Running the tests

The tests will run when compiling the project.
You can isolate run the tests as well if you prefer.
Integration tests and unit tests are present

### Break down into end to end tests

It currently have 2 unit tests and one integration test

```
TransacController, TransacService are unit test, both test and assert the results of both controller and service
```

## Deployment

You can run the jar directly or deploy it on an application server.

## Parameters

If you prefer you can override the following parameters:

server.port  -- value default:8080 -- It is the port where the API will be running and where Calcs API will come to ask for information https://github.com/jpganz/calcs/tree/develop


## Authors

Juan Hernandez

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details


 
