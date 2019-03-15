# ITexicoCoding
Users API Based on Spring Boot Rest Controller

Instructions
Create a database where Hibernate will create tables to work on. 
CREATE DATABASE  IF NOT EXISTS `users_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `users_db`;
After the first running it will show the recently created tables.
SHOW tables; 

Maven should be already installed in the computer where the service will be deployed.
By the first time the command: "mvn clean install" should be executed in order to download the required dependencies.

The application will run tests accordingly.
Such tests can be find at src/java/tests in the package com.example.users.UsersRestAPI. and can be executed as JUnit tests.


In order to get the application up and running we may have 2 possible options:
1.- running in cmd the command: mvn spring-boot:run
2.- at the file named "UsersRestAPIApplication.java" right click and run as Java Application.

Once up and running the app can be reached by postman tool on its different available services.

Creating a user
URL: http://localhost:8080/api/v1/users/create_user
HTTPMethod: POST 
RequestBody:
{
  	"firstName":"Name",
	"lastName": "LastName",
	"dateOfBirth": "1985-07-26"
}

Getting all users (only active users)
URL: http://localhost:8080/api/v1/users/get_all_users
HTTPMethod: GET

Getting user by Id
URL: http://localhost:8080/api/v1/users/get_user/{id}
HTTPMethod: GET

Update user
URL: http://localhost:8080/api/v1/users/update_user/{id}
HTTPMethod: PUT
RequestBody:
{
  	"firstName":"NameChanged",
	"lastName": "LastNameChanged",
	"dateOfBirth": "1986-08-27"
}

Deleting user (setting as INACTIVE)
URL:http://localhost:8080/api/v1/users/delete_user/{id}
HTTPMethod: DELETE

The app can be stopped either command line by ctrl+c or click on "terminate" at the console in eclipse.

Thanks for visiting my repo!
