# How I Approached This Challenge:
To start, I really enjoyed this challenge and am excited about this opportunity.

In order to complete Task #1, I knew recursion would be needed. 
I created the method `calculateDirectReports()` in order to traverse the binary tree.
I also noticed the list of `directReports` expects `Employee` objects, but the database only contains the `employeeId`.
While already iterating over the binary tree, the `calculateDirectReports()` method also creates an `Employee` object for each direct report, and stores them in a list.
The employee's `directReports` list is updated to contain all the information on each direct report.
I decided to utilize the existing `EmployeeService` to contain the new direct report methods.

For Task #2, I created a `Compensation` bean, created the 2 new REST endpoints, and created a new Mongo repository to store all the Compensations.
The `employeeId` field was used as the primary key, and a compensation JSON is returned.

I have also changed the way some errors were thrown. 
Rather than throw a runtime error on an `employeeId` not found, I have updated the response to indicate a Bad Request.
The error message still informs the user about why it was a bad request, and it is much better than throwing a runtime error.

I also used Postman for my development testing. I have uploaded the JSON of my postman project as well.
The tests can be imported through the `Postman API Tests` file.


# Below was the Coding Challenge Provided:
A simple [Spring Boot](https://projects.spring.io/spring-boot/) web application has been created and bootstrapped 
with data. The application contains information about all employees at a company. On application start-up, an in-memory 
Mongo database is bootstrapped with a serialized snapshot of the database. While the application runs, the data may be
accessed and mutated in the database without impacting the snapshot.

### How to Run
The application may be executed by running `gradlew bootRun`.

### How to Use
The following endpoints are available to use:
```
* CREATE
    * HTTP Method: POST 
    * URL: localhost:8080/employee
    * PAYLOAD: Employee
    * RESPONSE: Employee
* READ
    * HTTP Method: GET 
    * URL: localhost:8080/employee/{id}
    * RESPONSE: Employee
* UPDATE
    * HTTP Method: PUT 
    * URL: localhost:8080/employee/{id}
    * PAYLOAD: Employee
    * RESPONSE: Employee
```
The Employee has a JSON schema of:
```json
{
  "type":"Employee",
  "properties": {
    "employeeId": {
      "type": "string"
    },
    "firstName": {
      "type": "string"
    },
    "lastName": {
          "type": "string"
    },
    "position": {
          "type": "string"
    },
    "department": {
          "type": "string"
    },
    "directReports": {
      "type": "array",
      "items" : "string"
    }
  }
}
```
For all endpoints that require an "id" in the URL, this is the "employeeId" field.

## What to Implement
Clone or download the repository, do not fork it.

### Task 1
Create a new type, ReportingStructure, that has two properties: employee and numberOfReports.

For the field "numberOfReports", this should equal the total number of reports under a given employee. The number of 
reports is determined to be the number of directReports for an employee and all of their distinct reports. For example, 
given the following employee structure:
```
                    John Lennon
                /               \
         Paul McCartney         Ringo Starr
                               /        \
                          Pete Best     George Harrison
```
The numberOfReports for employee John Lennon (employeeId: 16a596ae-edd3-4847-99fe-c4518e82c86f) would be equal to 4. 

This new type should have a new REST endpoint created for it. This new endpoint should accept an employeeId and return 
the fully filled out ReportingStructure for the specified employeeId. The values should be computed on the fly and will 
not be persisted.

### Task 2
Create a new type, Compensation. A Compensation has the following fields: employee, salary, and effectiveDate. Create 
two new Compensation REST endpoints. One to create and one to read by employeeId. These should persist and query the 
Compensation from the persistence layer.
