RESTful Services with SpringMVC (tested with Mockito)
=====================================================

Sample project that covers the following concepts:

* Spring-MVC
* REST responses on Spring Controllers
* Testing for REST endpoints using Mockito

REST Endpoints
==============

We try to follow well-accepted REST principles with these examples. All operations performed on this user entity are through the same
endpoint and use the request method as a determiner for the service to call...

* Retrieve user with ID=3: <code>GET http://localhost:8080/user/3</code>
* Save a new User: <code>POST http://localhost:8080/user</code>
* Update User: <code>PUT http://localhost:8080/user</code>
* Delete use with ID=3: <code>DELETE http://localhost:8080/user/3</code>

If an endpoint is triggered that does not have a mapping, the <code>DefaultController</code> will respond with an error.

Usage
=====

* Clone the repository
* To run the tests: <code>mvn clean test</code>
* If deploying to a servlet container: <code>http://localhost:8080/user</code> will return an initial response.

See <code>org.royrusso.mvc.controller.UserController</code> for more REST endpoints.