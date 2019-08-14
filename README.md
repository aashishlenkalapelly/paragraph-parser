## Starting The Application
mvn spring-boot:run

## Stopping The Application
curl -X POST localhost:8080/actuator/shutdown


## Code Coverage

Code coverage can be found in /target/site/jacoco/index.html.

 
## User Instructions
The REST endpoints provided below can be called either by executing the provided cURL commands or through an HTTP client by using the provided URLs.


## Word Count Service REST Endpoint
Rest endpoint which accepts a JSON object containing a paragraph and returns a list of JSON objects containing the words and number of occurrences from the paragraph.

**cURL command**:
curl -H "Content-Type: application/json" -X GET -d "{\"para\":\"*<input the paragraph you want to pass in>*\"}"  http://localhost:8080/wordCount

**HTTP client**:

**URL**: http://localhost:8080/wordCount

**Method**: GET

**Request Body**:

{"para" : "*<input the paragraph you want to pass in>*"}

**Response**:
List of JSON objects containing the words and the number of occurrences for each word from the input para.

## Person Service REST Endpoints
#### Display all persons in the database:

**cURL command**:
curl -X GET http://localhost:8080/persons

**HTTP client**:

**URL**: http://localhost:8080/persons

**Method**: GET

**Response**:
List of JSON objects representing person rows in the database.

#### Display person in the database using personId:

**cURL command**: curl -X GET http://localhost:8080/person/*<input personId>*

**HTTP client**:

**URL**: http://localhost:8080/person/*<input personId>*

**Method**: GET

**Response**:
JSON object containing the person details of the provided personId.

#### Add a person to the database:

**cURL command**:
curl -H "Content-Type: application/json" -X POST -d "{\"personId\":*<input personId>*, \"firstName\":\"*<input firstName>*\", \"lastName\":\"*<input LastName>*\" }"  http://localhost:8080/person

**HTTP client**:

**URL**: http://localhost:8080/person/

**Method**: POST

**Request Body**: {"personId": *<input person-id>*, "firstName": "*<input firstName>*", "lastName": "*<input LastName>*"}

**Response**:
JSON object containing the details of the person entity which got added to the database.