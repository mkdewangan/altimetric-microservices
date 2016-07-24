# altimetric-microservices

A sample to demo microservices core components i.e centralized configuration server, registry server, service and service client. It has following components :

1. sample-config : Spring-boot based centralized configuration server for microservices. All other microservices retrive their configuration properties from here.

2. sample-registration : A centralized registration server, developed using Spring-Netflix-Eureka. Every microservice when it starts it gets register with this registeration server. It provides web-gui where we can see registered services and how many instances are running for each microservice.

3. sample-todo-service : A service which provides following operations

a. POST /to-do-app/tasks : To create a to-do task.
b. GET /to-do-app/tasks : To list out all to-do tasks created so far.
c. GET /to-do-app/config : It retrieve one configuration property reply.message from configuration server i.e sample-config. 

4. sample-todo-client : A client service which discover sample-todo-service from registery(i.e sample-registeration) and access its operations. It uses Spring-Eureka-DiscoveryClient for this. It has same set of operations as sample-todo-service. End user will access this service from REST Client.

a. POST /to-do-app/tasks : It calls sample-todo-service POST /to-do-app/tasks.
b. GET /to-do-app/tasks : It calls sample-todo-service GET /to-do-app/tasks.
c. GET /to-do-app/config : It calls sample-to-service GET /to-do-app/config


# 



