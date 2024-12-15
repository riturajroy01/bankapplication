# Getting Started
ad## Project Bank Application
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








### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.0/maven-plugin)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.0/reference/web/servlet.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.0/reference/data/sql.html#data.sql.jpa-and-spring-data)

