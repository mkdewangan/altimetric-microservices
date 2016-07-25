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


# Running without docker in amazon cloud

**Pre-requisite :** Amazon EC2 instance with 2GB RAM is setup and running. Instance type t2-small / t2-medium should be good enough. Java 8, Maven 3 is installed on it.

 **1. clone the repository from github.**

Example : 
ubuntu@ip-172-31-56-18:~/altimetric/repo$ git clone https://github.com/mkdewangan/altimetric-microservices.git

It will create altimetric-microservices folder under repo. i.e /home/ubuntu/altimetric/repo/altimetric-microservices

**2. Start Registration Server** 

In command shell go to altimetric-microservices/sample-registration folder and run :  nohup mvn spring-boot:run & . It will start eureka registration server at port 8761. You can go to browser and check at http://host:8761/. You should see Spring-Eureka page.

**3. Start Centralized Configuration Server**

In command shell go to altimetric-microservices/sample-config folder and run :  " nohup mvn spring-boot:run &". It will start configuration server and register it with eureka. Now if you refresh the spring-eureka page at http://host:8761/, you should see one instance of application SAMPLE-CONFIG in registered application list.

**4. Start To-Do Service App**

In command shell go to   altimetric-microservices/sample-todo-service folder and run : " nohup mvn spring-boot:run &". It will start configuration server and register it with eureka. Now if you refresh the spring-eureka page at http://host:8761/, you should see one instance of application TO-DO-APP in registered application list.

**5. Start To-DO Client App**

In command shell go to   altimetric-microservices/sample-todo-client folder and run : " nohup mvn spring-boot:run &". It will start configuration server and register it with eureka. Now if you refresh the spring-eureka page at http://host:8761/, you should see one instance of application TO-DO-APP-CLIENT in registered application list.

**6. Testing with Rest Client**
When all the application is started and registration is completed, now turn is to test it. 
Open any Rest Client and test following operations
**(a)**  
	POST  http://host:8084/to-do-app-client/tasks
	Header :
	Content-Type : application/json
	Body :
	{
	  "taskId" : "1",
	  "taskName" : "Grocery Purchase"
	}
	Response should be 
	{
	  "message": "Task added successfully."
	}
**(b)** 
	GET http://host:8084/to-do-app-client/tasks
	
	In response, list of to-do tasks should be coming e.g.
	
	[
	  {
	    "taskId": 1,
	    "taskName": "Grocery Purchase"
	  },
	  {
	    "taskId": 2,
	    "taskName": "Flight Ticket Booking over weekend"
	  }
	]

**(c)** 
	GET http://host:8084/to-do-app-client/config
	
	Response should be 
	"Hello From Configuration Server"









