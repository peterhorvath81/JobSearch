# Job Search Application

## Table of Contents

* [Requirements](#requirements)
* [Configuration](#configuration)
* [Getting started and use cases](#getting-started-and-use-cases)
* [Possible further improvements on the application](#possible-further-improvements-on-the-application)

### Requirements

The Job Search application is a Spring Boot application written in Java 11.
For development Maven was used as a build tool.

To run the application the followings are required:

    - Java11 (or above)
    - Apache Maven 3.8.4.
    - Git client for cloning the application from its repository (optional)

The application currently uses an in-memory H2 database so there is no need to install any database server locally.


### Configuration

The github repository for the project is available at: https://github.com/peterhorvath81/JobSearch.git

You can clone the application from the above repository: *git clone https://github.com/peterhorvath81/JobSearch.git* \
or you can download as a ZIP file. In this case you must extract it to a specific folder.

Once you downloaded the application go to the containing directory.\
Build the project using the command:

*./mvn clean install*

This command will generate a Target folder.\
Inside this folder you will find the generated jar file: 

*(jobsearch-0.0.1-SNAPSHOT.jar)*.

You can start the application with the command:

*java -jar jobsearch-0.0.1-SNAPSHOT.jar*

Or if you have any IDE installed you can start the application by running its main method.


### Getting started and use cases

Once the application is started it will run on **localhost:8080**

Its in-memory database is available at: **localhost:8080/h2-console**\
To access the database please make sure that its JDBC URL is: **jdbc:h2:mem:testdb**\
At the database you can find two tables: *Client* and *Job* with some initial data.\
Test data injection to DB is done via the data.sql file located under the Resources folder.

The application also uses Swagger API documentation.

The full API documentation is available at: http://localhost:8080/v2/api-docs \
The Swagger UI is available at: http://localhost:8080/swagger-ui.html \
(for some reason the swagger UI is available via Firefox)

##### Endpoints

All the rest endpoint can be tested through Swagger UI or you can alternatively use other REST Clients as well (Postman, insomnia for example).

###### Client endpoint (/client)

Client endpoint is a POST request that can register clients to the database.

*curl --location 'localhost:8080/client' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "james",
"email": "james@mail.com"
}'*

The request body contains the name and e-mail address of the client.\
The response will contain an API Key in UUID format, for example:

_"9c2229f4-1370-4422-9c8b-179d13e6a2c7"_

###### Position endpoint (/position)

Once the client is registered he/she can post positions.\
The position endpoint is a POST request where a registered client can post positions.\

_curl --location 'localhost:8080/position' \
--header 'Content-Type: application/json' \
--data '{
"name": "developer",
"location": "budapest",
"author": "james"
}'_

The request body contains the author, who is the registered client, the location of the position and the author, who posted the job.\
As a response, you get the URL of the posted job where it is available, for example:

_localhost:8080/position/2f86a4a6-f305-467e-9a00-b288477f1e1f_

###### Search endpoint (/position/search)

The search endpoint is a GET endpoint where you can search jobs.

*curl --location --request GET 'localhost:8080/position/search' \
--header 'Content-Type: application/json' \
--data '{
"keyWord": "developer" ,
"location": "budapest",
"requester": "james"
}'*

The request body contains the keyword, which is the name of the position, the location of the position and the requester name, who searched the job.\
As a response, you will get the List of the URLs for the position, for example:

_[
"localhost:8080/position/2f86a4a6-f305-467e-9a00-b288477f1e1f"
]_

You can copy the URL to your browser to see the details of the job.

###### Find position by id endpoint (/position/id)

This GET endpoint returns with the details of the job by the given ID:

_curl -X GET --header 'Accept: application/json' 'http://localhost:8080/position/2f86a4a6-f305-467e-9a00-b288477f1e1f'_ 

For request parameter it takes the ID of the position.
As a response you can get the details of the job:

_{
"id": "2f86a4a6-f305-467e-9a00-b288477f1e1f",
"name": "developer",
"place": "budapest"
}_


### Possible further improvements on the application

##### Containerization of the application

The application currently runs as a native spring boot application.\ 
Containerization of the application using docker allows us to develop it on a virtual environment.
As a docker image the application is more easily deployable to any environment.

#### Changing the embedded H2 database

Currently the application uses an embedded H2 database. Its drawback is that during every restart all the stored data
is lost.\
A more convenient solution is to change the H2 database to another one (for example MySQL, PostgreSQL).\
As a native application to achieve this is to download a database server and configure it accordingly.\
A more straightforward solution is to dockerize the application. In this case a database can be configured for the application through a docker-compose.yml file.\
Once it is configured the application and its corresponding database can run on a virtual environment.

#### Implementing paging and sorting of search result

Currently the application doesn't support paging and sorting.  
For big search results paging and sorting is a convenient way to look through the results.