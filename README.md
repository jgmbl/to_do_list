# TO-DO LIST
## About the project
To-do list is an app dedicated to plan tasks. User can add, change and delete tasks. Each task is assigned to user. The application is developed in accordance with the REST API. The user receives status codes and data in JSON format. Frontend is created in React.

## Functionalities
Functionalities of the application include:
 - adding task
 - changing content of a task
 - displaying all tasks
 - displaying task by id
 - deleting task
 - adding name
 - displaying all names
 - displaying name by id
 - deleting name

## Technologies
Technologies used:
- Spring Boot
- JUnit
- Mockito
- React

## Database
![Database schema](https://github.com/jgmbl/to_do_list/blob/main/images/to-do-list_database.jpg) 

## Tests
The code is tested manually using Postman and by unit tests.
![Coverage](https://github.com/jgmbl/to_do_list/blob/TDL-26-main-page/images/unit_tests_coverage.png)

## How to build and run
Open folder with *pom.xml* in terminal:
```
cd path-to-file/to-do-list/to_do_list_backend/
```
 Type:
```
mvn clean install
mvn spring-boot:run
```
Open the following folder in another terminal:
```
cd path-to-file/to-do-list/to_do_list_frontend/
```
Type:
```
npm start
```
