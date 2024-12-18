# Getting Started
## Project Bank Application
This is a demo project where following use cases are considered

* The assessment consists of an API to be used for opening a new “current account” of already existing
customers.
  * Requirements
    * The API will expose an endpoint which accepts the user information (customerID,
initialCredit).
    * Once the endpoint is called, a new account will be opened connected to the user whose ID is
customerID.
    * Also, if initialCredit is not 0, a transaction will be sent to the new account.
Another Endpoint will output the user information showing Name, Surname, balance, and
transactions of the accounts.

## Prerequisites
* JDK 21 and above
* Apache Maven 3.6.3 and above
* Lombok plugin support
* Any browser to render HTML
* Any IDE like IntelliJ IDEA
* Docker Desktop to run/pull docker image (Optional)

## Technology Stack
* JDK 21
* Spring Boot 3 and Spring Framework 6
* H2 in-memory Database integrated
* Spring Web
* Spring Data JPA
* Junit 5
* Spring Framework Thymeleaf - server-side Java template engine for UI templates

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.0/maven-plugin)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.0/reference/web/servlet.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.0/reference/data/sql.html#data.sql.jpa-and-spring-data)


### How to run the project : Use one of the options mentioned below
* Option 1: Install **Prerequisites** and open the repository branch in a IDE and run application.
  * Steps:    
    * Clone the repository into your projects directory - Clone using the web URL:
      
  ```
  git clone https://github.com/riturajroy01/bankapplication.git
  ```
   ```
  git remote add origin https://github.com/riturajroy01/bankapplication.git
  ```
    ```
  git checkout -b master
  ```
     ```
  git branch --show-current
  ```


* Option 2: Install Docker Desktop and pull the image from docker hub
    * Go to project root directory and run following command

    ```
    docker compose up -d
    ```

* Option 3: Install Docker Desktop, pull and run the image from docker hub
     * Step 1
    
    ```
    docker pull riturajdocker/bank-application-service
    ```
   * Step 2
  ```
    docker run -p 8080:8080 riturajdocker/bank-application-service
    ```

### End points to Test
* Account will be created with Customer ID as path variable
* Application start up creating two default customers with Customer Id 1 and 2

Create Account with Customer ID 1
```
    http://localhost:8080/customer/create-account/1
   
```
Create Account with Customer ID 2
```
    http://localhost:8080/customer/create-account/2
    
```
Display customer profile with Account number and Transactions
```
    http://localhost:8080/customer/account/profile/1
```    
    
 ```
    http://localhost:8080/customer/account/profile/2
 ```

### GitHub Action - Build Test and Push to DockerHub
* https://github.com/riturajroy01/bankapplication/actions
* https://github.com/riturajroy01/bankapplication/blob/master/.github/workflows/main.yml

### GitHub Action - Sonar Cloud
* https://github.com/riturajroy01/bankapplication/actions
* https://github.com/riturajroy01/bankapplication/blob/master/.github/workflows/sonar-check.yml
