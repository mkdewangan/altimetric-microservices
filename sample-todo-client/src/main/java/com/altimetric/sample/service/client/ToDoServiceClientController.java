package com.altimetric.sample.service.client;


import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.altimetric.sample.service.domain.ToDoTask;
import com.altimetric.sample.service.domain.ToDoTaskResult;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("/to-do-app-client")
public class ToDoServiceClientController {
	
	 @Autowired
	 Util util;
	 
    private RestTemplate restTemplate = new RestTemplate();
      
    private List<ToDoTask> taskList=new ArrayList<ToDoTask>();
    
    @RequestMapping(value = "tasks", method = RequestMethod.POST)
    public ResponseEntity<ToDoTaskResult> addTask(@RequestBody ToDoTask task) {

    	//URI uri = util.getServiceUrl("to-do-app", "http://localhost:8082/to-do-app");
    	URI uri = util.getServiceUrl("to-do-app", null);
        String url = uri.toString() + "/to-do-app/tasks" ;
        
        System.out.println("URL : " + url);
        
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ToDoTask> entity = new HttpEntity<ToDoTask>(task, requestHeaders);

        
        ResponseEntity<String> resultStr = restTemplate.postForEntity(url, entity, String.class);
        
        System.out.println("addTask http-status: {}" + resultStr.getStatusCode());
        System.out.println("addTask body: {}" + resultStr.getBody());
       
        ToDoTaskResult result = response2TaskResult(resultStr);
       	
        return new ResponseEntity<ToDoTaskResult>(result, HttpStatus.OK);
    }

    
    @RequestMapping(value = "tasks", method = RequestMethod.GET)
    public ResponseEntity<List<ToDoTask>> listTasks() {
    	
    	URI uri = util.getServiceUrl("to-do-app", null);
        String url = uri.toString() + "/to-do-app/tasks" ;
        
        System.out.println("URL : " + url);
        
        ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
        
        System.out.println("GetProduct http-status: {}" + resultStr.getStatusCode());
        System.out.println("GetProduct body: {}" + resultStr.getBody());

        List<ToDoTask> taskList = response2Tasks(resultStr);
     	
        return new ResponseEntity<List<ToDoTask>>(taskList, HttpStatus.OK);
    }
    
    @RequestMapping(value = "config", method = RequestMethod.GET)
    public ResponseEntity<String> getConfig() {
    	
    	URI uri = util.getServiceUrl("to-do-app", null);
        String url = uri.toString() + "/to-do-app/config" ;
        
        System.out.println("URL : " + url);
        
        ResponseEntity<String> resultStr = restTemplate.getForEntity(url, String.class);
        
        System.out.println("GetProduct http-status: {}" + resultStr.getStatusCode());
        System.out.println("GetProduct body: {}" + resultStr.getBody());

        //List<ToDoTask> taskList = response2Tasks(resultStr);
     	
        return new ResponseEntity<String>(resultStr.getBody(), HttpStatus.OK);
        
        
    }
    
    
    public ToDoTaskResult response2TaskResult(ResponseEntity<String> response) {
        try {
        	
        	ObjectMapper mapper = new ObjectMapper();
        	ToDoTaskResult result = mapper.readValue(response.getBody(), ToDoTaskResult.class);
        	return result;
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 
    private List<ToDoTask> response2Tasks(ResponseEntity<String> response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<ToDoTask> tasks = mapper.readValue(response.getBody(), new TypeReference<List<ToDoTask>>() {});
            return tasks;

        } catch (IOException e) {
        	System.out.println("IO-err. Failed to read JSON " + e);
            throw new RuntimeException(e);

        } catch (RuntimeException re) {
        	System.out.println("RTE-err. Failed to read JSON " + re);
            throw re;
        }
    }
    
 

}
